package com.example.binderservice.install;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.example.ipc.ApkInfo;
import com.example.ipc.IApkInstallListener;
import com.example.ipc.IApkInstallManager;
import com.zy.zlog.ZLog;

import java.util.Arrays;

public class ApkInstallManager extends IApkInstallManager.Stub {


    private static final String TAG = "ApkInstallManager";
    private int flag;

    private final String PER_PERMISSION = "com.example.binderservice.TEST_ACCESS";
    private final RemoteCallbackList<IApkInstallListener> listeners = new RemoteCallbackList<>();

    private final Context context;


    ApkInstallManager(Context context) {
        this.context = context;
    }


    @Override
    public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        //return super.onTransact(code, data, reply, flags);
        int checkCallingOrSelfPermission = context.checkCallingOrSelfPermission(PER_PERMISSION);
        ZLog.d(TAG, "onTransact check:" + checkCallingOrSelfPermission + " " + Thread.currentThread());
        if (checkCallingOrSelfPermission == PackageManager.PERMISSION_DENIED) {
            //权限不满足，返回NULL，绑定服务失败
            return false;
        }

        String pkgName = null;
        int callingUid = getCallingUid();
        int callingPid = getCallingPid();
        ZLog.d(TAG, "onTransact callingUid:" + callingUid + " callingPid:" + callingPid);
        String[] packages = context.getPackageManager().getPackagesForUid(callingUid);
        ZLog.d(TAG, "onTransact packages:" + Arrays.toString(packages));
        if (packages != null && packages.length > 0) {
            pkgName = packages[0];
        }

        //限定某些包名可以访问服务
        if (!"com.example.ipcdemo".equals(pkgName)) {
            return false;
        }

        return super.onTransact(code, data, reply, flags);
    }

    @Override
    public void setFlag(int flag) throws RemoteException {
        this.flag = flag;
        ZLog.d(TAG, "setFlag:" + flag + " " + Thread.currentThread() + " " + ZLog.getStackTraceString(new Throwable()));
    }

    @Override
    public int getFlag() throws RemoteException {
        ZLog.d(TAG, "getFlag:" + flag + " " + Thread.currentThread() + " " + ZLog.getStackTraceString(new Throwable()));
        return this.flag;
    }

    @Override
    public boolean checkPermission() throws RemoteException {
        ZLog.d(TAG, "checkPermission:" + Thread.currentThread());
        return false;
    }

    @Override
    public void startSilentInstall(ApkInfo info) throws RemoteException {
        ZLog.d(TAG, "startSilentInstall:" + info + " " + Thread.currentThread() + " " + ZLog.getStackTraceString(new Throwable()));
        startSilentInstallImp(info);
    }

    @Override
    public void startCommonInstall(ApkInfo info) throws RemoteException {
        ZLog.d(TAG, "startInstall:" + info + " " + Thread.currentThread() + " " + ZLog.getStackTraceString(new Throwable()));
        startInstallImp(info);
    }

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

    private void startSilentInstallImp(final ApkInfo info) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int n = listeners.beginBroadcast();
                for (int i = 0; i < n; i++) {
                    IApkInstallListener iApkInstallListener = listeners.getBroadcastItem(i);
                    if (iApkInstallListener != null) {
                        try {
                            ZLog.d(TAG, "1 start silent Install");
                            iApkInstallListener.onStatusChanged(1, "start silent Install");
                            ZLog.d(TAG, "2 check apk");
                            iApkInstallListener.onStatusChanged(2, "check apk");
                            ZLog.d(TAG, "3 Installing...");
                            info.setFilePath("" + info.getFilePath() + "...checked");
                            iApkInstallListener.onStatusChanged(3, "Installing...");
                            ZLog.d(TAG, "4 Install success");
                            iApkInstallListener.onStatusChanged(4, "Install success");
                            ZLog.d(TAG, "5 Install failed");
                            iApkInstallListener.onStatusChanged(5, "Install failed");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            ZLog.d(TAG, "startSilentInstallImp:" + e);
                        }
                    }
                }
                listeners.finishBroadcast();
            }
        }).start();
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
        ZLog.d(TAG, "registerListener:" + sum + " " + listener + " " + Thread.currentThread() + " " + ZLog.getStackTraceString(new Throwable()));
        ZLog.d(TAG, "registerListener asBinder:" + listener.asBinder());
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
        ZLog.d(TAG, "unregisterListener:" + sum + " " + listener + " " + Thread.currentThread());
    }
}
