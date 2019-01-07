package com.example.geeknews.http.server;

import com.example.geeknews.beans.zhihu.v2ex.NodeBean;
import com.example.geeknews.beans.zhihu.v2ex.NodeListBean;
import com.example.geeknews.beans.zhihu.v2ex.RepliesListBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 马明祥 on 2019/1/7.
 */

public interface V2exServer {
    String HOST = "https://www.v2ex.com/";

    String TAB_HOST = "https://www.v2ex.com/?tab=";

    String REPLIES_URL = "https://www.v2ex.com/t/";

    /**
     * 获取节点信息
     * @return
     */
    @GET("/api/nodes/show.json")
    Flowable<NodeBean> getNodeInfo(@Query("name") String name);

    /**
     * 获取主题列表
     * @return
     */
    @GET("/api/topics/show.json")
    Flowable<List<NodeListBean>> getTopicList(@Query("node_name") String name);

    /**
     * 获取主题信息
     * @return
     */
    @GET("/api/topics/show.json")
    Flowable<List<NodeListBean>> getTopicInfo(@Query("id") String id);

    /**
     * 获取主题回复
     * @return
     */
    @GET("/api/replies/show.json")
    Flowable<List<RepliesListBean>> getRepliesList(@Query("topic_id") String id);
}
