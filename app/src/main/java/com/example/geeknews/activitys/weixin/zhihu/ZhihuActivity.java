package com.example.geeknews.activitys.weixin.zhihu;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.api.ZhihuApi;
import com.example.geeknews.beans.zhihu.DetailExtraBean;
import com.example.geeknews.beans.zhihu.ZhihuDetailBean;
import com.example.geeknews.beas.activity.BaseActivity;
import com.example.geeknews.greendao.DaoNews;
import com.example.geeknews.greendao.GreenDaoHelep;
import com.example.geeknews.presenter.ZhihuPresenter;
import com.example.geeknews.view.ZhihuView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhihuActivity extends BaseActivity<ZhihuView<String>, ZhihuPresenter<ZhihuView<String>>> implements ZhihuView<String> {

    private static OnItemListener sListener;
    @BindView(R.id.detail_bar_copyright)
    TextView mDetailBarCopyright;
    @BindView(R.id.view_toolbar)
    Toolbar mViewToolbar;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout mClpToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.view_main)
    TextView mViewMain;
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
    @BindView(R.id.detail_bar_image)
    ImageView mDetailBarImage;

    private boolean isBottomShow = true;
    private ZhihuDetailBean mZhihuDetailBean;
    private boolean isShow;
    private int mXiangqing;
    private String From = "知乎";

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mXiangqing = intent.getIntExtra("xiangqing", 0);
        Log.e("4444444444", mXiangqing + "");

        List<DaoNews> daoNews = GreenDaoHelep.getInsh().selectId(mXiangqing + "");
        for (int i = 0; i < daoNews.size(); i++) {
            mFabLike.setSelected(daoNews.get(i).getIsShow());
        }

        presenter.getZhihu("", mXiangqing, ZhihuApi.RIBAOXIANGQING);

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
        presenter.getZhihu("", mXiangqing, ZhihuApi.EWAIXINXI);

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
                if (sListener != null) {
                    sListener.OnItemListener();
                }
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

                EventBus.getDefault().postSticky(mZhihuDetailBean.getId() + "");
                Glide.with(this).load(mZhihuDetailBean.getImage()).into(mDetailBarImage);

                mViewToolbar.setTitle(mZhihuDetailBean.getTitle());
                setSupportActionBar(mViewToolbar);
                ActionBar actionBar = getSupportActionBar();
                actionBar.setDisplayHomeAsUpEnabled(true);

                get(mZhihuDetailBean);
                break;
            case EWAIXINXI:
                final DetailExtraBean detailExtraBean = gson.fromJson(s, DetailExtraBean.class);
                Log.e("zzzzzzzzzzzz", detailExtraBean.getPopularity() + "");
                mTvDetailBottomLike.setText(detailExtraBean.getPopularity() + "");
                mTvDetailBottomComment.setText(detailExtraBean.getComments() + "");
                mTvDetailBottomComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ZhihuActivity.this, ZhiPingActivity.class);
                        intent.putExtra("zong", detailExtraBean.getComments());
                        intent.putExtra("chang", detailExtraBean.getLong_comments());
                        intent.putExtra("duan", detailExtraBean.getShort_comments());
                        startActivity(intent);
                    }
                });
                break;
        }
    }

    public void get(final ZhihuDetailBean zhihuDetailBean) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Html.ImageGetter imageGetter = new Html.ImageGetter() {
                    private URL mUrl;

                    @Override
                    public Drawable getDrawable(String source) {
                        Drawable drawable = null;
                        try {
                            mUrl = new URL(source);
                            drawable = Drawable.createFromStream(mUrl.openStream(), "jpg");

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                            return null;
                        }
                        drawable.setBounds(0, 0, 200, 200);
                        return drawable;
                    }
                };
                final CharSequence spanned = Html.fromHtml(zhihuDetailBean.getBody(), imageGetter, null);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mViewMain.setText(spanned);
                    }
                });
            }
        }).start();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected ZhihuPresenter<ZhihuView<String>> createPresenter() {
        return new ZhihuPresenter<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    @OnClick(R.id.fab_like)
    public void onViewClicked() {
        if (mFabLike.isSelected()) {
            mFabLike.setSelected(false);
            List<DaoNews> daoNews = GreenDaoHelep.getInsh().selectId(mXiangqing + "");
            for (int i = 0; i < daoNews.size(); i++) {
                GreenDaoHelep.getInsh().delect(daoNews.get(i));
            }
        } else {
            DaoNews daoNews = new DaoNews(null, mZhihuDetailBean.getId(), mZhihuDetailBean.getImage(), mZhihuDetailBean.getTitle(), mZhihuDetailBean.getShare_url(), 0, null, true,From);
            mFabLike.setSelected(true);
            GreenDaoHelep.getInsh().insert(daoNews);
        }
    }

    public interface OnItemListener {
        void OnItemListener();
    }

    public static void setOnItemListener(OnItemListener listener) {
        sListener = listener;
    }
}
