// IApkInstallManager.aidl
package com.example.ipc;
import com.example.ipc.ApkInfo;
import com.example.ipc.IApkInstallListener;

interface IApkInstallManager {
    void setFlag(int flag);
    int getFlag();
    // 检查权限
    boolean checkPermission();
    // 静默安装
    void startSilentInstall(in ApkInfo info);
    // 普通安装
    void startCommonInstall(in ApkInfo info);
    //设置安装回调
    void registerListener(IApkInstallListener listener);
    void unregisterListener(IApkInstallListener listener);
}
