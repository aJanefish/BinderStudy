package com.example.ipcdemo.classloader;


import static com.example.ipc.util.Constant.PRE_TAG;

import android.util.Log;

public class DemoClassLoader extends ClassLoader {

    private static final String TAG = PRE_TAG + "DemoClassLoader";

    public DemoClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Log.d(TAG, "loadClass 1:" + name);
        return super.loadClass(name);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Log.d(TAG, "loadClass 21:" + name + " " + resolve);
        Class<?> aClass = super.loadClass(name, resolve);
        Log.d(TAG, "loadClass 22:" + name + " " + aClass);
        return aClass;
    }
}
