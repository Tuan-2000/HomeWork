package com.example.itemclass.home;

import android.app.Application;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itemclass.R;
import com.example.itemclass.appointment.AppointmentFragment;
import com.example.itemclass.course.CourseFragment;
import com.example.itemclass.homefragment.HomeFragment;
import com.example.itemclass.my.MyFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout mFramelayoutHome;
    private RadioButton mButhomeHome;
    private RadioButton mCourseHome;
    private RadioButton mAppoinmentHome;
    private RadioButton mMyHome;
    private RadioGroup mRadiogroup;
    private HomeFragment homeFragment;
    private AppointmentFragment appointmentFragment;
    private CourseFragment courseFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        mFramelayoutHome = (FrameLayout) findViewById(R.id.home_framelayout);
        mButhomeHome = (RadioButton) findViewById(R.id.home_buthome);
        mButhomeHome.setOnClickListener(this);
        mCourseHome = (RadioButton) findViewById(R.id.home_course);
        mCourseHome.setOnClickListener(this);
        mAppoinmentHome = (RadioButton) findViewById(R.id.home_appoinment);
        mAppoinmentHome.setOnClickListener(this);
        mMyHome = (RadioButton) findViewById(R.id.home_my);
        mMyHome.setOnClickListener(this);
        mRadiogroup = (RadioGroup) findViewById(R.id.radiogroup);

        mButhomeHome.setSelected(true);
        initFragment();
    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        appointmentFragment = new AppointmentFragment();
        courseFragment = new CourseFragment();
        myFragment = new MyFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_framelayout,homeFragment)
                .add(R.id.home_framelayout,courseFragment)
                .add(R.id.home_framelayout,appointmentFragment)
                .add(R.id.home_framelayout,myFragment)
                .show(homeFragment)
                .hide(courseFragment)
                .hide(appointmentFragment)
                .hide(myFragment)
                .commit();
    }


    //--------------使用onKeyUp()干掉他--------------

    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(HomeActivity.this, "再按一次退出程序--->onKeyUp", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_buthome:
                // TODO 20/06/08
                getSupportFragmentManager().beginTransaction()
                        .show(homeFragment)
                        .hide(courseFragment)
                        .hide(appointmentFragment)
                        .hide(myFragment)
                        .commit();
                mButhomeHome.setSelected(true);
                mCourseHome.setSelected(false);
                mAppoinmentHome.setSelected(false);
                mMyHome.setSelected(false);
                break;
            case R.id.home_course:
                // TODO 20/06/08
                getSupportFragmentManager().beginTransaction()
                        .hide(homeFragment)
                        .show(courseFragment)
                        .hide(appointmentFragment)
                        .hide(myFragment)
                        .commit();
                mCourseHome.setSelected(true);
                mButhomeHome.setSelected(false);
                mAppoinmentHome.setSelected(false);
                mMyHome.setSelected(false);
                break;
            case R.id.home_appoinment:
                // TODO 20/06/08
                getSupportFragmentManager().beginTransaction()
                        .hide(homeFragment)
                        .hide(courseFragment)
                        .show(appointmentFragment)
                        .hide(myFragment)
                        .commit();
                mAppoinmentHome.setSelected(true);
                mCourseHome.setSelected(false);
                mButhomeHome.setSelected(false);
                mMyHome.setSelected(false);
                break;
            case R.id.home_my:
                // TODO 20/06/08
                getSupportFragmentManager().beginTransaction()
                        .hide(homeFragment)
                        .hide(courseFragment)
                        .hide(appointmentFragment)
                        .show(myFragment)
                        .commit();
                mMyHome.setSelected(true);
                mCourseHome.setSelected(false);
                mButhomeHome.setSelected(false);
                mAppoinmentHome.setSelected(false);
                break;
            default:
                break;
        }
    }
}