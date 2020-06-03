package com.example.qq;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.qq.R;
import com.example.qq.base.BaseActivity;
import com.example.qq.ui.adapter.VpFragmentAdapter;
import com.example.qq.ui.fragment.ContactsFragment;
import com.example.qq.ui.fragment.DynamicFragment;
import com.example.qq.ui.fragment.MessageFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_top_user)
    ImageView mIvTopUser;
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.navigation)
    NavigationView mNavigation;
    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerlayout;
    @BindView(R.id.tv_top_title)
    TextView mTvTopTitle;
    @BindView(R.id.iv_top_add)
    ImageView mIvTopAdd;
    @BindView(R.id.tv_top_add)
    TextView mTvTopAdd;

    public void initView() {
        mNavigation.setItemIconTintList(null);
        initTab();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initTab() {

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MessageFragment());
        fragments.add(new ContactsFragment());
        fragments.add(new DynamicFragment());
        VpFragmentAdapter vpFragmentAdapter = new VpFragmentAdapter(getSupportFragmentManager(), fragments);
        mVp.setAdapter(vpFragmentAdapter);
        mTablayout.setupWithViewPager(mVp);
        mTablayout.getTabAt(0).setText("消息").setIcon(R.drawable.tab_select_message);
        mTablayout.getTabAt(1).setText("联系人").setIcon(R.drawable.tab_select_im);
        mTablayout.getTabAt(2).setText("动态").setIcon(R.drawable.tab_select_d);
    }

    @Override
    protected void initListener() {
        mIvTopUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerlayout.openDrawer(mNavigation);
            }
        });
        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mTvTopTitle.setText(tab.getText());
                switch (tab.getPosition()) {
                    case 0:
                        mIvTopAdd.setVisibility(View.VISIBLE);
                        mTvTopAdd.setVisibility(View.GONE);
                        break;
                    case 1:
                        mIvTopAdd.setVisibility(View.GONE);
                        mTvTopAdd.setVisibility(View.VISIBLE);
                        mTvTopAdd.setText("添加");
                        break;
                    case 2:
                        mIvTopAdd.setVisibility(View.GONE);
                        mTvTopAdd.setVisibility(View.VISIBLE);
                        mTvTopAdd.setText("更多");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initData() {

    }

}
