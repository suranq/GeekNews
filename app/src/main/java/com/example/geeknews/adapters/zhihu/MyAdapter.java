package com.example.geeknews.adapters.zhihu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.beans.zhihu.DailyBeforeListBean;
import com.example.geeknews.beans.zhihu.DailyListBean;
import com.example.geeknews.beans.zhihu.SectionChildListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by 马明祥 on 2018/12/28.
 */

public class MyAdapter extends XRecyclerView.Adapter{

    private List<SectionChildListBean.StoriesBean> mBeans;
    private final Context mContext;
    private DailyBeforeListBean mData;

    public MyAdapter(List<SectionChildListBean.StoriesBean> beans, Context context) {

        mBeans = beans;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.ribaotem, null, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
//        Glide.with(mContext).load(m)
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends XRecyclerView.ViewHolder {

        private final ImageView mIv;
        private final TextView mTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
            mTv = itemView.findViewById(R.id.tv);
        }
    }

    public void setData(List<DailyListBean.StoriesBean> data) {
//        mBeans = data;
        notifyDataSetChanged();
    }
}
