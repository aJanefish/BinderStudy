package com.example.binderservice.install;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.ipc.util.Constant;

public class ApkTestService extends Service {

    private static final String TAG = Constant.PRE_TAG + "ApkTestService";

    public ApkTestService() {
    }

    private Binder mBinder = new ApkInstallManager(this);

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate:" + this + " " + Thread.currentThread());
    }


    @Override
    public IBinder onBind(Intent intent) {
        // FIXME: 2020/8/1 校验权限
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            int checkSelfPermission = checkSelfPermission(PER);
//            Log.d(TAG, "onBind checkSelfPermission:" + checkSelfPermission);
//        }
//
//        int checkCallingPermission = checkCallingPermission(PER);
//        Log.d(TAG, "onBind checkCallingPermission:" + checkCallingPermission);
//        int checkCallingOrSelfPermission = checkCallingOrSelfPermission(PER);
//        Log.d(TAG, "onBind check:" + checkCallingOrSelfPermission + " " + Thread.currentThread());
//        if (checkCallingOrSelfPermission == PackageManager.PERMISSION_DENIED) {
//            //权限不满足，返回NULL，绑定服务失败
//            return null;
//        }
        Log.d(TAG, "onBind:" + mBinder + " identityHashCode:" + System.identityHashCode(mBinder));
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand:" + mBinder + " " + Thread.currentThread());
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind:" + Thread.currentThread());
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind:" + Thread.currentThread());
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy:" + Thread.currentThread());
    }
}
