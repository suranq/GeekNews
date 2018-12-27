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
import com.example.geeknews.api.ZhihuApi;
import com.example.geeknews.beans.zhihu.DailyBeforeListBean;
import com.example.geeknews.beas.fragment.BaseFragment;
import com.example.geeknews.beas.fragment.SimpleFragment;
import com.example.geeknews.fragments.zhihu.RemenFragment;
import com.example.geeknews.fragments.zhihu.RibaoFragment;
import com.example.geeknews.fragments.zhihu.ZhuanlanFragment;
import com.example.geeknews.fragments.zhihu.ZhutiFragment;
import com.example.geeknews.presenter.ZhihuPresenter;
import com.example.geeknews.view.ZhihuView;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhihuFragment extends SimpleFragment{


    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    Unbinder unbinder;

    public ZhihuFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void load() {

    }

    @Override
    protected void initData() {

        mTab.addTab(mTab.newTab().setText("日报"));
        mTab.addTab(mTab.newTab().setText("主题"));
        mTab.addTab(mTab.newTab().setText("专栏"));
        mTab.addTab(mTab.newTab().setText("热门"));

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
        fragments.add(new RibaoFragment());
        fragments.add(new ZhutiFragment());
        fragments.add(new ZhuanlanFragment());
        fragments.add(new RemenFragment());
        mVp.setAdapter(new MyFragmentAdapter(getChildFragmentManager(),fragments));
    }

}
