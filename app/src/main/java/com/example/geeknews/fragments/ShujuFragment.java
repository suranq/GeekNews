package com.example.geeknews.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;

import com.example.geeknews.R;
import com.example.geeknews.adapters.MyFragmentAdapter;
import com.example.geeknews.adapters.shuju.ShujuActivity;
import com.example.geeknews.api.ShujuApi;
import com.example.geeknews.beans.zhihu.shuju.SHujuLilit;
import com.example.geeknews.beans.zhihu.shuju.ShujuBig;
import com.example.geeknews.beans.zhihu.shuju.ShujuTitle;
import com.example.geeknews.beas.fragment.BaseFragment;
import com.example.geeknews.fragments.shuju.ShujufuFragment;
import com.example.geeknews.presenter.ShujuPresenter;
import com.example.geeknews.shujudao.ShujuKu;
import com.example.geeknews.shujudao.Shujudao;
import com.example.geeknews.view.ShujuView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShujuFragment extends BaseFragment<ShujuView<String>, ShujuPresenter<ShujuView<String>>> implements ShujuView<String>,ShujuActivity.OnItemListener {

    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.iv_la)
    ImageView mIvLa;
    @BindView(R.id.vpager)
    ViewPager mVpager;
    List<String> mData = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private List<Shujudao> mShujudaos = new ArrayList<>();
    private Map<String, Object> mMap;
    private List<Shujudao> mShujuku;

    public ShujuFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_shuju;
    }

    @Override
    protected void load() {
    }

    @Override
    protected void initData() {
        SharedPreferences preferences = getActivity().getSharedPreferences("sp", 0);
        SharedPreferences.Editor edit = preferences.edit();
        edit.commit();

        SharedPreferences sp = getActivity().getSharedPreferences("sp", 0);
        boolean isss = sp.getBoolean("isss", false);

        if (isss) {
            mShujuku = ShujuKu.getInsh().selectAll();
            EventBus.getDefault().postSticky(mShujuku);
            for (int i = 0; i < mShujuku.size(); i++) {
                if (mShujuku.get(i).getIsShow()) {
                    mTab.addTab(mTab.newTab().setText(mShujuku.get(i).getTitle()));
                    mFragments.add(new ShujufuFragment(mShujuku.get(i).getTitle()));
                }
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
            mVpager.setAdapter(new MyFragmentAdapter(getChildFragmentManager(), mFragments));
        } else {
            mMap = new HashMap<>();
            mMap.put("appKey", "4f359e9003324dd0a6cff75e229ebbc3");
            presenter.getShuju("categories", mMap, ShujuApi.TITLE);
        }
        mData.clear();
        ShujuActivity.setOnItemListener(this);
    }

    @Override
    protected ShujuPresenter<ShujuView<String>> createPresenter() {
        return new ShujuPresenter<>();
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

    @Override
    public void show(String s, ShujuApi shujuApi) {
        Gson gson = new Gson();
        switch (shujuApi) {
            case TITLE:
                ShujuTitle shujuTitle = gson.fromJson(s, ShujuTitle.class);
                Log.e("shujuhusju", shujuTitle.getRESULT().get(1));
                List<String> result = shujuTitle.getRESULT();
                mTab.removeAllTabs();
                SharedPreferences preferences = getActivity().getSharedPreferences("sp", 0);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putBoolean("isss", true);
                edit.commit();
                for (int i = 0; i < result.size(); i++) {
                    mShujudaos.add(new Shujudao(null, result.get(i), true));
                }
                ShujuKu.getInsh().insert(mShujudaos);

                mShujuku = ShujuKu.getInsh().selectAll();
                for (Shujudao item : mShujuku) {
                    if (item.getIsShow()) {
                        mTab.addTab(mTab.newTab().setText(item.getTitle()));
                        mFragments.add(new ShujufuFragment(item.getTitle()));
                    }
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
                mVpager.setAdapter(new MyFragmentAdapter(getChildFragmentManager(), mFragments));
                break;
        }
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void OnItemListener() {
        mShujuku = ShujuKu.getInsh().selectAll();
        mTab.removeAllTabs();
        mFragments.clear();
        for (int i = 0; i < mShujuku.size(); i++) {
            if (mShujuku.get(i).getIsShow()) {
                mTab.addTab(mTab.newTab().setText(mShujuku.get(i).getTitle()));
                mFragments.add(new ShujufuFragment(mShujuku.get(i).getTitle()));
            }
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
        mVpager.setAdapter(new MyFragmentAdapter(getChildFragmentManager(), mFragments));
    }
}
