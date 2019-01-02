package com.example.geeknews.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.activitys.weixin.zhihu.ZhihuActivity;
import com.example.geeknews.beas.fragment.SimpleFragment;
import com.example.geeknews.greendao.DaoAdapter;
import com.example.geeknews.greendao.DaoNews;
import com.example.geeknews.greendao.GreenDaoHelep;
import com.example.geeknews.utils.DefaultItemTouchHelpCallback;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShouFragment extends SimpleFragment {

    Unbinder unbinder;
    @BindView(R.id.xrlv)
    RecyclerView mXrlv;
    Unbinder unbinder1;
    private DaoAdapter mDaoAdapter;
    DefaultItemTouchHelpCallback mCallback;

    public ShouFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_shou;
    }

    @Override
    protected void load() {

    }

    @Override
    protected void initData() {
        final List<DaoNews> daoNews = GreenDaoHelep.getInsh().selectAll();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mXrlv.setLayoutManager(manager);
        mXrlv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mDaoAdapter = new DaoAdapter(daoNews, getContext());
        mXrlv.setAdapter(mDaoAdapter);

        mDaoAdapter.setOnItemListener(new DaoAdapter.OnItemListener() {
            @Override
            public void OnItemListener(DaoNews daoNews) {
                Intent intent = new Intent(getContext(), ZhihuActivity.class);
                intent.putExtra("xiangqing",daoNews.getIda());
                startActivity(intent);
            }
        });

        mCallback = new DefaultItemTouchHelpCallback(new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int adapterPosition) {
                if (daoNews != null) {
                    DaoNews daoNews1 = daoNews.get(adapterPosition);
                    GreenDaoHelep.getInsh().delect(daoNews1);
                    daoNews.remove(daoNews1);
                    mDaoAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {
                if (daoNews != null) {
                    // 更换数据库中的数据Item的位置
                    boolean isPlus = srcPosition < targetPosition;
                    Collections.swap(daoNews,srcPosition,targetPosition);
                    mDaoAdapter.notifyItemMoved(srcPosition,targetPosition);
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
}
