package com.example.geeknews.adapters.shuju;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.geeknews.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 马明祥 on 2019/1/1.
 */

public class ShujuLanAdapter extends XRecyclerView.Adapter {
    private List<String> mData;
    private final ShujuActivity mShujuActivity;
    public static boolean mIsChecked;
    private List<String> shuju = new ArrayList<>();

    public ShujuLanAdapter(List<String> data, ShujuActivity shujuActivity) {

        mData = data;
        mShujuActivity = shujuActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mShujuActivity).inflate(R.layout.shujuitem, null, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        holder1.mTv.setText(mData.get(position));
        holder1.mSc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    shuju.add(mData.get(position));
                    Log.e("tttttt",mData.get(position));
                }
            }
        });
        EventBus.getDefault().postSticky(shuju);
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    class MyViewHolder extends XRecyclerView.ViewHolder {

        private final TextView mTv;
        private final SwitchCompat mSc;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv);
            mSc = itemView.findViewById(R.id.sc);
        }
    }

    public void setData(List<String> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
