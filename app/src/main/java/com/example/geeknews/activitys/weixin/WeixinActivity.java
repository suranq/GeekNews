package com.example.geeknews.activitys.weixin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.geeknews.R;
import com.example.geeknews.beas.activity.SimpleActivity;
import com.example.geeknews.greendao.DaoNews;
import com.example.geeknews.greendao.GreenDaoHelep;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeixinActivity extends SimpleActivity {

    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.toolbat)
    Toolbar mToolbat;
    @BindView(R.id.wv)
    WebView mWv;
    private String mTitle;
    private String mUrl;
    private String mImage;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        mUrl = intent.getStringExtra("url");
        mImage = intent.getStringExtra("image");
        mToolbat.setTitle(mTitle);
        setSupportActionBar(mToolbat);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        WebSettings settings = mWv.getSettings();
        settings.setJavaScriptEnabled(true);
        mWv.loadUrl(mUrl);
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
        return R.layout.activity_weixin;
    }

    @OnClick(R.id.iv)
    public void onViewClicked() {
        DaoNews daoNews = new DaoNews(null, 0, mImage, mTitle, mUrl, 0, null);
        if (mIv.isSelected()){
            mIv.setSelected(false);
            GreenDaoHelep.getInsh().delect(daoNews);
        }else {
            mIv.setSelected(true);
            GreenDaoHelep.getInsh().insert(daoNews);
        }
    }
}
