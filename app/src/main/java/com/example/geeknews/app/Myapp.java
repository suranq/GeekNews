package com.example.geeknews.app;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by 马明祥 on 2018/12/21.
 */

public class Myapp extends Application {
    private static Myapp sMyapp;

    @Override
    public void onCreate() {
        super.onCreate();
        sMyapp = this;
// 默认设置为日间模式
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }

    public static Myapp getMyapp() {
        return sMyapp;
    }
}
