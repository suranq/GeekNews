package com.example.geeknews.view;

import com.example.geeknews.beans.zhihu.DailyListBean;
import com.example.geeknews.beas.view.BaseView;

/**
 * Created by 马明祥 on 2018/12/21.
 */

public interface ZhihuView extends BaseView{

    void show(DailyListBean dailyListBean);

}
