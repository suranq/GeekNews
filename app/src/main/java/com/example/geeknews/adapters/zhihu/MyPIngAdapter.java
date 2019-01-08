package com.example.geeknews.adapters.zhihu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.geeknews.R;
import com.example.geeknews.beans.zhihu.CommentBean;
import com.example.geeknews.utils.DateUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by 马明祥 on 2018/12/27.
 */

public class MyPIngAdapter extends XRecyclerView.Adapter{
    private List<CommentBean.CommentsBean> mData;
    private final Context mContext;

    private static final int Weizhi = 0;
    private static final int Wuxu = 1;
    private static final int YiZhan = 2;
    private static final int YiShow = 3;

    private static final int ErHang =2;
    private final LayoutInflater mInflater;

    public MyPIngAdapter(List<CommentBean.CommentsBean> data, Context context) {

        mData = data;
        mContext = context;
        mInflater = LayoutInflater.from(context);
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
        final MyViewHolder holder1 = (MyViewHolder) holder;
        RequestOptions requestOptions = new RequestOptions().circleCrop();

        Glide.with(mContext).load(mData.get(position).getAvatar()).apply(requestOptions).into(holder1.mCiv_comment_face);
        holder1.mTvcomment_name.setText(mData.get(position).getAuthor());
        holder1.mTv_comment_content.setText(mData.get(position).getContent());
        holder1.mTv_comment_time.setText(DateUtil.formatTime2String(mData.get(position).getTime()));
        holder1.mTv_comment_like.setText(mData.get(position).getLikes()+"");
        if (mData.get(position).getReply_to() != null && mData.get(position).getReply_to().getId() != 0){
            String hui = "@"+mData.get(position).getReply_to().getAuthor()+":"+mData.get(position).getReply_to().getContent();
            holder1.mTv_comment_reply.setText(hui);
            if (mData.get(position).getReply_to().getExpandState() == Weizhi){
                holder1.mTv_comment_reply.post(new Runnable() {
                    @Override
                    public void run() {
                        if (holder1.mTv_comment_reply.getLineCount()>ErHang){
                            holder1.mTv_comment_reply.setMaxLines(ErHang);
                            holder1.mTv_comment_expand.setVisibility(View.VISIBLE);
                            holder1.mTv_comment_expand.setText("展开");
                            mData.get(holder1.getAdapterPosition()).getReply_to().setExpandState(YiShow);
                            holder1.mTv_comment_expand.setOnClickListener(new OnStateClickListener(holder1.getAdapterPosition(),holder1.mTv_comment_reply));
                        }else {
                            holder1.mTv_comment_expand.setVisibility(View.GONE);
                            mData.get(holder1.getAdapterPosition()).getReply_to().setExpandState(Wuxu);
                        }
                    }
                });
            }else if (mData.get(position).getReply_to().getExpandState() == Wuxu){
                holder1.mTv_comment_expand.setVisibility(View.GONE);
            }else if (mData.get(position).getReply_to().getExpandState() == YiZhan){
                holder1.mTv_comment_reply.setMaxLines(Integer.MAX_VALUE);
                holder1.mTv_comment_expand.setText("收起");
                holder1.mTv_comment_expand.setVisibility(View.VISIBLE);
                holder1.mTv_comment_expand.setOnClickListener(new OnStateClickListener(holder1.getAdapterPosition(),holder1.mTv_comment_reply));
            }else {

            }
        }
    }

    @Override
    public int getItemCount() {
        if (mData == null){
            return 0;
        }
        return mData.size();
    }

    private class OnStateClickListener implements View.OnClickListener{
        private final int position;
        private final TextView replyView;

        public OnStateClickListener(int position, TextView replyView) {
            this.position = position;
            this.replyView = replyView;
        }
        @Override
        public void onClick(View v) {
            TextView tv = (TextView) v;
            if (mData.get(position).getReply_to().getExpandState() == YiShow){
                tv.setText("收缩");
                replyView.setMaxLines(Integer.MAX_VALUE);
                replyView.setEllipsize(null);
                mData.get(position).getReply_to().setExpandState(YiZhan);
            }else {
                tv.setText("展开");
                replyView.setMaxLines(ErHang);
                replyView.setEllipsize(TextUtils.TruncateAt.END);
                mData.get(position).getReply_to().setExpandState(YiShow);
            }
        }
    }

    class MyViewHolder extends XRecyclerView.ViewHolder {

        private final ImageView mCiv_comment_face;
        private final TextView mTvcomment_name;
        private final TextView mTv_comment_content;
        private final TextView mTv_comment_reply;
        private final TextView mTv_comment_time;
        private final TextView mTv_comment_like;
        private final TextView mTv_comment_expand;

        public MyViewHolder(View itemView) {
            super(itemView);
            mCiv_comment_face = itemView.findViewById(R.id.civ_comment_face);
            mTvcomment_name = itemView.findViewById(R.id.tvcomment_name);
            mTv_comment_content = itemView.findViewById(R.id.tv_comment_content);
            mTv_comment_reply = itemView.findViewById(R.id.tv_comment_reply);
            mTv_comment_time = itemView.findViewById(R.id.tv_comment_time);
            mTv_comment_like = itemView.findViewById(R.id.tv_comment_like);
            mTv_comment_expand = itemView.findViewById(R.id.tv_comment_expand);

        }
    }

    public void setData(List<CommentBean.CommentsBean> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }
}
