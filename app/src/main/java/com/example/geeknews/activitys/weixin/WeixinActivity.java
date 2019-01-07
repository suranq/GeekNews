package com.example.geeknews.activitys.weixin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.geeknews.R;
import com.example.geeknews.activitys.weixin.zhihu.ZhihuActivity;
import com.example.geeknews.beas.activity.SimpleActivity;
import com.example.geeknews.greendao.DaoNews;
import com.example.geeknews.greendao.GreenDaoHelep;
import com.example.geeknews.utils.ShareUtil;
import com.example.geeknews.utils.SystemUtil;

import java.util.List;

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
    private String From = "微信";

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

        List<DaoNews> daoNews = GreenDaoHelep.getInsh().selectS(mTitle);
        for (int i = 0; i < daoNews.size(); i++) {
            mIv.setSelected(daoNews.get(i).getIsShow());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,R.id.pager_image1,0,"复制链接到剪贴板");
        menu.add(0,R.id.pager_image2,0,"分享链接");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.pager_image1:
                SystemUtil.copyToClipBoard(WeixinActivity.this,mUrl);
                break;
            case R.id.pager_image2:
                ShareUtil.shareText(WeixinActivity.this,mUrl,mTitle);
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
        if (mIv.isSelected()) {
            mIv.setSelected(false);
            List<DaoNews> daoNews = GreenDaoHelep.getInsh().selectS(mTitle);
            for (int i = 0; i < daoNews.size(); i++) {
                GreenDaoHelep.getInsh().delect(daoNews.get(i));
            }
        } else {
            mIv.setSelected(true);
            DaoNews daoNews = new DaoNews(null, 0, mImage, mTitle, mUrl, 0, null, true,From);
            GreenDaoHelep.getInsh().insert(daoNews);
        }
    }
}
