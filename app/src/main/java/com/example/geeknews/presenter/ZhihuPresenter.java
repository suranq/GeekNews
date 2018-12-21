package com.example.geeknews.presenter;

import com.example.geeknews.beans.zhihu.DailyListBean;
import com.example.geeknews.beas.presenter.IBasePresenter;
import com.example.geeknews.http.ZhihuManager;
import com.example.geeknews.mdolue.ZhihuModlue;
import com.example.geeknews.view.ZhihuView;

/**
 * Created by 马明祥 on 2018/12/21.
 */

public class ZhihuPresenter<V extends ZhihuView> extends IBasePresenter<V> implements ZhihuModlue.ZhihuCallback {

    private ZhihuModlue mZhihuModlue = new ZhihuModlue();

    public void getDailyListBean(){
        if (mView != null){
            mZhihuModlue.getDailyListBean(this);
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
        if (mView != null){
            mView.showError(error);
        }
    }

    @Override
    public void setDailyListBean(DailyListBean dailyListBean) {
        if (mView != null){
            mView.show(dailyListBean);
        }
    }
}
