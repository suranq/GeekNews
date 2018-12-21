package com.example.geeknews.http;


import com.example.geeknews.beas.moudle.HttpFinishCallback;

import java.net.HttpRetryException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


/**
 * Created by 马明祥 on 2018/12/21.
 */

public abstract class BaseObserver<T> implements Observer<T> {

    //回调结果处理
    private HttpFinishCallback mHttpFinishCallback;

    public BaseObserver(HttpFinishCallback httpFinishCallback) {
        mHttpFinishCallback = httpFinishCallback;
    }
    //管理内存网络请求
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    @Override
    public void onSubscribe(Disposable d) {
        mCompositeDisposable.add(d);
    }

    @Override
    public void onError(Throwable e) {
        if (mCompositeDisposable != null){
            mCompositeDisposable.clear();
        }
        if (mHttpFinishCallback != null){
            if (e instanceof HttpException){
                mHttpFinishCallback.setError("网络请求错误");
            }else {
                mHttpFinishCallback.setError("其他请求错误");
            }
        }
    }

    @Override
    public void onComplete() {
        if (mCompositeDisposable != null){
            mCompositeDisposable.clear();
        }
        if (mHttpFinishCallback != null){
            mHttpFinishCallback.setHideProgressbar();
        }
    }
}
