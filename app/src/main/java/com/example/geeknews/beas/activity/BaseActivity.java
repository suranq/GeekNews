package com.example.geeknews.beas.activity;

import com.example.geeknews.beas.presenter.BasePresenter;

/**
 * Created by 马明祥 on 2018/12/21.
 */

public abstract class BaseActivity<V, P extends BasePresenter<V>> extends SimpleActivity {

    public P presenter;

    @Override
    public void wiewCreated() {
        super.wiewCreated();
        presenter = createPresenter();
        if (presenter != null){
            presenter.attachView((V) this);
        }
    }
    //创建子类的P层对象
    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            presenter.deatchView();
            presenter = null;
        }
    }
}
