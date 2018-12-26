package com.example.geeknews.fragments.ganhuo;


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
import com.example.geeknews.adapters.ganhuo.MyAndroidAdapter;
import com.example.geeknews.api.GanhuoApi;
import com.example.geeknews.beans.zhihu.ganhuo.GanAndroid;
import com.example.geeknews.beas.fragment.BaseFragment;
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
//        EventBus.getDefault().register(this);
        presenter.getGanhuo(mTech,mPage, GanhuoApi.JISHU);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mXrlv.setLayoutManager(manager);
        mXrlv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        mMyAndroidAdapter = new MyAndroidAdapter(mData,getContext());
        mXrlv.setAdapter(mMyAndroidAdapter);
        mXrlv.setLoadingListener(this);

//        Bundle arguments = getArguments();
//        String meizi = arguments.getString("meizi");
//        Glide.with(getContext()).load(meizi).into(mIvTechBlur);
//        Log.e("77777",ganAndroid);

    }
//    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
//    public void getData(String url){
//        this.ganAndroid = url;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        // 注销订阅者
//        EventBus.getDefault().unregister(this);
//    }

    @Override
    public void showProgressbar() {
        presenter.setShowProgressbar();
    }

    @Override
    public void hideProgressbar() {
        presenter.setHideProgressbar();
    }

    @Override
    public void show(GanAndroid ganAndroid) {
//        Log.e("44444444", ganAndroid.getResults().get(1).getImages().get(0));
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
}
