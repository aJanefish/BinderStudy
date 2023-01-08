package com.example.ipcdemo.util;

import static com.example.ipc.util.Constant.PRE_TAG;

import android.util.Log;

import java.lang.reflect.Method;

public class BinderInternalUtils {
    private static final String TAG = PRE_TAG + "BinderInternalUtils";

    public static void test() {
        try {
            Class<?> binderInternal = Class.forName("com.android.internal.os.BinderInternal");
            Method method = binderInternal.getMethod("getContextObject");
            method.setAccessible(true);
            Object object = method.invoke(null);
            Log.d(TAG, "" + object);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
