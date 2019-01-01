package com.example.geeknews.adapters.ganhuo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.beans.zhihu.ganhuo.GanAndroid;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by 马明祥 on 2018/12/25.
 */

public class MyFuLiAdapter extends XRecyclerView.Adapter{
    private List<GanAndroid.ResultsBean> mData;
    private final Context mContext;
    private OnItemListener mListener;

    public MyFuLiAdapter(List<GanAndroid.ResultsBean> data, Context context) {

        mData = data;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.fuliitem, null, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        Glide.with(mContext).load(mData.get(position).getUrl()).into(holder1.mIv);
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.OnItemListener(mData.get(position));
                }
            }
        });
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

        public MyViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
        }
    }

    public void setData(List<GanAndroid.ResultsBean> ganAndroid, int page) {
        if (page == 1){
            mData.clear();
        }
        mData.addAll(ganAndroid);
        notifyDataSetChanged();
    }

    public interface OnItemListener{
        void OnItemListener(GanAndroid.ResultsBean resultsBean);
    }

    public void setOnItemListener(OnItemListener listener){
        mListener = listener;
    }
}
