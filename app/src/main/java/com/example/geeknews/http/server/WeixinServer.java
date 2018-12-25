package com.example.geeknews.http.server;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by 马明祥 on 2018/12/25.
 */

public interface WeixinServer {

//    http://api.tianapi.com/wxnew/?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1

    String HOST = "http://api.tianapi.com/";

    /**
     //     * 微信精选列表
     //     */
    @GET("wxnew/?")
    Observable<String> getWeixin(@QueryMap Map<String,Object>map);

//    /**
//     * 微信精选列表
//     */
//    @GET("wxnew")
//    Flowable<WXHttpResponse<List<WXItemBean>>> getWXHotSearch(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);

}
