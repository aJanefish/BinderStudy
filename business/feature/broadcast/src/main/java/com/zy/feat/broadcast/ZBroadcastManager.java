package com.zy.feat.broadcast;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class ZBroadcastManager {

    private static final String Z_STATIC_ACTION = "com.zy.test.Broadcast_Static_Action";
    private static final String Z_ACTION = "com.zy.test.Broadcast_Action";
    private static final ZBroadcastReceiver staticBR = new ZBroadcastReceiver();

    public static void registerStaticReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter(Z_STATIC_ACTION);
        context.registerReceiver(staticBR, intentFilter);
    }

    public static void registerReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter(Z_ACTION);
        ZBroadcastReceiver broadcastReceiver = new ZBroadcastReceiver();
        context.registerReceiver(broadcastReceiver, intentFilter);
    }

    public static void unregisterStaticReceiver(Context context) {
        context.unregisterReceiver(staticBR);
    }

    public static void sendBroadcast(Context context) {
        Intent intent = new Intent(Z_ACTION);
        intent.putExtra("time", System.currentTimeMillis());
        context.sendBroadcast(intent);
    }

    //发送黏性广播
    public static void sendStickyBroadcast(Context context) {
        Intent intent = new Intent(Z_ACTION);
        intent.putExtra("time", System.currentTimeMillis());
        context.sendStickyBroadcast(intent);
    }
}
