package com.example.itemclass;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itemclass.bean.SearchBean;
import com.example.itemclass.greendaodemo.db.SearchBeanDao;
import com.example.itemclass.widget.FlowLayout;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBackSearch;
    private EditText mEditSearch;
    private TextView mTvSearch;
    private ImageView mClearSearch;
    private FlowLayout mFlowSearch;
    private ConstraintLayout mFlowcon;
    private RecyclerView mRecySearch;
    private ConstraintLayout mNetcon;
    private SearchBeanDao searchBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initListener();
        updateDataBase();
    }

    private void initListener() {
        mEditSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(s)){
                    mFlowcon.setVisibility(View.VISIBLE);
                    mNetcon.setVisibility(View.GONE);
                }else {
                    mNetcon.setVisibility(View.VISIBLE);
                    mFlowcon.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void updateDataBase() {
        List<SearchBean> searchBeans = searchBeanDao.loadAll();
        mFlowSearch.removeAllViews();

        if(searchBeans!=null && searchBeans.size()>0){
            Collections.sort(searchBeans, new Comparator<SearchBean>() {
                @Override
                public int compare(SearchBean o1, SearchBean o2) {
                    return (int)(o2.getTime()-o1.getTime());
                }
            });
            for (int i = 0; i < searchBeans.size(); i++) {
                TextView inflate = (TextView) LayoutInflater.from(this).inflate(R.layout.item_label, null);
                inflate.setText(searchBeans.get(i).getKerword());
                mFlowSearch.addView(inflate);
            }
        }

    }

    private void initView() {
        mBackSearch = (ImageView) findViewById(R.id.search_back);
        mBackSearch.setOnClickListener(this);
        mEditSearch = (EditText) findViewById(R.id.search_edit);
        mTvSearch = (TextView) findViewById(R.id.search_tv);
        mTvSearch.setOnClickListener(this);
        mClearSearch = (ImageView) findViewById(R.id.search_clear);
        mClearSearch.setOnClickListener(this);
        mFlowSearch = (FlowLayout) findViewById(R.id.search_flow);
        mFlowcon = (ConstraintLayout) findViewById(R.id.flowcon);
        mRecySearch = (RecyclerView) findViewById(R.id.search_recy);
        mNetcon = (ConstraintLayout) findViewById(R.id.netcon);
        searchBeanDao = BaseApp.getInstance().getDaoSession().getSearchBeanDao();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_back:
                // TODO 20/06/17
                finish();
                break;
            case R.id.search_tv:
                // TODO 20/06/17
                String trim = mEditSearch.getText().toString().trim();

                SearchBean searchBean = new SearchBean(trim, System.currentTimeMillis());
                searchBeanDao.insertOrReplace(searchBean);
                updateDataBase();
                break;
            case R.id.search_clear:
                searchBeanDao.deleteAll();
updateDataBase();
                // TODO 20/06/17

                break;
            default:
                break;
        }
    }
}
