package com.zy.server.install;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.zy.zlog.ZLog;

public class ApkBaseService extends Service {

    private final String TAG;

    public ApkBaseService() {
        TAG = getDiyTag();
    }

    protected String getDiyTag() {
        return "ApkTestService";
    }

    private Binder mBinder = new ApkInstallManager(this);

    @Override
    public void onCreate() {
        super.onCreate();
        ZLog.d(TAG, "onCreate:" + this + " " + Thread.currentThread());
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
        ZLog.d(TAG, "onBind:" + mBinder + " identityHashCode:" + System.identityHashCode(mBinder));
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ZLog.d(TAG, "onStartCommand:" + mBinder + " " + Thread.currentThread());
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        ZLog.d(TAG, "onRebind:" + Thread.currentThread());
    }

    @Override
    public boolean onUnbind(Intent intent) {
        ZLog.d(TAG, "onUnbind:" + Thread.currentThread());
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ZLog.d(TAG, "onDestroy:" + Thread.currentThread());
    }
}
