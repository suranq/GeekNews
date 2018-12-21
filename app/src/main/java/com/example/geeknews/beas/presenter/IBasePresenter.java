package com.example.geeknews.beas.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by 马明祥 on 2018/12/21.
 */

public class IBasePresenter<V> implements BasePresenter<V>{
    //弱引用
    private WeakReference<V> weakreference;

    public V mView;

    @Override
    public void attachView(V v) {
        weakreference = new WeakReference<V>(v);
        this.mView = weakreference.get();
    }

    @Override
    public void deatchView() {
        if (weakreference != null && weakreference.get() != null){
            weakreference.clear();
            weakreference = null;
        }
    }
}
