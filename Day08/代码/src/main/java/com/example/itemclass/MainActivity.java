package com.example.itemclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import com.example.itemclass.home.HomeActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //倒计时
        initTimer();
    }

    private void initTimer() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                MainActivity.this.startActivity(new Intent(MainActivity.this, HomeActivity.class));
                cancel();
//                onDestroy();
            }
        };
        timer.schedule(timerTask, 3000, 3000);

    }

    public void MainDestroy(){
        onDestroy();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}