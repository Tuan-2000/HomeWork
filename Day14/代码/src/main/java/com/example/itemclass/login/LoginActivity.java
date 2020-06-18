package com.example.itemclass.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itemclass.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImgLogin;
    private EditText mPhoneEt;
    private TextView mGetVerifyCode;
    private EditText mVerifyEt;
    private Button mLoginBt;
    private TextView mPassSkipLogin;
    private TextView mLoginSanfang;
    private ImageView mLoginWx;
    private ImageView mLoginQq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        mImgLogin = (ImageView) findViewById(R.id.login_img);
        mPhoneEt = (EditText) findViewById(R.id.et_phone);
        mGetVerifyCode = (TextView) findViewById(R.id.getVerifyCode);
        mVerifyEt = (EditText) findViewById(R.id.et_verify);
        mLoginBt = (Button) findViewById(R.id.bt_login);
        mLoginBt.setOnClickListener(this);
        mPassSkipLogin = (TextView) findViewById(R.id.login_pass_skip);
        mLoginSanfang = (TextView) findViewById(R.id.sanfang_login);
        mLoginWx = (ImageView) findViewById(R.id.wx_login);
        mLoginQq = (ImageView) findViewById(R.id.qq_login);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                // TODO 20/06/17
                break;
            default:
                break;
        }
    }
}