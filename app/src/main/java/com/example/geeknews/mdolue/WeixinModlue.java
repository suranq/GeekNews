package com.example.geeknews.mdolue;

import com.example.geeknews.api.WeixinApi;
import com.example.geeknews.beas.moudle.HttpFinishCallback;
import com.example.geeknews.http.BaseObserver;
import com.example.geeknews.http.weixin.WeixinManager;
import com.example.geeknews.utils.RxUtils;

import java.util.Map;

/**
 * Created by 马明祥 on 2018/12/25.
 */

public class WeixinModlue {
    public interface WeixinCallback<T> extends HttpFinishCallback{
        void setWeixin(T t, WeixinApi weixinApi);
    }

    public void getWeixin(Map<String,Object>map, final WeixinCallback weixinCallback, final WeixinApi weixinApi){
        weixinCallback.setShowProgressbar();
        switch (weixinApi) {
            case DATA:
                WeixinManager.getWeixinServer().getWeixin(map).compose(RxUtils.<String>rxObserableSchedulerHelper()).subscribe(new BaseObserver<String>(weixinCallback) {
                    @Override
                    public void onNext(String value) {
                        weixinCallback.setWeixin(value,weixinApi);
                    }
                });
                break;
        }
    }
}
