// IApkInstallManager.aidl
package com.example.ipc;

// Declare any non-default types here with import statements
import com.example.ipc.ApkInfo;
import com.example.ipc.IApkInstallListener;

interface IApkInstallManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void setFlag(int flag);
    int getFlag();

    // 检查权限
    boolean checkPermission();

    // 静默安装
    void startSilentInstall(in ApkInfo info);
    // 普通安装
    void startInstall(in ApkInfo info);
    //设置安装回调
    void registerListener(IApkInstallListener listener);
    void unregisterListener(IApkInstallListener listener);
}
