package com.example.geeknews.beas.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.geeknews.beas.presenter.BasePresenter;

/**
 * Created by 马明祥 on 2018/12/21.
 */

public abstract class BaseFragment<V,P extends BasePresenter<V>> extends SimpleFragment{

    public P presenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        if (presenter != null){
            presenter.attachView((V) this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        if (presenter != null){
            presenter.deatchView();
            presenter = null;
        }
        super.onDestroyView();
    }
}
