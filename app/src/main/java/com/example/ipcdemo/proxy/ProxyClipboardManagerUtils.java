package com.example.ipcdemo.proxy;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.IBinder;

import com.zy.zlog.ZLog;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProxyClipboardManagerUtils {
    private static final String TAG = "ProxyUtils";

    public static void hockClipboardManager(Context context) {
        try {
            //1 getClass android.os.ServiceManager
            Class<?> serviceManager = Class.forName("android.os.ServiceManager");
            ZLog.d(TAG, "serviceManager:" + serviceManager);


            //2.
            Method getServiceMethod = serviceManager.getDeclaredMethod("getService", String.class);
            ZLog.d(TAG, "getServiceMethod:" + getServiceMethod);

            final IBinder getServiceMethodInvoke = (IBinder) getServiceMethod.invoke(null, Context.CLIPBOARD_SERVICE);
            ZLog.d(TAG, "getServiceMethodInvoke:" + getServiceMethodInvoke);

            IBinder proxyIBinder = (IBinder) Proxy.newProxyInstance(getServiceMethodInvoke.getClass().getClassLoader(),
                    new Class[]{IBinder.class},
                    new BinderProxyInvocationHandler(getServiceMethodInvoke));

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

            sCache.put(Context.CLIPBOARD_SERVICE, proxyIBinder);

        } catch (Exception e) {
            e.printStackTrace();
            ZLog.d(TAG, "hockSystemManager catch" + e);
        } finally {
            ZLog.d(TAG, "hockSystemManager finally");
        }
    }


    public static ClipboardManager getClipboardManager(Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ZLog.d(TAG, "clipboardManager:" + clipboardManager.getClass());
        ZLog.d(TAG, "clipboardManager:" + clipboardManager);
        return clipboardManager;
    }
}
