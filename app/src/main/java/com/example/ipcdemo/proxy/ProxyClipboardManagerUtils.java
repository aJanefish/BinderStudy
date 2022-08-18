package com.example.ipcdemo.proxy;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.Map;

public class ProxyClipboardManagerUtils {
    private static final String TAG = "ProxyClipboardManagerUtils";

    public static void hockSystemManager(Context context) {

        try {
            Class<?> serviceManager = Class.forName("android.os.ServiceManager");
            Log.d(TAG, "serviceManager:" + serviceManager);

            //Field
            //private static java.util.Map android.os.ServiceManager.sCache

            Field sCacheField = serviceManager.getDeclaredField("sCache");
            sCacheField.setAccessible(true);
            Object sCache = sCacheField.get(null);
            Log.d(TAG, "sCache:" + sCache);




        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "hockSystemManager catch" + e);
        } finally {
            Log.d(TAG, "hockSystemManager finally");
        }
    }


    public static void hockClipboardManager(Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
        Log.d(TAG, "clipboardManager:" + clipboardManager.getClass());
        Log.d(TAG, "clipboardManager:" + clipboardManager);

    }

    public static void getClipboardManager(Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
    }

    public static void getClipboardManagerByApp(Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
    }
}
