package com.example.geeknews.http.server;

import com.example.geeknews.beans.zhihu.ganhuo.GanAndroid;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 马明祥 on 2018/12/25.
 */

public interface GanhuoServer {

    String HOST = "http://gank.io/api/";
//
    /**
     * 技术文章列表
     */
    @GET("data/{tech}/10/{page}")
    Observable<String>getJishu(@Path("tech")String tech,@Path("page")int page);

    /**
     * 妹纸列表
     */
    @GET("data/福利/20/{page}")
    Observable<String>getMeizi(@Path("page")int page);

//
    /**
     * 随机妹纸图
     */
    @GET("random/data/福利/10")
    Observable<String>getSui();
//
//    /**
//     * 搜索
//     */
//    @GET("search/query/{query}/category/{type}/count/{count}/page/{page}")
//    Flowable<GankHttpResponse<List<GankSearchItemBean>>> getSearchList(@Path("query") String query, @Path("type") String type, @Path("count") int num, @Path("page") int page);


}
