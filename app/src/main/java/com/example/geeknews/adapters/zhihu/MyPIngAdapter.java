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
import com.bumptech.glide.request.RequestOptions;
import com.example.geeknews.R;
import com.example.geeknews.beans.zhihu.CommentBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by 马明祥 on 2018/12/27.
 */

public class MyPIngAdapter extends XRecyclerView.Adapter{
    private List<CommentBean.CommentsBean> mData;
    private final Context mContext;

    public MyPIngAdapter(List<CommentBean.CommentsBean> data, Context context) {

        mData = data;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.pinghuiitem, null, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        RequestOptions requestOptions = new RequestOptions().circleCrop();
        Glide.with(mContext).load(mData.get(position).getAvatar()).apply(requestOptions).into(holder1.mIv);
        holder1.mTvname.setText(mData.get(position).getAuthor());
        holder1.mTvbody.setText(mData.get(position).getContent());
        holder1.mTvtime.setText(mData.get(position).getTime());
        holder1.mTvlike.setText(mData.get(position).getLikes());
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
        private final TextView mTvname;
        private final TextView mTvbody;
        private final TextView mTvtime;
        private final TextView mTvlike;

        public MyViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
            mTvname = itemView.findViewById(R.id.tv_name);
            mTvbody = itemView.findViewById(R.id.tv_body);
            mTvtime = itemView.findViewById(R.id.tv_time);
            mTvlike = itemView.findViewById(R.id.tv_like);

        }
    }

    public void setData(List<CommentBean.CommentsBean> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }
}
