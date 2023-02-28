package com.zy.zlog;

import android.util.Log;

import com.zy.utils.Constant;

//功能-添加统一前缀
public class ZLog {


    public static void d(String tag, Object v) {
        Log.d(Constant.PRE_TAG + tag, "" + v);
    }

    public static void i(String tag, Object v) {
        Log.i(Constant.PRE_TAG + tag, "" + v);
    }

    public static void e(String tag, Object v) {
        Log.e(Constant.PRE_TAG + tag, "" + v);
    }

    public static String getStackTraceString(Throwable tr) {
        return Log.getStackTraceString(tr);
    }
}
