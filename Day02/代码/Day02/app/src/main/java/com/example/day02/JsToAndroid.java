package com.example.day02;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.JavascriptInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

class JsToAndroid {
    private Activity context;
    private final int CODE_PIC = 0;
    private final int CODE_CAMERA = 1;
    private final int CODE_CROP = 3;
    private final int CODE_PERMISSION = 4;
    private boolean FLAG_PERMISSION = false;
    private List<String> list;
    private Uri cameraUri, cropUri;


    public JsToAndroid(Activity context) {
        this.context = context;
    }
    @JavascriptInterface
    public void toPic(){
        //6.0以后需要获取权限
        obtainPermission();
        openPhoto();
    }

    //获取权限
    private void obtainPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            list = new ArrayList<>();
            if(context.checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
            {
                list.add(Manifest.permission.CAMERA);
            }
            if(context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                list.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if(list.size()!=0)
                context.requestPermissions(list.toArray(new String[list.size()]),CODE_PERMISSION);
        }
        else
            FLAG_PERMISSION = true;
    }

    //打开相册
    private void openPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        context.startActivityForResult(intent, CODE_PIC);
    }


}