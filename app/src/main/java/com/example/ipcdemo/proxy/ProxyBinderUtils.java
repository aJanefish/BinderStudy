package com.example.ipcdemo.proxy;

import android.content.Context;
import android.os.IBinder;


import com.zy.zlog.ZLog;

import java.lang.reflect.Method;

public class ProxyBinderUtils {
    private static final String TAG = "BinderUtils";

    public static void hockBinder001(Context context) {
        try {
            //1 getClass com.android.internal.os.BinderInternal
            Class<?> binderInternalClass = Class.forName("com.android.internal.os.BinderInternal");
            //hockBinder001 binderInternalClass:class com.android.internal.os.BinderInternal
            ZLog.d(TAG, "hockBinder001 binderInternalClass:" + binderInternalClass);

            Method getContextObjectMethod = binderInternalClass.getDeclaredMethod("getContextObject");
            //hockBinder001 getContextObjectMethodInvoke:android.os.BinderProxy@d98c1b5
            IBinder getContextObjectMethodInvoke = (IBinder) getContextObjectMethod.invoke(null);

            ZLog.d(TAG, "hockBinder001 getContextObjectMethodInvoke:" + getContextObjectMethodInvoke);


        } catch (Exception e) {
            e.printStackTrace();
            ZLog.d(TAG, "hockBinder001 catch" + e);
        } finally {
            ZLog.d(TAG, "hockBinder001 finally");
        }
    }
}
