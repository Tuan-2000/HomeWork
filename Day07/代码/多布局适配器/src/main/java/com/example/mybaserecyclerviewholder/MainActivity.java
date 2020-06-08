package com.example.mybaserecyclerviewholder;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybaserecyclerviewholder.adapter.BaseRecyclerAdapter1;
import com.example.mybaserecyclerviewholder.adapter.BaseRecyclerAdapter2;
import com.example.mybaserecyclerviewholder.eneity.NetDataBean;
import com.example.mybaserecyclerviewholder.net.Constant;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        initData1();
        initData2();
    }

    private void initData2() {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request.Builder request = new Request.Builder().url(Constant.BASE_URL);
        Call call = okHttpClient.newCall(request.build());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                NetDataBean netDataBean = gson.fromJson(string, NetDataBean.class);
                final List<NetDataBean.DataBean.DatasBean> datas = netDataBean.getData().getDatas();
                for (int i = 0; i < datas.size(); i++) {
                    NetDataBean.DataBean.DatasBean datasBean = datas.get(i);
                    if (i%3 == 0){
                        datasBean.setMtype(NetDataBean.DataBean.DatasBean.TYPE1);
                    }else if (i %3 ==1){
                        datasBean.setMtype(NetDataBean.DataBean.DatasBean.TYPE2);
                    }else {
                        datasBean.setMtype(NetDataBean.DataBean.DatasBean.TYPE3);
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRecycler.setLayoutManager(new LinearLayoutManager(context));
                        mRecycler.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
                        BaseRecyclerAdapter2 adapter2 = new BaseRecyclerAdapter2(context,datas);
                        mRecycler.setAdapter(adapter2);
                    }
                });
            }
        });
    }


    private void initData1() {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request.Builder request = new Request.Builder().url(Constant.BASE_URL);
        Call call = okHttpClient.newCall(request.build());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                NetDataBean netDataBean = gson.fromJson(string, NetDataBean.class);
                final List<NetDataBean.DataBean.DatasBean> datas = netDataBean.getData().getDatas();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRecycler.setLayoutManager(new LinearLayoutManager(context));
                        BaseRecyclerAdapter1 adapter1 = new BaseRecyclerAdapter1(R.layout.activity_item1, datas);
                        mRecycler.setAdapter(adapter1);
                    }
                });
            }
        });
    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
    }
}