package com.example.geeknews.mdolue;

import com.example.geeknews.beans.zhihu.DailyListBean;
import com.example.geeknews.beas.moudle.HttpFinishCallback;
import com.example.geeknews.http.BaseObserver;
import com.example.geeknews.http.ZhihuManager;
import com.example.geeknews.utils.RxUtils;

/**
 * Created by 马明祥 on 2018/12/21.
 */

public class ZhihuModlue {

    public interface ZhihuCallback extends HttpFinishCallback{
        void setDailyListBean(DailyListBean dailyListBean);
    }

    public void getDailyListBean(final ZhihuCallback zhihuCallback){
        zhihuCallback.setShowProgressbar();
        ZhihuManager.getZhihuManager().getDailyList().compose(RxUtils.<DailyListBean>rxObserableSchedulerHelper()).subscribe(new BaseObserver<DailyListBean>(zhihuCallback) {
            @Override
            public void onNext(DailyListBean value) {
                zhihuCallback.setDailyListBean(value);
            }
        });
    }

}
