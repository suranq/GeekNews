package com.example.geeknews.fragments.zhihu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.activitys.weixin.zhihu.ZhuanlanActivity;
import com.example.geeknews.adapters.zhihu.MyZhuanLanAdapter;
import com.example.geeknews.api.ZhihuApi;
import com.example.geeknews.beans.zhihu.SectionListBean;
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
public class ZhuanlanFragment extends BaseFragment<ZhihuView<String>, ZhihuPresenter<ZhihuView<String>>> implements ZhihuView<String> {


    @BindView(R.id.xrlv)
    XRecyclerView mXrlv;
    Unbinder unbinder;
    private MyZhuanLanAdapter mMyZhuanLanAdapter;
    private List<SectionListBean.DataBean> mData = new ArrayList<>();

    public ZhuanlanFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_zhuanlan;
    }

    @Override
    protected void load() {

    }

    @Override
    protected void initData() {
        presenter.getZhihu("",0,ZhihuApi.ZHUANLANRIBAO);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mXrlv.setLayoutManager(manager);
        mXrlv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        mMyZhuanLanAdapter = new MyZhuanLanAdapter(mData,getContext());
        mXrlv.setAdapter(mMyZhuanLanAdapter);

        mMyZhuanLanAdapter.setOnItemListener(new MyZhuanLanAdapter.OnItemListener() {
            @Override
            public void OnItemListener(SectionListBean.DataBean dataBean) {
                Intent intent = new Intent(getContext(), ZhuanlanActivity.class);
                intent.putExtra("id",dataBean.getId());
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
    public void showZhihu(String s, ZhihuApi zhihuApi) {
        Gson gson = new Gson();
        switch (zhihuApi) {
            case ZHUANLANRIBAO:
                SectionListBean sectionListBean = gson.fromJson(s, SectionListBean.class);
                mMyZhuanLanAdapter.setData(sectionListBean.getData());
                break;
        }
    }

    @Override
    public void showError(String error) {
        Log.e("error",error);
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
