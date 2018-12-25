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
import com.example.geeknews.beans.zhihu.HotListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by 马明祥 on 2018/12/24.
 */

public class MyRemenAdapter extends XRecyclerView.Adapter{
    private List<HotListBean.RecentBean> mData;
    private final Context mContext;

    public MyRemenAdapter(List<HotListBean.RecentBean> data, Context context) {

        mData = data;
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
        Glide.with(mContext).load(mData.get(position).getThumbnail()).into(holder1.mIv);
        holder1.mTv.setText(mData.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if (mData == null){
            return 0;
        }
        return mData.size();
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

    public void setData(List<HotListBean.RecentBean> data) {
        if (mData != null){
            mData.clear();
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }
}
