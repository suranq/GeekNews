package com.example.geeknews.adapters.zhihu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.activitys.weixin.zhihu.ZhuanlanActivity;
import com.example.geeknews.beans.zhihu.SectionChildListBean;
import com.example.geeknews.shezhidao.SheZhi;
import com.example.geeknews.shezhidao.SheZhiku;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by 马明祥 on 2018/12/26.
 */

public class MyZhuanLanXiangAdapter extends XRecyclerView.Adapter {
    private List<SectionChildListBean.StoriesBean> mData;
    private final ZhuanlanActivity mZhuanlanActivity;
    private OnItemListener mListener;

    public MyZhuanLanXiangAdapter(List<SectionChildListBean.StoriesBean> data, ZhuanlanActivity zhuanlanActivity) {

        mData = data;
        mZhuanlanActivity = zhuanlanActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mZhuanlanActivity).inflate(R.layout.ribaotem, null, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        List<SheZhi> sheZhis = SheZhiku.getInsh().selectAll();
        if (!sheZhis.get(0).getIsWutu()) {
            Glide.with(mZhuanlanActivity).load(mData.get(position).getImages().get(0)).into(holder1.mIv);
        }
        holder1.mTv.setText(mData.get(position).getTitle());

        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.OnItemListener(mData.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
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

    public void setData(List<SectionChildListBean.StoriesBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public interface OnItemListener {
        void OnItemListener(SectionChildListBean.StoriesBean storiesBean);
    }

    public void setOnItemListener(OnItemListener listener) {
        mListener = listener;
    }
}
