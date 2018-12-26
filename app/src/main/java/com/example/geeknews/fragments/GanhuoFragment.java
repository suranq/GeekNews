package com.example.geeknews.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.adapters.MyFragmentAdapter;
import com.example.geeknews.api.GanhuoApi;
import com.example.geeknews.beans.zhihu.ganhuo.GanAndroid;
import com.example.geeknews.beas.fragment.BaseFragment;
import com.example.geeknews.beas.fragment.SimpleFragment;
import com.example.geeknews.fragments.ganhuo.AndroidFragment;
import com.example.geeknews.fragments.ganhuo.FuliFragment;
import com.example.geeknews.fragments.ganhuo.IOSFragment;
import com.example.geeknews.fragments.ganhuo.QianduanFragment;
import com.example.geeknews.presenter.GanhuoPresenter;
import com.example.geeknews.view.GanhuoView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GanhuoFragment extends BaseFragment<GanhuoView<GanAndroid>,GanhuoPresenter<GanhuoView<GanAndroid>>> implements GanhuoView<GanAndroid>{


    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    Unbinder unbinder;

    public GanhuoFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_ganhuo;
    }

    @Override
    protected void load() {
        presenter.getGanhuo("福利",1, GanhuoApi.JISHU);
    }

    @Override
    protected void initData() {
        mTab.addTab(mTab.newTab().setText("ANDROID"));
        mTab.addTab(mTab.newTab().setText("IOS"));
        mTab.addTab(mTab.newTab().setText("前端"));
        mTab.addTab(mTab.newTab().setText("福利"));
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new AndroidFragment());
        fragments.add(new IOSFragment());
        fragments.add(new QianduanFragment());
        fragments.add(new FuliFragment());
        mVp.setAdapter(new MyFragmentAdapter(getChildFragmentManager(),fragments));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected GanhuoPresenter<GanhuoView<GanAndroid>> createPresenter() {
        return new GanhuoPresenter<>();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void show(GanAndroid ganAndroid) {
        Log.e("666666",ganAndroid.getResults().get(0).getUrl());
    }

    @Override
    public void showError(String error) {

    }
}
