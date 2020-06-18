package com.example.itemclass;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.itemclass.appointment.AppointmentFragment;
import com.example.itemclass.course.CourseFragment;
import com.example.itemclass.homefragment.HomeFragment;
import com.example.itemclass.my.MyFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.example.itemclass.home.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Timer timer;
    private FrameLayout mFrame;
    private RadioButton mShouyeMain;
    private RadioButton mClassMain;
    private RadioButton mYueClassMain;
    private RadioButton mPersonMain;
    private RadioGroup mGroupRadio;
    private ArrayList<Fragment> fragments;
    private FragmentManager fragmentManager;
    private BottomNavigationView mNavigationBottom;
    private HomeFragment homeFragment;
    private CourseFragment courseFragment;
    private AppointmentFragment appointmentFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //倒计时
        initTimer();

        /*initView();
        initListener();*/
    }

   /* private void initListener() {
        mNavigationBottom.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (item.getItemId()){
                    case R.id.home_buthome:
                        fragmentTransaction.show(homeFragment)
                                .hide(courseFragment)
                                .hide(appointmentFragment)
                                .hide(myFragment)
                                .commit();
                        break;
                    case R.id.home_course:
                        fragmentTransaction.show(courseFragment)
                                .hide(homeFragment)
                                .hide(appointmentFragment)
                                .hide(myFragment)
                                .commit();
                        break;
                    case R.id.home_appoinment:
                        fragmentTransaction.show(appointmentFragment)
                                .hide(courseFragment)
                                .hide(homeFragment)
                                .hide(myFragment)
                                .commit();
                        break;
                    case R.id.home_my:
                        fragmentTransaction.show(myFragment)
                                .hide(courseFragment)
                                .hide(appointmentFragment)
                                .hide(homeFragment)
                                .commit();
                        break;
                }
            }
        });
    }

    private void initView() {
        mNavigationBottom = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mFrame = (FrameLayout) findViewById(R.id.frame);
        fragmentManager = getSupportFragmentManager();
        initFragments();

    }

    private void initFragments() {
        homeFragment = new HomeFragment();
        courseFragment = new CourseFragment();
        appointmentFragment = new AppointmentFragment();
        myFragment = new MyFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame, homeFragment).add(R.id.frame, courseFragment)
                .add(R.id.frame, appointmentFragment)
                .add(R.id.frame, myFragment)
                .hide(courseFragment)
                .hide(appointmentFragment)
                .hide(myFragment);
        fragmentTransaction.commit();
    }
*/

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