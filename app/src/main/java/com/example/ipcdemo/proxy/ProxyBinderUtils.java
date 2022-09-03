package com.example.ipcdemo.proxy;

import static com.example.ipc.util.Constant.PRE_TAG;

import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Method;

public class ProxyBinderUtils {
    private static final String TAG = PRE_TAG + "BinderUtils";

    public static void hockBinder001(Context context) {
        try {
            //1 getClass com.android.internal.os.BinderInternal
            Class<?> binderInternalClass = Class.forName("com.android.internal.os.BinderInternal");
            //hockBinder001 binderInternalClass:class com.android.internal.os.BinderInternal
            Log.d(TAG, "hockBinder001 binderInternalClass:" + binderInternalClass);

            Method getContextObjectMethod = binderInternalClass.getDeclaredMethod("getContextObject");
            //hockBinder001 getContextObjectMethodInvoke:android.os.BinderProxy@d98c1b5
            IBinder getContextObjectMethodInvoke = (IBinder) getContextObjectMethod.invoke(null);

            Log.d(TAG, "hockBinder001 getContextObjectMethodInvoke:" + getContextObjectMethodInvoke);


        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "hockBinder001 catch" + e);
        } finally {
            Log.d(TAG, "hockBinder001 finally");
        }
    }
}
