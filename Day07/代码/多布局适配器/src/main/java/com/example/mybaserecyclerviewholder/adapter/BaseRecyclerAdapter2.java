package com.example.mybaserecyclerviewholder.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mybaserecyclerviewholder.R;
import com.example.mybaserecyclerviewholder.eneity.NetDataBean;

import java.util.List;

public class BaseRecyclerAdapter2 extends BaseMultiItemQuickAdapter<NetDataBean.DataBean.DatasBean,BaseViewHolder> {
    private Context context;

    public BaseRecyclerAdapter2(Context context, List<NetDataBean.DataBean.DatasBean> data) {
        super(data);
        addItemType(NetDataBean.DataBean.DatasBean.TYPE1, R.layout.activity_item1);
        addItemType(NetDataBean.DataBean.DatasBean.TYPE2,R.layout.activity_item2);
        addItemType(NetDataBean.DataBean.DatasBean.TYPE3,R.layout.activity_item3);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, NetDataBean.DataBean.DatasBean item) {
        switch (item.getItemType()){
            case NetDataBean.DataBean.DatasBean.TYPE1:
                helper.setText(R.id.item1_tv,item.getTitle());
                break;
            case NetDataBean.DataBean.DatasBean.TYPE2:
                Glide.with(context).load(item.getEnvelopePic()).into((ImageView) helper.getView(R.id.item2_img));
                break;
            case NetDataBean.DataBean.DatasBean.TYPE3:
                helper.setText(R.id.item3_iv,item.getTitle());
                Glide.with(context).load(item.getEnvelopePic()).into((ImageView) helper.getView(R.id.item3_img));
                break;
            default:
                break;
        }
    }
}