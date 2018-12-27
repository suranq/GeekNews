package com.example.geeknews.fragments.zhihu;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.activitys.weixin.zhihu.ZhihuActivity;
import com.example.geeknews.activitys.weixin.zhihu.ZhihuRiliActivity;
import com.example.geeknews.adapters.zhihu.MyXrlvAdapter;
import com.example.geeknews.api.ZhihuApi;
import com.example.geeknews.beans.zhihu.DailyBeforeListBean;
import com.example.geeknews.beans.zhihu.DailyListBean;
import com.example.geeknews.beas.fragment.BaseFragment;
import com.example.geeknews.presenter.ZhihuPresenter;
import com.example.geeknews.utils.CircularAnimUtil;
import com.example.geeknews.view.ZhihuView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RibaoFragment extends BaseFragment<ZhihuView<String>, ZhihuPresenter<ZhihuView<String>>> implements ZhihuView<String> {

    @BindView(R.id.xrlv)
    XRecyclerView mXrlv;
    Unbinder unbinder;
    @BindView(R.id.fab_calender)
    FloatingActionButton mFabCalender;
    Unbinder unbinder1;
//    @BindView(R.id.banner)
//    Banner mBanner;

    private List<DailyListBean.TopStoriesBean> mData = new ArrayList<>();
    private MyXrlvAdapter mMyXrlvAdapter;
    private List<DailyListBean.StoriesBean> mStoriesBeans = new ArrayList<>();
    private List<DailyListBean.StoriesBean> mStories;
    private int mYear;
    private int mYue;
    private int mDay;
    private String mDate;

    public RibaoFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_ribao;
    }

    @Override
    protected void load() {

    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        presenter.getZhihu("",0, ZhihuApi.ZUIXINRIBAO);


//        Log.e("sssssssssssss",data);
//        presenter.getZhihu(data,0,ZhihuApi.WANGQIRIBAO);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mXrlv.setLayoutManager(manager);
        mXrlv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mMyXrlvAdapter = new MyXrlvAdapter(mData, getContext());
        mXrlv.setAdapter(mMyXrlvAdapter);

        mMyXrlvAdapter.setOnItemListener(new MyXrlvAdapter.OnItemListener() {
            @Override
            public void OnItemListener(DailyListBean.StoriesBean storiesBean, int position) {
                Log.e("22222", storiesBean.getId() + "");
                Intent intent = new Intent(getContext(), ZhihuActivity.class);
                intent.putExtra("xiangqing", storiesBean.getId());
                startActivity(intent);
            }
        });

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getRiqi(CalendarDay data){
        mYear = data.getYear();
        int month = data.getMonth();
        mYue = month+1;
        mDay = data.getDay();
        String dataa = mYear+""+mYue+""+mDay;
        if (data.getDate().equals(mDate)){
            mXrlv.setVisibility(View.VISIBLE);
            mFabCalender.setVisibility(View.GONE);
        }else {
            presenter.getZhihu(dataa,0,ZhihuApi.WANGQIRIBAO);
        }

    }


    @OnClick(R.id.fab_calender)
    void startCalender() {
        Intent intent = new Intent();
        intent.setClass(getContext(), ZhihuRiliActivity.class);
        CircularAnimUtil.startActivity(getActivity(),intent,mFabCalender,R.color.fab_bg);
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
    public void showZhihu(String s,ZhihuApi zhihuApi) {
        Gson gson = new Gson();
        switch (zhihuApi) {
            case ZUIXINRIBAO:
                DailyListBean dailyListBean = gson.fromJson(s, DailyListBean.class);
                mDate = dailyListBean.getDate();
                Log.e("vvvvvvv",dailyListBean.getStories().get(1).getTitle());
                mStoriesBeans.addAll(dailyListBean.getStories());
                mStories = dailyListBean.getStories();
                mMyXrlvAdapter.setData(dailyListBean);
                break;
            case WANGQIRIBAO:
                DailyBeforeListBean dailyBeforeListBean = gson.fromJson(s, DailyBeforeListBean.class);
                Log.e("wwwwwwwww",dailyBeforeListBean.getStories().get(1).getTitle());
                mMyXrlvAdapter.setWang(dailyBeforeListBean);
                break;
        }

    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected ZhihuPresenter<ZhihuView<String>> createPresenter() {
        return new ZhihuPresenter<>();
    }
}
