package com.example.geeknews.view;

import com.example.geeknews.beas.view.BaseView;

/**
 * Created by 马明祥 on 2018/12/24.
 */

public interface ZhihuView<T> extends BaseView{

    void showZhihu(T t);
    void showError(String error);
}
