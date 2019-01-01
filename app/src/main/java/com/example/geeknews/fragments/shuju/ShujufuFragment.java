package com.example.geeknews.fragments.shuju;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.adapters.shuju.ShujuXiangAdapter;
import com.example.geeknews.api.ShujuApi;
import com.example.geeknews.beans.zhihu.shuju.ShujuData;
import com.example.geeknews.beas.fragment.BaseFragment;
import com.example.geeknews.presenter.ShujuPresenter;
import com.example.geeknews.view.ShujuView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShujufuFragment extends BaseFragment<ShujuView<String>, ShujuPresenter<ShujuView<String>>> implements ShujuView<String> {


    @BindView(R.id.xrlv)
    XRecyclerView mXrlv;
    Unbinder unbinder;
    private String mItem;
    private int mPage = 1;
    private ShujuXiangAdapter mShujuXiangAdapter;
    private List<ShujuData.RESULTBean.NewsListBean> mData = new ArrayList<>();
    private Map<String, Object> mMap;

    public ShujufuFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ShujufuFragment(String item) {
        mItem = item;
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_shujufu;
    }

    @Override
    protected void load() {
        mMap = new HashMap<>();
        mMap.put("appKey","4f359e9003324dd0a6cff75e229ebbc3");
        mMap.put("category",mItem);
        mMap.put("page",mPage);
    }

    @Override
    protected void initData() {
        presenter.getShuju("list",mMap,ShujuApi.DATA);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mXrlv.setLayoutManager(manager);
        mXrlv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        mShujuXiangAdapter = new ShujuXiangAdapter(mData,getContext());
        mXrlv.setAdapter(mShujuXiangAdapter);
    }

    @Override
    public void show(String s, ShujuApi shujuApi) {
        Gson gson = new Gson();
        switch (shujuApi) {
            case DATA:
                ShujuData shujuData = gson.fromJson(s, ShujuData.class);
                mShujuXiangAdapter.setData(shujuData.getRESULT().getNewsList());
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
}
