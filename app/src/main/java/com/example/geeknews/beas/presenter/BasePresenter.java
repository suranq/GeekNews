package com.example.geeknews.beas.presenter;

/**
 * Created by 马明祥 on 2018/12/21.
 */

public interface BasePresenter<V> {

    //绑定view
    void attachView(V v);

    //解绑view
    void deatchView();
}
