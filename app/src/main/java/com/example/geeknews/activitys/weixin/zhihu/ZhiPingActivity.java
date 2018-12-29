package com.example.geeknews.activitys.weixin.zhihu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.geeknews.R;
import com.example.geeknews.adapters.MyFragmentAdapter;
import com.example.geeknews.api.ZhihuApi;
import com.example.geeknews.beans.zhihu.DetailExtraBean;
import com.example.geeknews.beas.activity.BaseActivity;
import com.example.geeknews.beas.activity.SimpleActivity;
import com.example.geeknews.fragments.zhihu.ChangFragment;
import com.example.geeknews.fragments.zhihu.DuanFragment;
import com.example.geeknews.presenter.ZhihuPresenter;
import com.example.geeknews.view.ZhihuView;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhiPingActivity extends SimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private DetailExtraBean mDetailExtraBean;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int zong = intent.getIntExtra("zong", 0);
        int chang = intent.getIntExtra("chang", 0);
        int duan = intent.getIntExtra("duan", 0);

        mToolbar.setTitle(zong+"条评论");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mTab.addTab(mTab.newTab().setText("短评论"+"("+duan+")"));
        mTab.addTab(mTab.newTab().setText("长评论"+"("+chang+")"));
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
        fragments.add(new DuanFragment());
        fragments.add(new ChangFragment());
        mVp.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(),fragments));
    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_zhi_ping;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                startActivity(new Intent(ZhiPingActivity.this,ZhihuActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
