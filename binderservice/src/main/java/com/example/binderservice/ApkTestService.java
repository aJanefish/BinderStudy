package com.example.binderservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.example.ipc.ApkInfo;
import com.example.ipc.IApkInstallListener;
import com.example.ipc.IApkInstallManager;

public class ApkTestService extends Service {

    private static final String TAG = "zhangyu.ApkTestService";
    private int flag;
    private RemoteCallbackList<IApkInstallListener> listeners = new RemoteCallbackList<>();

    public ApkTestService() {
    }


    private Binder mBinder = new IApkInstallManager.Stub() {
        @Override
        public void setFlag(int flag) throws RemoteException {
            ApkTestService.this.flag = flag;
            Log.d(TAG, "setFlag:" + " " + Thread.currentThread());
        }

        @Override
        public int getFlag() throws RemoteException {
            Log.d(TAG, "getFlag:" + flag + " " + Thread.currentThread());
            return ApkTestService.this.flag;
        }

        @Override
        public boolean checkPermission() throws RemoteException {
            Log.d(TAG, "checkPermission:" + Thread.currentThread());
            return false;
        }

        @Override
        public void startSilentInstall(ApkInfo info) throws RemoteException {
            Log.d(TAG, "startSilentInstall:" + info + " " + Thread.currentThread());
            startSilentInstallImp(info);
        }

        @Override
        public void startInstall(ApkInfo info) throws RemoteException {
            Log.d(TAG, "startInstall:" + info + " " + Thread.currentThread());
            startInstallImp(info);
        }

        @Override
        public void registerListener(IApkInstallListener listener) throws RemoteException {
            try {
                listeners.register(listener);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int sum = listeners.beginBroadcast();
            listeners.finishBroadcast();
            Log.d(TAG, "registerListener:" + sum + " " + listener + " " + Thread.currentThread());
            Log.d(TAG, "registerListener:" + listener.asBinder());
        }

        @Override
        public void unregisterListener(IApkInstallListener listener) throws RemoteException {
            try {
                listeners.unregister(listener);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int sum = listeners.beginBroadcast();
            listeners.finishBroadcast();
            Log.d(TAG, "unregisterListener:" + sum + " " + listener + " " + Thread.currentThread());
        }
    };

    private void startInstallImp(ApkInfo info) throws RemoteException {
        int n = listeners.beginBroadcast();
        for (int i = 0; i < n; i++) {
            IApkInstallListener iApkInstallListener = listeners.getBroadcastItem(i);
            if (iApkInstallListener != null) {
                iApkInstallListener.onStatusChanged(1, "start common Install");
                iApkInstallListener.onStatusChanged(2, "check apk");
                info.setFilePath("" + info.getFilePath() + "...checked");
                iApkInstallListener.onStatusChanged(3, "Installing...");
                iApkInstallListener.onStatusChanged(4, "Install success");
                iApkInstallListener.onStatusChanged(5, "Install failed");
            }
        }
        listeners.finishBroadcast();
    }

    private void startSilentInstallImp(ApkInfo info) throws RemoteException {
        int n = listeners.beginBroadcast();
        for (int i = 0; i < n; i++) {
            IApkInstallListener iApkInstallListener = listeners.getBroadcastItem(i);
            if (iApkInstallListener != null) {
                iApkInstallListener.onStatusChanged(1, "start silent Install");
                iApkInstallListener.onStatusChanged(2, "check apk");
                info.setFilePath("" + info.getFilePath() + "...checked");
                iApkInstallListener.onStatusChanged(3, "Installing...");
                iApkInstallListener.onStatusChanged(4, "Install success");
                iApkInstallListener.onStatusChanged(5, "Install failed");
            }
        }
        listeners.finishBroadcast();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate:" + Thread.currentThread());
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.d(TAG, "onBind:" + Thread.currentThread());
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand:" + Thread.currentThread());
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
