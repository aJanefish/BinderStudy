package com.example.ipcdemo.proxy;

import com.zy.zlog.ZLog;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class IClipboardInvocationHandler implements InvocationHandler {

    private static final String TAG = "IClipboard";

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
            ZLog.d(TAG, "IClipboardInvocationHandler invoke name:" + name);
            ZLog.d(TAG, "IClipboardInvocationHandler invoke method:" + method);
            ZLog.d(TAG, "IClipboardInvocationHandler invoke args:" + Arrays.toString(args));

            Object invoke = method.invoke(IClipboard$Stub$Proxy, args);
            ZLog.d(TAG, "IClipboardInvocationHandler invoke result:" + invoke);
            return invoke;
        } catch (Exception e) {
            e.printStackTrace();
            ZLog.e(TAG, "IClipboardInvocationHandler e " + e);
        }
        return null;
    }
}