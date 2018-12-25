package com.example.geeknews.http.ganhuo;

import com.example.geeknews.api.GanhuoApi;
import com.example.geeknews.http.HttpManager;
import com.example.geeknews.http.server.GanhuoServer;

import io.reactivex.Observable;

/**
 * Created by 马明祥 on 2018/12/25.
 */

public class GanhuoManager {

    private static GanhuoServer sGanhuoServer;

    public static GanhuoServer getGanhuoServer(){
        if (sGanhuoServer == null){
            synchronized (GanhuoServer.class){
                if (sGanhuoServer == null){
                    sGanhuoServer = HttpManager.getInstance().getServer(GanhuoServer.HOST,GanhuoServer.class);
                }
            }
        }
        return sGanhuoServer;
    }

    public Observable<String> getGanhuo(String tech,int page,GanhuoApi ganhuoApi){
        switch (ganhuoApi) {
            case JISHU:
                sGanhuoServer.getJishu(tech,page);
                break;
        }
        return null;
    }

}
