package com.example.geeknews.http.server;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by 马明祥 on 2018/12/25.
 */

public interface ShujuServer {

    String HOTP = "http://api.shujuzhihui.cn/api/news/";

    /**
     * 数据智汇列表
     * */

    @GET()
    Observable<String> getCategories(@Url String url, @QueryMap Map<String,Object>map);

}
