package com.example.nayir.moviesapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by nayir on 5/8/2017.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance;
        // or return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
