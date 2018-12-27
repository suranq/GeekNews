package com.example.geeknews.activitys.weixin.zhihu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geeknews.R;
import com.example.geeknews.api.ZhihuApi;
import com.example.geeknews.beans.zhihu.ZhihuDetailBean;
import com.example.geeknews.beas.activity.BaseActivity;
import com.example.geeknews.presenter.ZhihuPresenter;
import com.example.geeknews.view.ZhihuView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhihuActivity extends BaseActivity<ZhihuView<String>, ZhihuPresenter<ZhihuView<String>>> implements ZhihuView<String> {

    @BindView(R.id.iv_hop)
    ImageView mIvHop;
    @BindView(R.id.detail_bar_copyright)
    TextView mDetailBarCopyright;
    @BindView(R.id.view_toolbar)
    Toolbar mViewToolbar;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout mClpToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.view_main)
    WebView mViewMain;
    @BindView(R.id.nsv_scroller)
    NestedScrollView mNsvScroller;
    @BindView(R.id.tv_detail_bottom_like)
    TextView mTvDetailBottomLike;
    @BindView(R.id.tv_detail_bottom_comment)
    TextView mTvDetailBottomComment;
    @BindView(R.id.tv_detail_bottom_share)
    TextView mTvDetailBottomShare;
    @BindView(R.id.ll_detail_bottom)
    FrameLayout mLlDetailBottom;
    @BindView(R.id.fab_like)
    FloatingActionButton mFabLike;

    private boolean isBottomShow = true;
    private ZhihuDetailBean mZhihuDetailBean;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int xiangqing = intent.getIntExtra("xiangqing", 0);
        Log.e("4444444444", xiangqing + "");
        presenter.getZhihu("",xiangqing, ZhihuApi.RIBAOXIANGQING);
    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_zhihu;
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
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showZhihu(String s, ZhihuApi zhihuApi) {
        Gson gson = new Gson();
        switch (zhihuApi) {
            case RIBAOXIANGQING:
                mZhihuDetailBean = gson.fromJson(s, ZhihuDetailBean.class);

                EventBus.getDefault().postSticky(mZhihuDetailBean.getId()+"");
                WebSettings settings = mViewMain.getSettings();
                settings.setJavaScriptEnabled(true);
                mViewMain.loadUrl(mZhihuDetailBean.getShare_url());

                mViewToolbar.setTitle(mZhihuDetailBean.getTitle());
                setSupportActionBar(mViewToolbar);
                ActionBar actionBar = getSupportActionBar();
                actionBar.setDisplayHomeAsUpEnabled(true);

                mNsvScroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        if (scrollY - oldScrollY > 0 && isBottomShow) {//下移隐藏
                            isBottomShow = false;
                            mLlDetailBottom.animate().translationY(mLlDetailBottom.getHeight());
                        } else if (scrollY - oldScrollY < 0 && !isBottomShow) {
                            isBottomShow = true;
                            mLlDetailBottom.animate().translationY(0);
                        }
                    }
                });

                mTvDetailBottomComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ZhihuActivity.this, ZhiPingActivity.class);
                        intent.putExtra("id", mZhihuDetailBean.getId());
                        startActivity(intent);
                    }
                });
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
