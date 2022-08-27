package com.example.ipcdemo.proxy;

import static com.example.ipc.util.Constant.PRE_TAG;

import android.app.ActivityManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProxyActivityManagerUtils {
    private static final String TAG = PRE_TAG + "AManagerUtils";

    public static void hockActivityManager(Context context) {
        try {
            //1 getClass android.os.ServiceManager
            Class<?> serviceManager = Class.forName("android.os.ServiceManager");
            Log.d(TAG, "serviceManager:" + serviceManager);


            //2.
            Method getServiceMethod = serviceManager.getDeclaredMethod("getService", String.class);
            Log.d(TAG, "getServiceMethod:" + getServiceMethod);

            final IBinder getServiceMethodInvoke = (IBinder) getServiceMethod.invoke(null, Context.ACTIVITY_SERVICE);
            Log.d(TAG, "getServiceMethodInvoke:" + getServiceMethodInvoke);

            IBinder proxyIBinder = (IBinder) Proxy.newProxyInstance(getServiceMethodInvoke.getClass().getClassLoader(),
                    new Class[]{IBinder.class},
                    new BinderProxyInvocationHandler(getServiceMethodInvoke));

            //3 put sCache
            //Field
            //private static java.util.Map android.os.ServiceManager.sCache
            Field sCacheField = serviceManager.getDeclaredField("sCache");
            sCacheField.setAccessible(true);
            Map<String, IBinder> sCache = (HashMap<String, IBinder>) sCacheField.get(null);
            Log.d(TAG, "sCache size:" + sCache.size());
            Log.d(TAG, "sCache:" + sCache);
            for (Object key : sCache.keySet().toArray()) {
                Log.d(TAG, "sCache:" + key + " " + sCache.get(key));
            }

            //sCache.put(Context.ACTIVITY_SERVICE, proxyIBinder);

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "hockSystemManager catch" + e);
        } finally {
            Log.d(TAG, "hockSystemManager finally");
        }
    }


    public static ActivityManager getActivityManager(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        Log.d(TAG, "activityManager:" + activityManager.getClass());
        Log.d(TAG, "activityManager:" + activityManager);
        activityManager.getAppTasks();
        return activityManager;
    }
}
