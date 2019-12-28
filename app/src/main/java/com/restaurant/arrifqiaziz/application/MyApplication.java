package com.restaurant.arrifqiaziz.application;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

public class MyApplication extends Application {


    private static MyApplication instance;

    public static Context getContext() {
        return instance;
    }

    public MyApplication() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
