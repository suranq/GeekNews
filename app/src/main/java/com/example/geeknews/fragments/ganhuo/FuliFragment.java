package com.example.geeknews.fragments.ganhuo;


import android.content.Intent;
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
import com.example.geeknews.activitys.weixin.ganhuo.MeiziActivity;
import com.example.geeknews.adapters.ganhuo.MyFuLiAdapter;
import com.example.geeknews.api.GanhuoApi;
import com.example.geeknews.beans.zhihu.ganhuo.GanAndroid;
import com.example.geeknews.beas.fragment.BaseFragment;
import com.example.geeknews.presenter.GanhuoPresenter;
import com.example.geeknews.view.GanhuoView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FuliFragment extends BaseFragment<GanhuoView<String>, GanhuoPresenter<GanhuoView<String>>> implements GanhuoView<String>, XRecyclerView.LoadingListener {

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

        mMyFuLiAdapter.setOnItemListener(new MyFuLiAdapter.OnItemListener() {
            @Override
            public void OnItemListener(GanAndroid.ResultsBean resultsBean) {
                String url = resultsBean.getUrl();
                Intent intent = new Intent(getContext(), MeiziActivity.class);
                intent.putExtra("meinv",url);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void show(String s, GanhuoApi ganhuoApi) {
        Gson gson = new Gson();
        switch (ganhuoApi) {
            case JISHU:
                GanAndroid ganAndroid = gson.fromJson(s, GanAndroid.class);
                mMyFuLiAdapter.setData(ganAndroid.getResults(),mPage);
                break;
        }
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected GanhuoPresenter<GanhuoView<String>> createPresenter() {
        return new GanhuoPresenter<>();
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
