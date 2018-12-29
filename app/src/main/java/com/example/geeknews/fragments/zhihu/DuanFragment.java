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
import com.example.geeknews.adapters.zhihu.MyPIngAdapter;
import com.example.geeknews.api.ZhihuApi;
import com.example.geeknews.beans.zhihu.CommentBean;
import com.example.geeknews.beas.fragment.BaseFragment;
import com.example.geeknews.presenter.ZhihuPresenter;
import com.example.geeknews.view.ZhihuView;
import com.google.gson.Gson;
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
public class DuanFragment extends BaseFragment<ZhihuView<String>, ZhihuPresenter<ZhihuView<String>>> implements ZhihuView<String> {

    @BindView(R.id.xrlv)
    XRecyclerView mXrlv;
    Unbinder unbinder;
    private String mId;
    private MyPIngAdapter mMyPIngAdapter;
    private List<CommentBean.CommentsBean> mData = new ArrayList<>();

    public DuanFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_duan;
    }

    @Override
    protected void load() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void get(String id) {
        Log.e("dddddddd", id + "");
        mId = id;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        presenter.getZhihu("",Integer.valueOf(mId), ZhihuApi.DUANPING);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mXrlv.setLayoutManager(manager);
        mXrlv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        mMyPIngAdapter = new MyPIngAdapter(mData,getContext());
        mXrlv.setAdapter(mMyPIngAdapter);
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
            case DUANPING:
                CommentBean commentBean = gson.fromJson(s, CommentBean.class);
                Log.e("ddddddd",commentBean.getComments().get(1).getAuthor());
                mMyPIngAdapter.setData(commentBean.getComments());
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
