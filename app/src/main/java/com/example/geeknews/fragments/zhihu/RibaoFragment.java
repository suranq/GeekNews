package com.example.geeknews.fragments.zhihu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.adapters.MyXrlvAdapter;
import com.example.geeknews.api.ZhihuApi;
import com.example.geeknews.beans.zhihu.DailyListBean;
import com.example.geeknews.beas.fragment.BaseFragment;
import com.example.geeknews.presenter.ZhihuPresenter;
import com.example.geeknews.view.ZhihuView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RibaoFragment extends BaseFragment<ZhihuView<DailyListBean>, ZhihuPresenter<ZhihuView<DailyListBean>>> implements ZhihuView<DailyListBean> {


    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.xrlv)
    XRecyclerView mXrlv;
    Unbinder unbinder;

    private List<DailyListBean.TopStoriesBean> mData = new ArrayList<>();
    private MyXrlvAdapter mMyXrlvAdapter;

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
        presenter.getZhihu(ZhihuApi.ZUIXINRIBAO);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mXrlv.setLayoutManager(manager);
        mXrlv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        mMyXrlvAdapter = new MyXrlvAdapter(mData,getContext());

    }

    @Override
    public void showZhihu(DailyListBean dailyListBean) {
        Log.e("111111111", dailyListBean.getStories().get(1).getTitle());
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected ZhihuPresenter<ZhihuView<DailyListBean>> createPresenter() {
        return new ZhihuPresenter<>();
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
}
