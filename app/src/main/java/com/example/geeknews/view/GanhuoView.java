package com.example.geeknews.view;

import com.example.geeknews.api.GanhuoApi;
import com.example.geeknews.beas.view.BaseView;

/**
 * Created by 马明祥 on 2018/12/25.
 */

public interface GanhuoView<T> extends BaseView{

    void show(T t, GanhuoApi ganhuoApi);

    void showError(String error);

}
