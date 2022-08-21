package com.example.ipcdemo.proxy;

import static com.example.ipc.util.Constant.PRE_TAG;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class IClipboardInvocationHandler implements InvocationHandler {

    private static final String TAG = PRE_TAG + "IClipboard";

    private final Object IClipboard$Stub$Proxy;

    public IClipboardInvocationHandler(Object IClipboard$Stub$Proxy) {
        this.IClipboard$Stub$Proxy = IClipboard$Stub$Proxy;
    }

    //IClipboardInvocationHandler e java.lang.IllegalArgumentException: Expected receiver of type android.content.IClipboard, but got android.os.BinderProxy
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // FIXME: 2022/8/21 服务处理真正的方法，可以拦截业务处理
        try {
            String name = method.getName();
            Log.d(TAG, "IClipboardInvocationHandler invoke name:" + name);
            Log.d(TAG, "IClipboardInvocationHandler invoke method:" + method);
            Log.d(TAG, "IClipboardInvocationHandler invoke args:" + Arrays.toString(args));

            Object invoke = method.invoke(IClipboard$Stub$Proxy, args);
            Log.d(TAG, "IClipboardInvocationHandler invoke result:" + invoke);
            return invoke;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "IClipboardInvocationHandler e " + e);
        }
        return null;
    }
}