package com.example.geeknews.presenter;

import com.example.geeknews.api.GanhuoApi;
import com.example.geeknews.beans.zhihu.ganhuo.GanAndroid;
import com.example.geeknews.beas.presenter.IBasePresenter;
import com.example.geeknews.mdolue.GanhuoModlue;
import com.example.geeknews.view.GanhuoView;
import com.google.gson.Gson;

/**
 * Created by 马明祥 on 2018/12/25.
 */

public class GanhuoPresenter<V extends GanhuoView> extends IBasePresenter<V> implements GanhuoModlue.GanhuoCallback {

    private GanhuoModlue mGanhuoModlue = new GanhuoModlue();

    public void getGanhuo(String query,String tech,int page, GanhuoApi ganhuoApi) {
        if (mView != null) {
            mGanhuoModlue.getGanhuo(query,tech,page,this, ganhuoApi);
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
    public void setGanhuo(Object o, GanhuoApi ganhuoApi) {
        if (mView != null) {
            switch (ganhuoApi) {
                case JISHU:
                    mView.show(o,ganhuoApi);
                    break;
                case SUIJIMEIZI:
                    mView.show(o,ganhuoApi);
                    break;
                case SOUSUO:
                    mView.show(o,ganhuoApi);
                    break;
            }
        }
    }
}
