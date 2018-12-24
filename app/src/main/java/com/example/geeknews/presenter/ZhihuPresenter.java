package com.example.geeknews.presenter;

import com.example.geeknews.api.ZhihuApi;
import com.example.geeknews.beans.zhihu.DailyListBean;
import com.example.geeknews.beas.presenter.BasePresenter;
import com.example.geeknews.beas.presenter.IBasePresenter;
import com.example.geeknews.mdolue.ZhihuModlue;
import com.example.geeknews.view.ZhihuView;
import com.google.gson.Gson;

/**
 * Created by 马明祥 on 2018/12/24.
 */

public class ZhihuPresenter<V extends ZhihuView> extends IBasePresenter<V> implements ZhihuModlue.ZhihuCallback {

    private ZhihuModlue mZhihuModlue = new ZhihuModlue();

    public void getZhihu(ZhihuApi zhihuApi){
        if (mView != null){
            mZhihuModlue.getZhihu(this,zhihuApi);
        }
    }


    @Override
    public void setShowProgressbar() {

    }

    @Override
    public void setHideProgressbar() {

    }

    @Override
    public void setError(String error) {

    }

    @Override
    public void setZhihu(Object o, ZhihuApi zhihuApi) {
        switch (zhihuApi) {
            case ZUIXINRIBAO:
                mView.showZhihu(o);
                break;
        }
    }
}
