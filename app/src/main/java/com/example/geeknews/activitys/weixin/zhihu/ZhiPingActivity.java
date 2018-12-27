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
import com.example.geeknews.fragments.zhihu.ChangFragment;
import com.example.geeknews.fragments.zhihu.DuanFragment;
import com.example.geeknews.presenter.ZhihuPresenter;
import com.example.geeknews.view.ZhihuView;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhiPingActivity extends BaseActivity<ZhihuView<String>, ZhihuPresenter<ZhihuView<String>>> implements ZhihuView<String> {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private DetailExtraBean mDetailExtraBean;

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", 0);
        presenter.getZhihu("",id, ZhihuApi.EWAIXINXI);


    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_zhi_ping;
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

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

    @Override
    public void showZhihu(String s, ZhihuApi zhihuApi) {
        Gson gson = new Gson();
        switch (zhihuApi) {
            case EWAIXINXI:
                DetailExtraBean detailExtraBean = gson.fromJson(s, DetailExtraBean.class);
                mToolbar.setTitle(detailExtraBean.getComments()+"条评论");
                setSupportActionBar(mToolbar);
                ActionBar actionBar = getSupportActionBar();
                actionBar.setDisplayHomeAsUpEnabled(true);

                mTab.addTab(mTab.newTab().setText("短评论"+"("+detailExtraBean.getShort_comments()+")"));
                mTab.addTab(mTab.newTab().setText("长评论"+"("+detailExtraBean.getLong_comments()+")"));
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
