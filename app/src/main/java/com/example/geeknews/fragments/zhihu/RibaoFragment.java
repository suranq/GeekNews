package com.example.geeknews.fragments.zhihu;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.adapters.zhihu.MyXrlvAdapter;
import com.example.geeknews.api.ZhihuApi;
import com.example.geeknews.beans.zhihu.DailyListBean;
import com.example.geeknews.beas.fragment.BaseFragment;
import com.example.geeknews.presenter.ZhihuPresenter;
import com.example.geeknews.view.ZhihuView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RibaoFragment extends BaseFragment<ZhihuView<DailyListBean>, ZhihuPresenter<ZhihuView<DailyListBean>>> implements ZhihuView<DailyListBean> {

    @BindView(R.id.xrlv)
    XRecyclerView mXrlv;
    Unbinder unbinder;
//    @BindView(R.id.banner)
//    Banner mBanner;

    private List<DailyListBean.TopStoriesBean> mData = new ArrayList<>();
    private MyXrlvAdapter mMyXrlvAdapter;
    private DailyListBean mDailyListBean;

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
        mXrlv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mMyXrlvAdapter = new MyXrlvAdapter(mData, getContext());
        mXrlv.setAdapter(mMyXrlvAdapter);

    }

    public class ImageLoader extends com.youth.banner.loader.ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    @Override
    public void showZhihu(DailyListBean dailyListBean) {
        Log.e("111111111", dailyListBean.getStories().get(1).getTitle());
        mDailyListBean = dailyListBean;
        mMyXrlvAdapter.setData(dailyListBean);

//        ArrayList<String> iamge = new ArrayList<>();
//        List<DailyListBean.StoriesBean> top_stories = mDailyListBean.getStories();
//        for (int i = 0; i < top_stories.size(); i++) {
//            List<String> images = top_stories.get(i).getImages();
//            String s = images.get(0);
//            iamge.add(s);
//        }
//
//        mBanner.setImages(iamge).setImageLoader(new ImageLoader()).start();
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
