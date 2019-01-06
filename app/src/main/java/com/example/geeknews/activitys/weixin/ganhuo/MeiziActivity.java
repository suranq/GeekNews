package com.example.geeknews.activitys.weixin.ganhuo;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.geeknews.R;
import com.example.geeknews.beas.activity.SimpleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeiziActivity extends SimpleActivity {

    @BindView(R.id.wv)
    WebView mWv;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String meinv = intent.getStringExtra("meinv");

        WebSettings settings = mWv.getSettings();
        settings.setJavaScriptEnabled(true);
        mWv.loadUrl(meinv);
    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_meizi;
    }

}
