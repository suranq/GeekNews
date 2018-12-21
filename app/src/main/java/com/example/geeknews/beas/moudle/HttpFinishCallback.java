package com.example.geeknews.beas.moudle;

/**
 * Created by 马明祥 on 2018/12/21.
 */

//p层接口回调
public interface HttpFinishCallback {
    void setShowProgressbar();
    void setHideProgressbar();
    void setError(String error);
}
