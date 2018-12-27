package com.example.geeknews.http.zhihu;

import com.example.geeknews.api.ZhihuApi;
import com.example.geeknews.http.HttpManager;
import com.example.geeknews.http.server.ZhihuServer;

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

    public Observable<String> getZhihu(String data,int id,ZhihuApi zhihuApi) {
        switch (zhihuApi) {
            case ZUIXINRIBAO:
                sZhihuServer.getDailyList();
                break;
            case ZHUANLANRIBAO:
                sZhihuServer.getSectionList();
                break;
            case REMENRIBAO:
                sZhihuServer.getHotList();
                break;
            case RIBAOXIANGQING:
                sZhihuServer.getDetailInfo(id);
                break;
            case ZHUANLANRIBAOXIANGQING:
                sZhihuServer.getSectionChildList(id);
                break;
            case EWAIXINXI:
                sZhihuServer.getDetailExtraInfo(id);
                break;
            case CHANGPING:
                sZhihuServer.getLongCommentInfo(id);
                break;
            case DUANPING:
                sZhihuServer.getShortCommentInfo(id);
                break;
            case WANGQIRIBAO:
                sZhihuServer.getDailyBeforeList(data);
                break;
        }
        return null;
    }

}
