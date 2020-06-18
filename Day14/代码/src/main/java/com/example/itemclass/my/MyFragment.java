package com.example.itemclass.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.itemclass.R;
import com.example.itemclass.login.LoginActivity;


public class MyFragment extends Fragment implements View.OnClickListener {
    private TextView mOrRegistLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.my_frament, null);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        mOrRegistLogin = (TextView) itemView.findViewById(R.id.login_or_regist);
        mOrRegistLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_or_regist:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                // TODO 20/06/17
                break;
            default:
                break;
        }
    }
}