package com.example.geeknews.adapters.shuju;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.geeknews.R;
import com.example.geeknews.api.ShujuApi;
import com.example.geeknews.beans.zhihu.shuju.ShujuTitle;
import com.example.geeknews.beas.activity.BaseActivity;
import com.example.geeknews.presenter.ShujuPresenter;
import com.example.geeknews.view.ShujuView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShujuActivity extends BaseActivity<ShujuView<String>, ShujuPresenter<ShujuView<String>>> implements ShujuView<String> {

    @BindView(R.id.xrlv)
    XRecyclerView mXrlv;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void initData() {
        Map<String, Object> map = new HashMap<>();
        map.put("appKey", "4f359e9003324dd0a6cff75e229ebbc3");
        presenter.getShuju("categories", map, ShujuApi.TITLE);

        mToolbar.setTitle("首页特别展示");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();

    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_shuju;
    }

    @Override
    public void show(String s, ShujuApi shujuApi) {
        Gson gson = new Gson();
        switch (shujuApi) {
            case TITLE:
                ShujuTitle shujuTitle = gson.fromJson(s, ShujuTitle.class);
                Log.e("sssssss", shujuTitle.getRESULT().get(1));
                break;
        }
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected ShujuPresenter<ShujuView<String>> createPresenter() {
        return new ShujuPresenter<>();
    }
}
