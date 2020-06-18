package com.example.itemclass.appointment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.itemclass.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class AppointmentFragment extends Fragment implements View.OnClickListener{

    private TabLayout mTabs;
    private TextView mTv;
    private Button mBut;
//    private FrameLayout mFramelayoutAppointment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.appointment_frament, null);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        mTabs = (TabLayout) itemView.findViewById(R.id.tabs);
//        mFramelayoutAppointment = (FrameLayout) itemView.findViewById(R.id.appointment_framelayout);
        initTab();
        mTv = (TextView) itemView.findViewById(R.id.tv);
        mBut = (Button) itemView.findViewById(R.id.but);
        mBut.setOnClickListener(this);
    }

    private void initTab() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("待上课");
        strings.add("已上课");
        strings.add("已取消");
        for (int i = 0; i < strings.size(); i++) {
            mTabs.addTab(mTabs.newTab().setText(strings.get(i)));
        }

        mTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position){
                    case 0:
                        mTv.setText("还没有待上课记录哦");
                        mBut.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        mTv.setText("还没有上课记录哦");
                        mBut.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        mTv.setText("还没有取消上课记录哦");
                        mBut.setVisibility(View.GONE);
                        break;
                    default:
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but:
                // TODO 20/06/11
                break;
            default:
                break;
        }
    }
}