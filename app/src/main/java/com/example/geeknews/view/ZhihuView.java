package com.example.geeknews.view;

/**
 * Created by 马明祥 on 2018/12/24.
 */

public interface ZhihuView<T> {

    void showZhihu(T t);
    void showError(String error);
}
