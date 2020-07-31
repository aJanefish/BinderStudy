package com.example.ipcdemo;

import android.app.Application;
import android.util.Log;

public class App extends Application {
    private static final String TAG = "zhangyu.APP";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate:" + this);
    }
}
