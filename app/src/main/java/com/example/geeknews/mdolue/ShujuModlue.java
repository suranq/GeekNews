package com.example.geeknews.mdolue;

import com.example.geeknews.api.ShujuApi;
import com.example.geeknews.beas.moudle.HttpFinishCallback;
import com.example.geeknews.http.BaseObserver;
import com.example.geeknews.http.shuju.ShujuManager;
import com.example.geeknews.utils.RxUtils;

import java.util.Map;

/**
 * Created by 马明祥 on 2018/12/29.
 */

public class ShujuModlue {

    public interface ShujuCallback<T>extends HttpFinishCallback{
        void setshuju(T t, ShujuApi shujuApi);
    }
    public void getShuju(String url, Map<String,Object>map, final ShujuCallback shujuCallback, final ShujuApi shujuApi){
        switch (shujuApi) {
            case TITLE:
                ShujuManager.getShujuServer().getCategories(url,map).compose(RxUtils.<String>rxObserableSchedulerHelper()).subscribe(new BaseObserver<String>(shujuCallback) {
                    @Override
                    public void onNext(String s) {
                        shujuCallback.setshuju(s,shujuApi);
                    }
                });
                break;
            case DATA:
                ShujuManager.getShujuServer().getCategories(url,map).compose(RxUtils.<String>rxObserableSchedulerHelper()).subscribe(new BaseObserver<String>(shujuCallback) {
                    @Override
                    public void onNext(String s) {
                        shujuCallback.setshuju(s,shujuApi);
                    }
                });
                break;
        }
    }
}
