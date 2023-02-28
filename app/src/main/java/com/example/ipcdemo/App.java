package com.example.ipcdemo;

import android.app.Application;
import android.os.Looper;

import com.zy.zlog.ZLog;


public class App extends Application {
    private static final String TAG = "APP";
    private long id = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        ZLog.d(TAG, "onCreate:" + this + " " + System.identityHashCode(this) + " " + Looper.getMainLooper());
//        DemoManager.getDemoManager().init(this);
//        DemoManager.getDemoManager().bindDiyService();
        //ClassLoaderUtils.installHook();
    }
}
