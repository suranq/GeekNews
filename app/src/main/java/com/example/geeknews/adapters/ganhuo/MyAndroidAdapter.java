package com.example.geeknews.adapters.ganhuo;

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
import com.example.geeknews.beans.zhihu.ganhuo.GanAndroid;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by 马明祥 on 2018/12/25.
 */

public class MyAndroidAdapter extends XRecyclerView.Adapter{
    private List<GanAndroid.ResultsBean> mData;
    private final Context mContext;

    public MyAndroidAdapter(List<GanAndroid.ResultsBean> data, Context context) {

        mData = data;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.androiditem, null, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        holder1.mTv1.setText(mData.get(position).getDesc());
        holder1.mTv2.setText(mData.get(position).getWho());
        holder1.mTv3.setText(mData.get(position).getPublishedAt());
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
        private final TextView mTv1;
        private final TextView mTv2;
        private final TextView mTv3;

        public MyViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
            mTv1 = itemView.findViewById(R.id.tv1);
            mTv2 = itemView.findViewById(R.id.tv2);
            mTv3 = itemView.findViewById(R.id.tv3);
        }
    }

    public void setData(List<GanAndroid.ResultsBean> ganAndroid, int page) {
        if (page == 1){
            mData.clear();
        }
        mData.addAll(ganAndroid);
        notifyDataSetChanged();
    }
}
