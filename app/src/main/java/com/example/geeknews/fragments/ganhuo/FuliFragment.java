package com.example.geeknews.fragments.ganhuo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.adapters.ganhuo.MyFuLiAdapter;
import com.example.geeknews.api.GanhuoApi;
import com.example.geeknews.beans.zhihu.ganhuo.GanAndroid;
import com.example.geeknews.beas.fragment.BaseFragment;
import com.example.geeknews.presenter.GanhuoPresenter;
import com.example.geeknews.view.GanhuoView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FuliFragment extends BaseFragment<GanhuoView<GanAndroid>, GanhuoPresenter<GanhuoView<GanAndroid>>> implements GanhuoView<GanAndroid>, XRecyclerView.LoadingListener {

    @BindView(R.id.xrlv)
    XRecyclerView mXrlv;
    Unbinder unbinder;
    private int mPage = 1;
    private String mTech = "福利";
    private MyFuLiAdapter mMyFuLiAdapter;
    private List<GanAndroid.ResultsBean> mData = new ArrayList<>();

    public FuliFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_fuli;
    }

    @Override
    protected void load() {

    }

    @Override
    protected void initData() {
        presenter.getGanhuo(mTech,mPage, GanhuoApi.JISHU);

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mXrlv.setLayoutManager(manager);
        mXrlv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        mMyFuLiAdapter = new MyFuLiAdapter(mData,getContext());
        mXrlv.setAdapter(mMyFuLiAdapter);
        mXrlv.setLoadingListener(this);
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void show(GanAndroid ganAndroid) {
        Log.e("5555555555",ganAndroid.getResults().get(2).getUrl());
        mMyFuLiAdapter.setData(ganAndroid.getResults(),mPage);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected GanhuoPresenter<GanhuoView<GanAndroid>> createPresenter() {
        return new GanhuoPresenter<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        mPage =1;
        presenter.getGanhuo(mTech,mPage,GanhuoApi.JISHU);
        mXrlv.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        mPage++;
        presenter.getGanhuo(mTech,mPage,GanhuoApi.JISHU);
        mXrlv.loadMoreComplete();
    }
}
