package com.example.geeknews.beas.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 马明祥 on 2018/12/21.
 */

public abstract class SimpleActivity extends AppCompatActivity{

    public Activity mActivity;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createLayoutId());
        mBind = ButterKnife.bind(this);
        mActivity = this;
        wiewCreated();
        initData();
    }
    //初始化数据
    protected abstract void initData();

    public void wiewCreated() {

    }
    //初始化布局
    protected abstract int createLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null){
            mBind.unbind();
        }
    }
}
