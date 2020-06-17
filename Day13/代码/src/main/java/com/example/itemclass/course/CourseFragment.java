package com.example.itemclass.course;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.itemclass.R;
import com.example.itemclass.SearchActivity;

public class CourseFragment extends Fragment implements View.OnClickListener {
    private TextView mSortCourse;
    private ImageView mImgCourse;
    private boolean isChecked = false;
    private ConstraintLayout mSelItem;
    private ImageView mSkipSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.course_frament, null);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        mSortCourse = (TextView) itemView.findViewById(R.id.course_sort);
        mSortCourse.setOnClickListener(this);
        mImgCourse = (ImageView) itemView.findViewById(R.id.course_img);
        mImgCourse.setOnClickListener(this);
        mSelItem = (ConstraintLayout) itemView.findViewById(R.id.item_sel);
        mSkipSearch = (ImageView) itemView.findViewById(R.id.search_skip);
        mSkipSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.course_sort:
                // TODO 20/06/10
                isCheckedrResult();
                break;
            case R.id.course_img:
                // TODO 20/06/10
                isCheckedrResult();
                break;
            case R.id.search_skip:// TODO 20/06/17
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void isCheckedrResult() {
        if (isChecked == false) {
            isChecked = true;
            mSortCourse.setTextColor(Color.parseColor("#eb6100"));
            mImgCourse.setBackground(getResources().getDrawable(R.drawable.common_ic_filter_up));
            initPop();
        } else {
            isChecked = false;
            mSortCourse.setTextColor(Color.parseColor("#708090"));
            mImgCourse.setBackground(getResources().getDrawable(R.drawable.common_ic_filter_down));
        }
    }

    private Context context = getContext();

    private void initPop() {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.pop_laout, null);
        final PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, 500);
        popupWindow.setBackgroundDrawable(null);
        backgroundAlpha(0.5f);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(mSelItem, 0, 0);
        //点击非菜单部分退出
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }
}