package com.example.geeknews.view;

import com.example.geeknews.api.ShujuApi;

/**
 * Created by 马明祥 on 2018/12/29.
 */

public interface ShujuView<T> {

    void show(T t, ShujuApi shujuApi);

    void showError(String error);

}
