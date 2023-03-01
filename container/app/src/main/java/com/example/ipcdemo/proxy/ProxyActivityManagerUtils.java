package com.example.ipcdemo.proxy;

import android.app.ActivityManager;
import android.content.Context;
import android.os.IBinder;

import com.zy.zlog.ZLog;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProxyActivityManagerUtils {
    private static final String TAG = "AManagerUtils";

    public static void hockActivityManager(Context context) {
        try {
            //1 getClass android.os.ServiceManager
            Class<?> serviceManager = Class.forName("android.os.ServiceManager");
            ZLog.d(TAG, "serviceManager:" + serviceManager);


            //2.
            Method getServiceMethod = serviceManager.getDeclaredMethod("getService", String.class);
            ZLog.d(TAG, "getServiceMethod:" + getServiceMethod);

            final IBinder getServiceMethodInvoke = (IBinder) getServiceMethod.invoke(null, Context.ACTIVITY_SERVICE);
            ZLog.d(TAG, "getServiceMethodInvoke:" + getServiceMethodInvoke);

            IBinder proxyIBinder = (IBinder) Proxy.newProxyInstance(getServiceMethodInvoke.getClass().getClassLoader(), new Class[]{IBinder.class}, new BinderProxyInvocationHandler(getServiceMethodInvoke));

            //3 put sCache
            //Field
            //private static java.util.Map android.os.ServiceManager.sCache
            Field sCacheField = serviceManager.getDeclaredField("sCache");
            sCacheField.setAccessible(true);
            Map<String, IBinder> sCache = (HashMap<String, IBinder>) sCacheField.get(null);
            ZLog.d(TAG, "sCache size:" + sCache.size());
            ZLog.d(TAG, "sCache:" + sCache);
            for (Object key : sCache.keySet().toArray()) {
                ZLog.d(TAG, "sCache:" + key + " " + sCache.get(key));
            }

            //sCache.put(Context.ACTIVITY_SERVICE, proxyIBinder);

        } catch (Exception e) {
            e.printStackTrace();
            ZLog.d(TAG, "hockSystemManager catch" + e);
        } finally {
            ZLog.d(TAG, "hockSystemManager finally");
        }
    }


    public static ActivityManager getActivityManager(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ZLog.d(TAG, "activityManager:" + activityManager.getClass());
        ZLog.d(TAG, "activityManager:" + activityManager);
        activityManager.getAppTasks();
        return activityManager;
    }
}
