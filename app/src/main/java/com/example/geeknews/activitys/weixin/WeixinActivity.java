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

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeixinActivity extends SimpleActivity {

    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.toolbat)
    Toolbar mToolbat;
    @BindView(R.id.wv)
    WebView mWv;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");
        mToolbat.setTitle(title);
        setSupportActionBar(mToolbat);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        WebSettings settings = mWv.getSettings();
        settings.setJavaScriptEnabled(true);
        mWv.loadUrl(url);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
