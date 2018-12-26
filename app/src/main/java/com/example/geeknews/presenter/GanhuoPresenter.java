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

    public void getGanhuo(String tech,int page, GanhuoApi ganhuoApi) {
        if (mView != null) {
            mGanhuoModlue.getGanhuo(tech,page,this, ganhuoApi);
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
        String data = (String) o;
        Gson gson = new Gson();
        if (mView != null) {
            switch (ganhuoApi) {
                case JISHU:
                    GanAndroid ganAndroid = gson.fromJson(data, GanAndroid.class);
                    mView.show(ganAndroid);
                    break;
                case SUIJIMEIZI:
                    GanAndroid ganAndroid1 = gson.fromJson(data, GanAndroid.class);
                    mView.show(ganAndroid1);
                    break;
            }
        }
    }
}
