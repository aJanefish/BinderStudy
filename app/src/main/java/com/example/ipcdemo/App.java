package com.example.ipcdemo;

import android.app.Application;
import android.util.Log;

import com.example.ipc.util.Constant;
import com.example.ipcdemo.classloader.ClassLoaderUtils;
import com.example.ipcdemo.manager.DemoManager;

public class App extends Application {
    private static final String TAG = Constant.PRE_TAG + "APP";
    private long id = 0 ;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate:" + this + " " + System.identityHashCode(this));
//        DemoManager.getDemoManager().init(this);
//        DemoManager.getDemoManager().bindDiyService();
        //ClassLoaderUtils.installHook();
    }
}
