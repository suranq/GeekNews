package com.example.geeknews.greendao;

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
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by 马明祥 on 2019/1/2.
 */

public class DaoAdapter extends XRecyclerView.Adapter{
    private List<DaoNews> mDaoNews;
    private final Context mContext;
    private OnItemListener mListener;

    public DaoAdapter(List<DaoNews> daoNews, Context context) {

        mDaoNews = daoNews;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        Glide.with(mContext).load(mDaoNews.get(position).getImage()).into(holder1.mIv);
        holder1.mTv.setText(mDaoNews.get(position).getTitle());

        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.OnItemListener(mDaoNews.get(position));
                }
            }
        });
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

    @Override
    public int getItemCount() {
        if (mDaoNews == null){
            return 0;
        }
        return mDaoNews.size();
    }

    public interface OnItemListener{
        void OnItemListener(DaoNews daoNews);
    }

    public void setOnItemListener(OnItemListener listener){
        mListener = listener;
    }
}
