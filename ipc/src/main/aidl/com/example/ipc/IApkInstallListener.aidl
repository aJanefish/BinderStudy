// IApkInstallListener.aidl
package com.example.ipc;

// Declare any non-default types here with import statements

interface IApkInstallListener {

    //回调安装过程
    void onStatusChanged(int status,String msg);
}
