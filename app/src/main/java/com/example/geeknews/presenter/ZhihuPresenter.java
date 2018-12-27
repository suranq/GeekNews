package com.example.geeknews.presenter;

import com.example.geeknews.api.ZhihuApi;
import com.example.geeknews.beans.zhihu.CommentBean;
import com.example.geeknews.beans.zhihu.DailyBeforeListBean;
import com.example.geeknews.beans.zhihu.DailyListBean;
import com.example.geeknews.beans.zhihu.DetailExtraBean;
import com.example.geeknews.beans.zhihu.HotListBean;
import com.example.geeknews.beans.zhihu.SectionChildListBean;
import com.example.geeknews.beans.zhihu.SectionListBean;
import com.example.geeknews.beans.zhihu.ZhihuDetailBean;
import com.example.geeknews.beas.presenter.BasePresenter;
import com.example.geeknews.beas.presenter.IBasePresenter;
import com.example.geeknews.mdolue.ZhihuModlue;
import com.example.geeknews.utils.ZhihuDiffCallback;
import com.example.geeknews.view.ZhihuView;
import com.google.gson.Gson;

/**
 * Created by 马明祥 on 2018/12/24.
 */

public class ZhihuPresenter<V extends ZhihuView> extends IBasePresenter<V> implements ZhihuModlue.ZhihuCallback {

    private ZhihuModlue mZhihuModlue = new ZhihuModlue();

    public void getZhihu(String data,int id,ZhihuApi zhihuApi){
        if (mView != null){
            mZhihuModlue.getZhihu(data,id,this,zhihuApi);
        }
    }


    @Override
    public void setShowProgressbar() {

    }

    @Override
    public void setHideProgressbar() {

    }

    @Override
    public void setError(String error) {

    }

    @Override
    public void setZhihu(Object o, ZhihuApi zhihuApi) {
        if (mView!= null){
            switch (zhihuApi) {
                case ZUIXINRIBAO:

                    mView.showZhihu(o,zhihuApi);
                    break;
                case ZHUANLANRIBAO:
                    mView.showZhihu(o,zhihuApi);
                    break;
                case REMENRIBAO:
                    mView.showZhihu(o,zhihuApi);
                    break;
                case RIBAOXIANGQING:
                    mView.showZhihu(o,zhihuApi);
                    break;
                case ZHUANLANRIBAOXIANGQING:
                    mView.showZhihu(o,zhihuApi);
                    break;
                case EWAIXINXI:
                    mView.showZhihu(o,zhihuApi);
                    break;
                case CHANGPING:
                    mView.showZhihu(o,zhihuApi);
                    break;
                case DUANPING:
                    mView.showZhihu(o,zhihuApi);
                    break;
                case WANGQIRIBAO:
                    mView.showZhihu(o,zhihuApi);
                    break;
            }
        }
    }
}
