package com.example.ipcdemo.proxy;

import static com.example.ipc.util.Constant.PRE_TAG;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProxyPMSUtils {
    private static final String TAG = PRE_TAG + "ProxyPMSUtils";

    public static void hockPMS(Context context) {
        try {
            //1 getClass android.os.ServiceManager
            Class<?> serviceManager = Class.forName("android.os.ServiceManager");
            Log.d(TAG, "serviceManager:" + serviceManager);


            //2.
            Method getServiceMethod = serviceManager.getDeclaredMethod("getService", String.class);
            Log.d(TAG, "getServiceMethod:" + getServiceMethod);

            //IBinder b = ServiceManager.getService("package");
            final IBinder getServiceMethodInvoke = (IBinder) getServiceMethod.invoke(null, Context.CLIPBOARD_SERVICE);
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

            sCache.put(Context.CLIPBOARD_SERVICE, proxyIBinder);

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "hockSystemManager catch" + e);
        } finally {
            Log.d(TAG, "hockSystemManager finally");
        }
    }

    public static PackageManager getPMS(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Log.d(TAG, "packageManager:" + packageManager.getClass());
        Log.d(TAG, "packageManager:" + packageManager);
        return packageManager;
    }
}
