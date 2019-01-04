package com.example.geeknews.adapters.shuju;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;

import com.example.geeknews.R;
import com.example.geeknews.api.ShujuApi;
import com.example.geeknews.beans.zhihu.shuju.ShujuTitle;
import com.example.geeknews.beas.activity.BaseActivity;
import com.example.geeknews.presenter.ShujuPresenter;
import com.example.geeknews.shujudao.Shujudao;
import com.example.geeknews.utils.DefaultItemTouchHelpCallback;
import com.example.geeknews.view.ShujuView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class ShujuActivity extends BaseActivity<ShujuView<String>, ShujuPresenter<ShujuView<String>>> implements ShujuView<String> {

    private static OnItemListener sListener;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.xrlv)
    RecyclerView mXrlv;
    private ShujuLanAdapter mShujuLanAdapter;
    DefaultItemTouchHelpCallback mCallback;
    private List<String> mResult;


    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        Map<String, Object> map = new HashMap<>();
        map.put("appKey", "4f359e9003324dd0a6cff75e229ebbc3");
        presenter.getShuju("categories", map, ShujuApi.TITLE);

        mToolbar.setTitle("首页特别展示");
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mXrlv.setLayoutManager(manager);
        mXrlv.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        mCallback = new DefaultItemTouchHelpCallback(new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int adapterPosition) {

            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {
                if (mResult != null){
                    Collections.swap(mResult,srcPosition,targetPosition);
                    mShujuLanAdapter.notifyItemMoved(srcPosition,targetPosition);
                    return true;
                }
                return false;
            }
        });
        mCallback.setDragEnable(true);
        mCallback.setSwipeEnable(true);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(mXrlv);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                sListener.OnItemListener();
                finish();

                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected int createLayoutId() {
        return R.layout.activity_shuju;
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getShuju(List<Shujudao> lilits){
        mShujuLanAdapter = new ShujuLanAdapter(lilits, ShujuActivity.this);
        mXrlv.setAdapter(mShujuLanAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void show(String s, ShujuApi shujuApi) {
        Gson gson = new Gson();
        switch (shujuApi) {
            case TITLE:
                ShujuTitle shujuTitle = gson.fromJson(s, ShujuTitle.class);
                mResult = shujuTitle.getRESULT();
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

    public interface OnItemListener{
        void OnItemListener();
    }

    public static void setOnItemListener(OnItemListener listener){
        sListener = listener;
    }
}
