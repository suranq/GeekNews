package com.example.geeknews.activitys.weixin.zhihu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.geeknews.R;
import com.example.geeknews.adapters.zhihu.MyZhuanLanXiangAdapter;
import com.example.geeknews.api.ZhihuApi;
import com.example.geeknews.beans.zhihu.SectionChildListBean;
import com.example.geeknews.beas.activity.BaseActivity;
import com.example.geeknews.presenter.ZhihuPresenter;
import com.example.geeknews.view.ZhihuView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhuanlanActivity extends BaseActivity<ZhihuView<String>, ZhihuPresenter<ZhihuView<String>>> implements ZhihuView<String> {

    @BindView(R.id.xrlv)
    XRecyclerView mXrlv;
    @BindView(R.id.tab)
    Toolbar mTab;
    private MyZhuanLanXiangAdapter mMyZhuanLanXiangAdapter;
    private List<SectionChildListBean.StoriesBean> mData = new ArrayList<>();

    @Override
    protected void initData() {
        final Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        presenter.getZhihu("",id, ZhihuApi.ZHUANLANRIBAOXIANGQING);

        mTab.setTitle("GeekNews");
        setSupportActionBar(mTab);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mXrlv.setLayoutManager(manager);
        mXrlv.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        mMyZhuanLanXiangAdapter = new MyZhuanLanXiangAdapter(mData, this);
        mXrlv.setAdapter(mMyZhuanLanXiangAdapter);

        mMyZhuanLanXiangAdapter.setOnItemListener(new MyZhuanLanXiangAdapter.OnItemListener() {
            @Override
            public void OnItemListener(SectionChildListBean.StoriesBean storiesBean) {
                Intent intent1 = new Intent(ZhuanlanActivity.this, ZhihuActivity.class);
                intent1.putExtra("xiangqing",storiesBean.getId());
                startActivity(intent1);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected int createLayoutId() {
        return R.layout.activity_zhuanlan;
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
            case ZHUANLANRIBAOXIANGQING:
                SectionChildListBean sectionChildListBean = gson.fromJson(s, SectionChildListBean.class);
                mMyZhuanLanXiangAdapter.setData(sectionChildListBean.getStories());
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
