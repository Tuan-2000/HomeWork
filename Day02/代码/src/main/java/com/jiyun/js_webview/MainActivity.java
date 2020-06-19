package com.jiyun.js_webview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private WebView mWebView;
    private WebSettings settings;
    private static final String APP_CACAHE_DIRNAME = "/webViewPath";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mWebView = (WebView) findViewById(R.id.web);
        initWebView();//初始化WebView
//        mWebView.loadUrl("file:///android_asset/demo2.html");
        //---------------------------------------以下是JS(BUtton和图片都是再网页里面的)调用Android------------------------------------
        //1.要映射的那个类对象  2.类对象引用
        mWebView.addJavascriptInterface(new JsToAndroid(this),"test");
    }

    private void initWebView() {
        //想让网页适配webview
        settings = mWebView.getSettings();
        //支持js代码
        settings.setJavaScriptEnabled(true);
        //允许js弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);


        //设置自适应屏幕，两者合用
        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //设置网页支持缩放
        //缩放操作
        settings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        settings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        settings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //网页无图模式
        settings.setLoadsImagesAutomatically(true); //支持自动加载图片

        //设置编码格式 为gbk  当网页出现乱码 就可以设置网页编码格式
        settings.setDefaultTextEncodingName("GBK");//设置编码格式
    }

    public void load_webView(View view) {
        //Android调用JS代码的第一种方法
//        mWebView.loadUrl("javascript:androidCallJs(\"哈哈,我是Android里面的数据\")");
        //Android 4.4之后出现的api
        mWebView.evaluateJavascript("javascript:androidCallJs(\"哈哈,我是Android里面的数据\")", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                Log.e("TAG", value + "========");
            }
        });
        //两种区别： 前者不能获取js函数的返回值   后者可以获取js函数的返回值

        //加载webView地址
//        mWebView.loadUrl("https://www.wanandroid.com/");
        //WebView在程序内打开,不跳转系统浏览器
        mWebView.setWebViewClient(new WebViewClient() {
            //webView开始加载
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }
            //webView加载完成
            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });

        //webview的缓存   如果有网 根据cache-ctral 是否从网络上或者缓存中加载  如果没网就从缓存中获取网页(离线加载)
        if (NetWorkUtl.isConnected(getApplicationContext())) {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        settings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        settings.setDatabaseEnabled(true);   //开启 database storage API 功能
        settings.setAppCacheEnabled(true);//开启 Application Caches 功能
        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME;
        settings.setAppCachePath(cacheDirPath); //设置  Application Caches 缓存目录

        //获取WebView里面的内容
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.e("TAG", "网页的标题是：" + title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }

            @Override
            public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
                super.onReceivedTouchIconUrl(view, url, precomposed);
                Log.e("TAG", "点击的图片的url地址：" + url);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.e("TAG", "当前加载网页的进度是：" + newProgress);
            }
        });
    }

    //点击返回键
    @Override
    public void onBackPressed() {
        if (mWebView != null && mWebView.canGoBack()) {
            String webviewUrl = mWebView.getUrl();
            //如果当前网页是这些tab(导航，体系, 项目分类，工具)的话  再点击返回键就退出程序
            if (webviewUrl.equals("https://www.wanandroid.com/index") ||
                    webviewUrl.equals("https://www.wanandroid.com/user_article") ||
                    webviewUrl.equals("https://www.wanandroid.com/navi") ||
                    webviewUrl.equals("https://www.wanandroid.com/wenda") ||
                    webviewUrl.equals("https://www.wanandroid.com/projectindex") ||
                    webviewUrl.equals("https://www.wanandroid.com/wxarticle/list/408/1") ||
                    webviewUrl.equals("https://www.wanandroid.com/project") ||
                    webviewUrl.equals("https://www.wanandroid.com/tools"))
                super.onBackPressed();
            else {
                //当前网页不是这些，有可能是其他的网页  比如：二级网页
                mWebView.goBack();
            }
        } else//如果webview不能返回了，说明现当前网页就是首页tab 对应的网页
            super.onBackPressed();
    }


    private final int CODE_PIC = 0;
    private final int CODE_CAMERA = 1;
    private final int CODE_CROP = 3;
    private final int CODE_PERMISSION = 4;
    private boolean FLAG_PERMISSION = false;
    private Uri cameraUri, cropUri;


    //剪切图片
    private void cropPhoto(Uri uri) {
        Log.d("test","uri:"+uri.toString());
        File file = new File(Environment.getExternalStorageDirectory(),"cropImage.jpg");
        if(file.exists())
            file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
        }
        cropUri = Uri.fromFile(file);
        intent.setDataAndType(uri,"image/*");
        //裁剪图片的宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("crop", "true");//可裁剪
        // 裁剪后输出图片的尺寸大小
        //intent.putExtra("outputX", 400);
        //intent.putExtra("outputY", 200);
        intent.putExtra("scale", true);//支持缩放
        String path = file.getPath();///storage/emulated/0/cropImage.jpg
        Log.e("uri",path);
        intent.putExtra("file", path);//file地址
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//输出图片格式
        intent.putExtra("noFaceDetection", true);//取消人脸识别
        startActivityForResult(intent, CODE_CROP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CODE_PIC:
                Log.e("uri","相册："+data.getData().toString());
                cropPhoto(data.getData());
                break;
            case CODE_CROP:
                    String file = data.getStringExtra("file");
                    Log.e("uri","file："+file);
                    mWebView.loadUrl("javascript:");
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("test", Arrays.toString(grantResults));
        for(int i=0;i<grantResults.length;i++) {
            if (grantResults[i] == -1)
            {
                FLAG_PERMISSION = false;
                break;
            }
        }
        FLAG_PERMISSION = true;
    }

    public void load_webView1(View view) {
        //---------------------------JS调用Android第二种方式------------------------------------
//      mWebView.loadUrl("file:///android_asset/demo3.html");
//
//        mWebView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                Uri uri = Uri.parse(url);
//                if ("js".equals(uri.getScheme())) {
//
//                    if (uri.getAuthority().equals("webview")) {
//
//                        //TODO 可以执行Android代码了
//                        Log.e("TAG", "执行Android代码了");
//                        //打开相册，获取图片，裁剪
//
//                    }
//
//                    return true;
//                }
//
//                return super.shouldOverrideUrlLoading(view, url);
//            }
//
//        });

        //------------------------------------JS调用Android第三种方式-------------------
        mWebView.loadUrl("file:///android_asset/demo4.html");
        mWebView.setWebChromeClient(new WebChromeClient() {


            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {

                Uri uri = Uri.parse(message);
                if (uri.getScheme().equals("js")) {
                    if (uri.getAuthority().equals("webview")) {

                        //TODO 可以执行Android的代码了
                        Log.e("TAG","HAHHHHHHHHHH,js调用Android了");


                        result.confirm("哈哈哈，这是要传递给JS的返回值");
                    }
                    return true;
                }

                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.e("UWUEWUWE1111UUWEUWE", url+"======"+message+"========="+result.toString());
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                // Log.e("UWUEWUWEUUWEUWE", url+"======"+message+"========="+result.toString());
                return super.onJsConfirm(view, url, message, result);
            }

        });

    }
}
