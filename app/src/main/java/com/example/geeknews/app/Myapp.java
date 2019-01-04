package com.example.geeknews.app;

import android.app.Application;

/**
 * Created by 马明祥 on 2018/12/21.
 */

public class Myapp extends Application{
    private static Myapp sMyapp;
    @Override
    public void onCreate() {
        super.onCreate();
        sMyapp = this;

    }

    public static Myapp getMyapp(){
        return sMyapp;
    }
}
