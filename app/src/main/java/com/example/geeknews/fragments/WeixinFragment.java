package com.example.geeknews.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.activitys.weixin.WeixinActivity;
import com.example.geeknews.adapters.MyWeixinAdapter;
import com.example.geeknews.api.WeixinApi;
import com.example.geeknews.beans.zhihu.weixin.WeiXinBean;
import com.example.geeknews.beas.fragment.BaseFragment;
import com.example.geeknews.beas.fragment.SimpleFragment;
import com.example.geeknews.presenter.WeixinPresenter;
import com.example.geeknews.view.WeixinView;
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
public class WeixinFragment extends BaseFragment<WeixinView<WeiXinBean>,WeixinPresenter<WeixinView<WeiXinBean>>> implements WeixinView<WeiXinBean>, XRecyclerView.LoadingListener {


    @BindView(R.id.xrlv)
    XRecyclerView mXrlv;
    Unbinder unbinder;

    private int mPage = 1;
    private MyWeixinAdapter mMyWeixinAdapter;
    private List<WeiXinBean.NewslistBean> mData = new ArrayList<>();
    private Map<String, Object> mMap;

    public WeixinFragment() {
        // Required empty public constructor
    }


    @Override
    protected int createLayoutId() {
        return R.layout.fragment_weixin;
    }

    @Override
    protected void load() {

    }

    @Override
    protected void initData() {
        mMap = new HashMap<>();
        mMap.put("key","52b7ec3471ac3bec6846577e79f20e4c");
        mMap.put("num","10");
        mMap.put("page",mPage);
        presenter.getWeixin(mMap, WeixinApi.DATA);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mXrlv.setLayoutManager(manager);
        mXrlv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        mMyWeixinAdapter = new MyWeixinAdapter(mData,getContext());
        mXrlv.setAdapter(mMyWeixinAdapter);
        mXrlv.setLoadingListener(this);

        mMyWeixinAdapter.setonItemListener(new MyWeixinAdapter.onItemListener() {
            @Override
            public void OnItemlistener(View v, int position) {
                Intent intent = new Intent(getContext(), WeixinActivity.class);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected WeixinPresenter<WeixinView<WeiXinBean>> createPresenter() {
        return new WeixinPresenter<>();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showProgressbar() {
        presenter.setShowProgressbar();
    }

    @Override
    public void hideProgressbar() {
        presenter.setHideProgressbar();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void show(WeiXinBean weiXinBean) {
        mData.addAll(weiXinBean.getNewslist());
        Log.e("55555555",weiXinBean.getNewslist().get(1).getTitle());
        mMyWeixinAdapter.setData(weiXinBean.getNewslist(),mPage);
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        presenter.getWeixin(mMap,WeixinApi.DATA);
        mXrlv.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        mPage ++;
        mMap.put("key","52b7ec3471ac3bec6846577e79f20e4c");
        mMap.put("num","10");
        mMap.put("page",mPage);
        presenter.getWeixin(mMap,WeixinApi.DATA);
        mXrlv.loadMoreComplete();
    }
}
