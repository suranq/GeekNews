package com.example.geeknews.adapters.zhihu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.beans.zhihu.DailyListBean;

import java.util.List;

/**
 * Created by 马明祥 on 2018/12/26.
 */

public class MyImageAdapter extends PagerAdapter{
    private final Context mContext;
    private List<DailyListBean.TopStoriesBean> mData;

    public MyImageAdapter(Context context, List<DailyListBean.TopStoriesBean> data) {

        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_top, container, false);
        ImageView ivImage = (ImageView) inflate.findViewById(R.id.iv_top_image);
        TextView tvTitle = (TextView) inflate.findViewById(R.id.tv_top_title);
        Glide.with(mContext).load(mData.get(position).getImage()).into(ivImage);

        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
