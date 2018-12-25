package com.example.geeknews.mdolue;

import com.example.geeknews.api.GanhuoApi;
import com.example.geeknews.beans.zhihu.ganhuo.GanAndroid;
import com.example.geeknews.beas.moudle.HttpFinishCallback;
import com.example.geeknews.http.BaseObserver;
import com.example.geeknews.http.ganhuo.GanhuoManager;
import com.example.geeknews.utils.RxUtils;

/**
 * Created by 马明祥 on 2018/12/25.
 */

public class GanhuoModlue {

    public interface GanhuoCallback<T> extends HttpFinishCallback{
        void setGanhuo(T t, GanhuoApi ganhuoApi);
    }

    public void getGanhuo(String tech,int page, final GanhuoCallback ganhuoCallback, final GanhuoApi ganhuoApi){
        ganhuoCallback.setShowProgressbar();
        switch (ganhuoApi) {
            case JISHU:
                GanhuoManager.getGanhuoServer().getJishu(tech,page).compose(RxUtils.<String>rxObserableSchedulerHelper()).subscribe(new BaseObserver<String>(ganhuoCallback) {
                    @Override
                    public void onNext(String value) {
                        ganhuoCallback.setGanhuo(value,ganhuoApi);
                    }
                });
                break;
        }
    }

}
