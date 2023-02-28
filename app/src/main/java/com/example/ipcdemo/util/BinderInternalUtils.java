package com.example.ipcdemo.util;

import com.zy.zlog.ZLog;

import java.lang.reflect.Method;

public class BinderInternalUtils {
    private static final String TAG = "BinderInternalUtils";

    public static void test() {
        try {
            Class<?> binderInternal = Class.forName("com.android.internal.os.BinderInternal");
            Method method = binderInternal.getMethod("getContextObject");
            method.setAccessible(true);
            Object object = method.invoke(null);
            ZLog.d(TAG, "" + object);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
