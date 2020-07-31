package com.example.ipcdemo.util;

import android.os.Process;
import android.util.Log;

import static com.example.ipc.util.Constant.TAG_UTILS;

public class Utils {


    public static void showProcess() {
        Process.myPid();
        Process.myTid();
        Process.myUid();
        Log.d(TAG_UTILS, "Uid:" + Process.myUid() + " Pid:" + Process.myPid() + " Tid:" + Process.myTid());
    }
}
