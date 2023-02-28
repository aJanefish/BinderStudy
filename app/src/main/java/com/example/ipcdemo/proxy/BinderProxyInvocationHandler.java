package com.example.ipcdemo.proxy;


import android.os.IBinder;
import android.os.IInterface;

import com.zy.zlog.ZLog;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class BinderProxyInvocationHandler implements InvocationHandler {

    private static final String TAG =  "BinderProxy";
    private final IBinder originProxy;
    private final Class<?> mIClipboardClass;
    private final Object IClipboard$Stub$Proxy;

    public BinderProxyInvocationHandler(IBinder originProxy) throws Exception {
        this.originProxy = originProxy;

        //android.content.IClipboard
        mIClipboardClass = Class.forName("android.content.IClipboard");
        ZLog.d(TAG, "mIClipboardClass:" + mIClipboardClass);
        Class IClipboard$Stub = Class.forName("android.content.IClipboard$Stub");
        ZLog.d(TAG, "IClipboard$Stub:" + IClipboard$Stub);
        Method asInterfaceMethod = IClipboard$Stub.getDeclaredMethod("asInterface", IBinder.class);
        ZLog.d(TAG, "asInterfaceMethod:" + asInterfaceMethod);
        IClipboard$Stub$Proxy = asInterfaceMethod.invoke(null, originProxy);
        ZLog.d(TAG, "asInterfaceMethod IClipboard$Stub$Proxy:" + IClipboard$Stub$Proxy);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();

        ZLog.d(TAG, "BinderProxyInvocationHandler invoke name:" + name);
        ZLog.d(TAG, "BinderProxyInvocationHandler invoke method:" + method + " args:" + Arrays.toString(args));

        if ("queryLocalInterface".equals(name)) {
            try {
                //返回代理对象
                Object clipboard = Proxy.newProxyInstance(
                        originProxy.getClass().getClassLoader(),
                        new Class[]{mIClipboardClass, IInterface.class},
                        new IClipboardInvocationHandler(IClipboard$Stub$Proxy)
                );
                ZLog.d(TAG, "invoke clipboard:" + clipboard.getClass());
                ZLog.d(TAG, "invoke clipboard:" + clipboard);
                return clipboard;
            } catch (Exception e) {
                e.printStackTrace();
                ZLog.e(TAG, "BinderProxyInvocationHandler invoke " + e);
            }
        }

        Object tmp = method.invoke(originProxy, args);
        ZLog.d(TAG, "invoke tmp:" + tmp);
        return tmp;
    }
}
