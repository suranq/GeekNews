package com.example.geeknews.adapters.shuju;

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
import com.example.geeknews.beans.zhihu.shuju.ShujuData;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by 马明祥 on 2019/1/1.
 */

public class ShujuXiangAdapter extends XRecyclerView.Adapter{
    private List<ShujuData.RESULTBean.NewsListBean> mData;
    private final Context mContext;

    public ShujuXiangAdapter(List<ShujuData.RESULTBean.NewsListBean> data, Context context) {

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
        if (mData.get(position).getNewsImg().contains("https")){
            Glide.with(mContext).load(mData.get(position).getNewsImg()).into(holder1.mIv);
        }else {
            Glide.with(mContext).load("https"+mData.get(position).getNewsImg()).into(holder1.mIv);
        }
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

        private final TextView mTv;
        private final ImageView mIv;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv);
            mIv = itemView.findViewById(R.id.iv);
        }
    }

    public void setData(List<ShujuData.RESULTBean.NewsListBean> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
