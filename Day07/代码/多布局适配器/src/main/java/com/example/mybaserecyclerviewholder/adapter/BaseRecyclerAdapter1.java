package com.example.mybaserecyclerviewholder.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mybaserecyclerviewholder.R;
import com.example.mybaserecyclerviewholder.eneity.NetDataBean;

import java.util.List;

public class BaseRecyclerAdapter1 extends BaseQuickAdapter<NetDataBean.DataBean.DatasBean, BaseViewHolder> {

    public BaseRecyclerAdapter1(int layoutResId, @Nullable List<NetDataBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NetDataBean.DataBean.DatasBean item) {
        helper.setText(R.id.item1_tv,item.getTitle());
    }
}