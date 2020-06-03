package com.example.qq.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView{
    protected P mPresenter;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mBind = ButterKnife.bind(this);
        mPresenter = initPresenter();
        if (mPresenter != null){
            mPresenter.AttachView(this);
        }
        initView();
        initData();
        initListener();
    }

    protected P initPresenter() {
        return null;
    }

    protected void initListener() {}

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
        if (mPresenter != null){
            mPresenter.disAttachView();
        }
    }
}
