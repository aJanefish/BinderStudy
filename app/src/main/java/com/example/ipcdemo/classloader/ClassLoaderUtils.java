package com.example.ipcdemo.classloader;

import static com.example.ipc.util.Constant.PRE_TAG;

import android.util.Log;

import java.lang.reflect.Field;

import dalvik.system.PathClassLoader;

public class ClassLoaderUtils {
    private static final String TAG = PRE_TAG + "CLoaderUtils";

    //2022-08-28 10:47:51.449  8776-8776  ZYDebug.CLoaderUtils    com.example.ipcdemo                  D  0 dalvik.system.PathClassLoader[DexPathList[[zip file "/data/app/com.example.ipcdemo-VSuwTvFQByoWp3ko-Ex_cw==/base.apk"],nativeLibraryDirectories=[/data/app/com.example.ipcdemo-VSuwTvFQByoWp3ko-Ex_cw==/lib/x86, /system/lib]]]
    //2022-08-28 10:47:51.449  8776-8776  ZYDebug.CLoaderUtils    com.example.ipcdemo                  D  1 java.lang.BootClassLoader@d522a8b
    public static void installHook() {
        ClassLoader classLoader = ClassLoaderUtils.class.getClassLoader();
        if (classLoader instanceof PathClassLoader) {
            ClassLoader parent = classLoader.getParent();
            DemoClassLoader demoClassLoader = new DemoClassLoader(parent);
            Log.d(TAG, "parent:" + parent);
            Log.d(TAG, "classLoader:" + classLoader);

            try {
                Field parentField = ClassLoader.class.getDeclaredField("parent");
                parentField.setAccessible(true);
                parentField.set(classLoader, demoClassLoader);
                parentField.setAccessible(false);
                Log.d(TAG, "installHook");
            } catch (NoSuchFieldException | IllegalAccessException e) {
                Log.d(TAG, "installHook:" + e);
            }

        }
    }

    public static void testPathClassLoader() {
        ClassLoader classLoader = ClassLoaderUtils.class.getClassLoader();
        if (classLoader instanceof PathClassLoader) {

            try {
                ((PathClassLoader)classLoader).loadClass("com.example.ipcdemo.activity.ProxyManagerDemoActivity");
                Log.d(TAG, "testPathClassLoader");
            } catch (ClassNotFoundException e) {
                Log.d(TAG, "testPathClassLoader:" + e);
            }

        }
    }
}
