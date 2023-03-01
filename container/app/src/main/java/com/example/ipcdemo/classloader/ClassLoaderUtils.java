package com.example.ipcdemo.classloader;

import com.zy.zlog.ZLog;

import java.lang.reflect.Field;

import dalvik.system.PathClassLoader;

public class ClassLoaderUtils {
    private static final String TAG = "CLoaderUtils";

    //2022-08-28 10:47:51.449  8776-8776  ZYDebug.CLoaderUtils    com.example.ipcdemo                  D  0 dalvik.system.PathClassLoader[DexPathList[[zip file "/data/app/com.example.ipcdemo-VSuwTvFQByoWp3ko-Ex_cw==/base.apk"],nativeLibraryDirectories=[/data/app/com.example.ipcdemo-VSuwTvFQByoWp3ko-Ex_cw==/lib/x86, /system/lib]]]
    //2022-08-28 10:47:51.449  8776-8776  ZYDebug.CLoaderUtils    com.example.ipcdemo                  D  1 java.lang.BootClassLoader@d522a8b
    public static void installHook() {
        ClassLoader classLoader = ClassLoaderUtils.class.getClassLoader();
        if (classLoader instanceof PathClassLoader) {
            ClassLoader parent = classLoader.getParent();
            DemoClassLoader demoClassLoader = new DemoClassLoader(parent);
            ZLog.d(TAG, "parent:" + parent);
            ZLog.d(TAG, "classLoader:" + classLoader);

            try {
                Field parentField = ClassLoader.class.getDeclaredField("parent");
                parentField.setAccessible(true);
                parentField.set(classLoader, demoClassLoader);
                parentField.setAccessible(false);
                ZLog.d(TAG, "installHook");
            } catch (NoSuchFieldException | IllegalAccessException e) {
                ZLog.d(TAG, "installHook:" + e);
            }

        }
    }

    public static void testPathClassLoader() {
        ClassLoader classLoader = ClassLoaderUtils.class.getClassLoader();
        if (classLoader instanceof PathClassLoader) {

            try {
                ((PathClassLoader) classLoader).loadClass("com.example.ipcdemo.activity.ProxyManagerDemoActivity");
                ZLog.d(TAG, "testPathClassLoader");
            } catch (ClassNotFoundException e) {
                ZLog.d(TAG, "testPathClassLoader:" + e);
            }

        }
    }
}
