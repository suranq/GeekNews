package com.example.geeknews.http.shuju;

import com.example.geeknews.api.ShujuApi;
import com.example.geeknews.http.HttpManager;
import com.example.geeknews.http.server.ShujuServer;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by 马明祥 on 2018/12/29.
 */

public class ShujuManager {

    private static ShujuServer sShujuServer;

    public static ShujuServer getShujuServer(){
        if (sShujuServer == null){
            synchronized (ShujuServer.class){
                if (sShujuServer == null){
                    sShujuServer = HttpManager.getInstance().getServer(ShujuServer.HOTP,ShujuServer.class);
                }
            }
        }
        return sShujuServer;
    }

    public Observable<String> getShuju(String url, Map<String,Object> map, ShujuApi shujuApi){
        switch (shujuApi) {
            case TITLE:
                sShujuServer.getCategories(url,map);
                break;
            case DATA:
                sShujuServer.getCategories(url,map);
                break;
        }
        return null;
    }
}
