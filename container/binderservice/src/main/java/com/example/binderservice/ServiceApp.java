package com.example.binderservice;

import android.app.Application;

import com.zy.zlog.ZLog;


public class ServiceApp extends Application {
    private static final String TAG = "ServiceApp";

    @Override
    public void onCreate() {
        super.onCreate();
        ZLog.d(TAG, "onCreate:" + this);
    }
}
