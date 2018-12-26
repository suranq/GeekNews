package com.example.geeknews.http.weixin;

import com.example.geeknews.api.WeixinApi;
import com.example.geeknews.http.HttpManager;
import com.example.geeknews.http.server.WeixinServer;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by 马明祥 on 2018/12/25.
 */

public class WeixinManager {

    private static WeixinServer sWeixinServer;

    public static WeixinServer getWeixinServer(){
        if (sWeixinServer == null){
            synchronized (WeixinServer.class){
                if (sWeixinServer == null){
                    sWeixinServer = HttpManager.getInstance().getServer(WeixinServer.HOST,WeixinServer.class);
                }
            }
        }
        return sWeixinServer;
    }

    public Observable<String> getWeixin(Map<String,Object> map,WeixinApi weixinApi){
        switch (weixinApi) {
            case DATA:
                sWeixinServer.getWeixin(map);
                break;
            case SHOUSUO:
                sWeixinServer.getSousuo(map);
                break;
        }
        return null;
    }

}
