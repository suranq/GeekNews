package com.example.geeknews.fragments.zhihu;


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
import com.example.geeknews.activitys.weixin.zhihu.ZhihuActivity;
import com.example.geeknews.adapters.zhihu.MyRemenAdapter;
import com.example.geeknews.api.ZhihuApi;
import com.example.geeknews.beans.zhihu.HotListBean;
import com.example.geeknews.beas.fragment.BaseFragment;
import com.example.geeknews.presenter.ZhihuPresenter;
import com.example.geeknews.view.ZhihuView;
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
public class RemenFragment extends BaseFragment<ZhihuView<String>, ZhihuPresenter<ZhihuView<String>>> implements ZhihuView<String> {


    @BindView(R.id.xrlv)
    XRecyclerView mXrlv;
    Unbinder unbinder;
    private MyRemenAdapter mMyRemenAdapter;
    private List<HotListBean.RecentBean> mData = new ArrayList<>();

    public RemenFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_remen;
    }

    @Override
    public void load() {

    }

    @Override
    protected void initData() {
        presenter.getZhihu("",0,ZhihuApi.REMENRIBAO);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mXrlv.setLayoutManager(manager);
        mXrlv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        mMyRemenAdapter = new MyRemenAdapter(mData,getContext());
        mXrlv.setAdapter(mMyRemenAdapter);

        mMyRemenAdapter.setOnItemListener(new MyRemenAdapter.OnItemListener() {
            @Override
            public void OnItemListener(HotListBean.RecentBean recentBean) {
                Intent intent = new Intent(getContext(), ZhihuActivity.class);
                intent.putExtra("xiangqing",recentBean.getNews_id());
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
    public void showZhihu(String s, ZhihuApi zhihuApi) {
        Gson gson = new Gson();
        switch (zhihuApi) {
            case REMENRIBAO:
                HotListBean hotListBean = gson.fromJson(s, HotListBean.class);
                mMyRemenAdapter.setData(hotListBean.getRecent());
                break;
        }
    }

    @Override
    public void showError(String error) {
        Log.e("3333333",error);
    }

    @Override
    protected ZhihuPresenter<ZhihuView<String>> createPresenter() {
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
