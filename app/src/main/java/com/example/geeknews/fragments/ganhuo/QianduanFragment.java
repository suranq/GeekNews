package com.example.geeknews.fragments.ganhuo;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geeknews.MainActivity;
import com.example.geeknews.R;
import com.example.geeknews.activitys.weixin.WeixinActivity;
import com.example.geeknews.adapters.ganhuo.MyAndroidAdapter;
import com.example.geeknews.api.GanhuoApi;
import com.example.geeknews.beans.zhihu.ganhuo.GanAndroid;
import com.example.geeknews.beas.fragment.BaseFragment;
import com.example.geeknews.fragments.GanhuoFragment;
import com.example.geeknews.presenter.GanhuoPresenter;
import com.example.geeknews.shezhidao.SheZhi;
import com.example.geeknews.shezhidao.SheZhiku;
import com.example.geeknews.view.GanhuoView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class QianduanFragment extends BaseFragment<GanhuoView<String>, GanhuoPresenter<GanhuoView<String>>> implements GanhuoView<String>, XRecyclerView.LoadingListener {

    @BindView(R.id.iv_tech_blur)
    ImageView mIvTechBlur;
    @BindView(R.id.iv_tech_origin)
    ImageView mIvTechOrigin;
    @BindView(R.id.tv_tech_copyright)
    TextView mTvTechCopyright;
    @BindView(R.id.tech_appbar)
    AppBarLayout mTechAppbar;
    @BindView(R.id.xrlv)
    XRecyclerView mXrlv;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    Unbinder unbinder;
    private MyAndroidAdapter mMyAndroidAdapter;
    private int mPage = 1;
    private String mTech = "前端";
    private List<GanAndroid.ResultsBean> mData = new ArrayList<>();
    private String Url;

    public QianduanFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_android;
    }

    @Override
    protected void load() {
        MainActivity.mViewsearch.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("qqqqqqq",query);
                presenter.getGanhuo(query,mTech,0,GanhuoApi.SOUSUO);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getGanhuo("",mTech, mPage, GanhuoApi.JISHU);
        presenter.getGanhuo("","福利", 1, GanhuoApi.SUIJIMEIZI);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mXrlv.setLayoutManager(manager);
        mXrlv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mMyAndroidAdapter = new MyAndroidAdapter(mData, getContext(),mTech);
        mXrlv.setAdapter(mMyAndroidAdapter);
        mXrlv.setLoadingListener(this);

        mMyAndroidAdapter.setOnItemListener(new MyAndroidAdapter.OnItemListener() {
            @Override
            public void OnItemListener(GanAndroid.ResultsBean resultsBean) {
                Intent intent = new Intent(getContext(), WeixinActivity.class);
                intent.putExtra("url", resultsBean.getUrl());
                intent.putExtra("title", resultsBean.getDesc());
                startActivity(intent);
            }
        });
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
    public void show(String s, GanhuoApi ganhuoApi) {
        Gson gson = new Gson();
        switch (ganhuoApi) {
            case JISHU:
                GanAndroid ganAndroid = gson.fromJson(s, GanAndroid.class);
                mMyAndroidAdapter.setData(ganAndroid.getResults(), mPage);
                break;
            case SUIJIMEIZI:
                GanAndroid ganAndroid1 = gson.fromJson(s, GanAndroid.class);
                List<SheZhi> sheZhis = SheZhiku.getInsh().selectAll();
                if (!sheZhis.get(0).getIsWutu()) {
                    Glide.with(getContext()).load(ganAndroid1.getResults().get(0).getUrl()).into(mIvTechOrigin);
                }
                mTvTechCopyright.setText("by:" + ganAndroid1.getResults().get(0).getWho());
                break;
            case SOUSUO:
                GanAndroid ganAndroid2 = gson.fromJson(s, GanAndroid.class);
                mMyAndroidAdapter.setData(ganAndroid2.getResults(),mPage);
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
        mPage = 1;
        presenter.getGanhuo("",mTech, mPage, GanhuoApi.JISHU);
        mXrlv.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        mPage++;
        presenter.getGanhuo("",mTech, mPage, GanhuoApi.JISHU);
        mXrlv.loadMoreComplete();
    }
}
