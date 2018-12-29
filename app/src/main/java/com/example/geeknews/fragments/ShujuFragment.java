package com.example.geeknews.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.geeknews.R;
import com.example.geeknews.adapters.shuju.ShujuActivity;
import com.example.geeknews.api.ShujuApi;
import com.example.geeknews.beans.zhihu.shuju.ShujuTitle;
import com.example.geeknews.beas.fragment.BaseFragment;
import com.example.geeknews.presenter.ShujuPresenter;
import com.example.geeknews.view.ShujuView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShujuFragment extends BaseFragment<ShujuView<String>, ShujuPresenter<ShujuView<String>>> implements ShujuView<String> {


    @BindView(R.id.tab)
    Toolbar mTab;
    @BindView(R.id.iv_la)
    ImageView mIvLa;
    @BindView(R.id.vpager)
    ViewPager mVpager;
    Unbinder unbinder;
    private ShujuTitle mShujuTitle;

    public ShujuFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_shuju;
    }

    @Override
    protected void load() {

    }

    @Override
    protected void initData() {
        Map<String, Object> map = new HashMap<>();
        map.put("appKey", "4f359e9003324dd0a6cff75e229ebbc3");
        presenter.getShuju("categories", map, ShujuApi.TITLE);

    }

    @Override
    public void show(String s, ShujuApi shujuApi) {
        Gson gson = new Gson();
        switch (shujuApi) {
            case TITLE:
                mShujuTitle = gson.fromJson(s, ShujuTitle.class);
                Log.e("sssssss", mShujuTitle.getRESULT().get(1));
                break;
        }
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected ShujuPresenter<ShujuView<String>> createPresenter() {
        return new ShujuPresenter<>();
    }

    @OnClick(R.id.iv_la)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), ShujuActivity.class);
        startActivity(intent);
    }
}
