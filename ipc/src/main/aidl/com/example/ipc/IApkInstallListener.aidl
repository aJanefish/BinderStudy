// IApkInstallListener.aidl
package com.example.ipc;
interface IApkInstallListener {
    //回调安装过程
    void onStatusChanged(int status,String msg);
}
