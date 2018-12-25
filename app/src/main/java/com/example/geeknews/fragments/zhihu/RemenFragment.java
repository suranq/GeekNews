package com.example.geeknews.fragments.zhihu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.adapters.MyRemenAdapter;
import com.example.geeknews.api.ZhihuApi;
import com.example.geeknews.beans.zhihu.HotListBean;
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
public class RemenFragment extends BaseFragment<ZhihuView<HotListBean>, ZhihuPresenter<ZhihuView<HotListBean>>> implements ZhihuView<HotListBean> {


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
    protected void load() {

    }

    @Override
    protected void initData() {
        presenter.getZhihu(ZhihuApi.REMENRIBAO);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mXrlv.setLayoutManager(manager);
        mXrlv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        mMyRemenAdapter = new MyRemenAdapter(mData,getContext());
        mXrlv.setAdapter(mMyRemenAdapter);
    }

    @Override
    public void showZhihu(HotListBean hotListBean) {
        Log.e("3333",hotListBean.getRecent().get(2).getThumbnail());
        mMyRemenAdapter.setData(hotListBean.getRecent());
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
        Log.e("3333333",error);
    }

    @Override
    protected ZhihuPresenter<ZhihuView<HotListBean>> createPresenter() {
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
