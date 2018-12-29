package com.example.geeknews.presenter;

import com.example.geeknews.api.ShujuApi;
import com.example.geeknews.beas.presenter.IBasePresenter;
import com.example.geeknews.mdolue.ShujuModlue;
import com.example.geeknews.view.ShujuView;

import java.util.Map;

/**
 * Created by 马明祥 on 2018/12/29.
 */

public class ShujuPresenter<V extends ShujuView> extends IBasePresenter<V> implements ShujuModlue.ShujuCallback {

    private ShujuModlue mShujuModlue = new ShujuModlue();

    public void getShuju(String url, Map<String,Object>map, ShujuApi shujuApi){
        mShujuModlue.getShuju(url,map,this,shujuApi);
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
    public void setshuju(Object o, ShujuApi shujuApi) {
        if (mView != null){
            switch (shujuApi) {
                case TITLE:
                    mView.show(o,shujuApi);
                    break;
                case DATA:
                    mView.show(o,shujuApi);
                    break;
            }
        }
    }
}
