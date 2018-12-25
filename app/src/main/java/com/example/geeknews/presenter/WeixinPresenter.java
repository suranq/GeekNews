package com.example.geeknews.presenter;

import com.example.geeknews.api.WeixinApi;
import com.example.geeknews.beans.zhihu.weixin.WeiXinBean;
import com.example.geeknews.beas.presenter.IBasePresenter;
import com.example.geeknews.mdolue.WeixinModlue;
import com.example.geeknews.view.WeixinView;
import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by 马明祥 on 2018/12/25.
 */

public class WeixinPresenter<V extends WeixinView>extends IBasePresenter<V> implements WeixinModlue.WeixinCallback {

    private WeixinModlue mWeixinModlue = new WeixinModlue();

    public void getWeixin(Map<String,Object>map, WeixinApi weixinApi){
        if (mView != null){
            mWeixinModlue.getWeixin(map,this,weixinApi);
        }
    }

    @Override
    public void setShowProgressbar() {
        mView.showProgressbar();
    }

    @Override
    public void setHideProgressbar() {
        mView.hideProgressbar();
    }

    @Override
    public void setError(String error) {

    }

    @Override
    public void setWeixin(Object o, WeixinApi weixinApi) {
        String data = (String) o;
        Gson gson = new Gson();
        if (mView != null){
            switch (weixinApi) {
                case DATA:
                    WeiXinBean weiXinBean = gson.fromJson(data, WeiXinBean.class);
                    mView.show(weiXinBean);
                    break;
            }
        }
    }
}
