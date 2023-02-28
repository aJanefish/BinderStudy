package com.example.ipcdemo.classloader;


import com.zy.zlog.ZLog;

public class DemoClassLoader extends ClassLoader {

    private static final String TAG = "DemoClassLoader";

    public DemoClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        ZLog.d(TAG, "loadClass 1:" + name);
        return super.loadClass(name);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        ZLog.d(TAG, "loadClass 21:" + name + " " + resolve);
        Class<?> aClass = super.loadClass(name, resolve);
        ZLog.d(TAG, "loadClass 22:" + name + " " + aClass);
        return aClass;
    }
}
