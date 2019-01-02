package com.example.geeknews.fragments;


import android.content.Intent;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;

import com.example.geeknews.R;
import com.example.geeknews.adapters.MyFragmentAdapter;
import com.example.geeknews.adapters.shuju.ShujuActivity;
import com.example.geeknews.beas.fragment.SimpleFragment;
import com.example.geeknews.fragments.shuju.ShujufuFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShujuFragment extends SimpleFragment {


    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.iv_la)
    ImageView mIvLa;
    @BindView(R.id.vpager)
    ViewPager mVpager;
    Unbinder unbinder;
    List<String> mData = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private List<String> mShuju;
    private List<String> mResult;
    private boolean isShow = true;
    private Map<String, Object> mMap;

    public ShujuFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_shuju;
    }

    @Override
    protected void load() {
        mMap = new HashMap<>();
        mMap.put("appKey", "4f359e9003324dd0a6cff75e229ebbc3");
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        mData.clear();
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getshuju(List<String> shuju){
        mTab.removeAllTabs();
        mShuju = shuju;
        for (String item : shuju){
            Log.e("iiiiii",item);
            mTab.addTab(mTab.newTab().setText(item));
            mFragments.add(new ShujufuFragment(item));
        }
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mVpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));
        mVpager.setAdapter(new MyFragmentAdapter(getChildFragmentManager(),mFragments));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.iv_la)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), ShujuActivity.class);
        startActivity(intent);
    }
}
