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
import com.example.geeknews.R;
import com.example.geeknews.activitys.weixin.WeixinActivity;
import com.example.geeknews.adapters.ganhuo.MyAndroidAdapter;
import com.example.geeknews.api.GanhuoApi;
import com.example.geeknews.beans.zhihu.ganhuo.GanAndroid;
import com.example.geeknews.beas.fragment.BaseFragment;
import com.example.geeknews.fragments.GanhuoFragment;
import com.example.geeknews.presenter.GanhuoPresenter;
import com.example.geeknews.view.GanhuoView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AndroidFragment extends BaseFragment<GanhuoView<GanAndroid>, GanhuoPresenter<GanhuoView<GanAndroid>>> implements GanhuoView<GanAndroid>, XRecyclerView.LoadingListener {


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
    private String mTech = "Android";
    private List<GanAndroid.ResultsBean> mData = new ArrayList<>();
    private String ganAndroid;
    private boolean isMeizi = false;
    private String Url;

    public AndroidFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_android;
    }

    @Override
    protected void load() {

    }

    @Override
    protected void initData() {

        presenter.getGanhuo(mTech, mPage, GanhuoApi.JISHU);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mXrlv.setLayoutManager(manager);
        mXrlv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mMyAndroidAdapter = new MyAndroidAdapter(mData, getContext());
        mXrlv.setAdapter(mMyAndroidAdapter);
        mXrlv.setLoadingListener(this);

        Glide.with(getContext()).load(GanhuoFragment.mUrl).into(mIvTechOrigin);

        mMyAndroidAdapter.setOnItemListener(new MyAndroidAdapter.OnItemListener() {
            @Override
            public void OnItemListener(GanAndroid.ResultsBean resultsBean) {
                Intent intent = new Intent(getContext(), WeixinActivity.class);
                intent.putExtra("url",resultsBean.getUrl());
                intent.putExtra("title",resultsBean.getDesc());
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
    public void show(GanAndroid ganAndroid) {
        Log.e("44444444",ganAndroid.getResults().get(1).getUrl());
        mMyAndroidAdapter.setData(ganAndroid.getResults(),mPage);

    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected GanhuoPresenter<GanhuoView<GanAndroid>> createPresenter() {
        return new GanhuoPresenter<>();
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        presenter.getGanhuo(mTech,mPage,GanhuoApi.JISHU);
        mXrlv.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        mPage++;
        presenter.getGanhuo(mTech,mPage,GanhuoApi.JISHU);
        mXrlv.loadMoreComplete();
    }

    public void setData(String data) {
        Url = data;
//        Glide.with(getContext()).load(data).into(mIvTechOrigin);
    }
}
