package com.example.geeknews.http;

import com.example.geeknews.beans.zhihu.DailyListBean;

import io.reactivex.Observable;

/**
 * Created by 马明祥 on 2018/12/21.
 */

public class ZhihuManager {

    private static ZhihuServer sZhihuServer;

    public static ZhihuServer getZhihuManager() {
        if (sZhihuServer == null) {
            synchronized (ZhihuServer.class) {
                if (sZhihuServer == null) {
                    sZhihuServer = HttpManager.getInstance().getServer(ZhihuServer.HOST, ZhihuServer.class);
                }
            }
        }
        return sZhihuServer;
    }

    public Observable<DailyListBean> getDailyListBean() {
        return sZhihuServer.getDailyList();
    }

}
