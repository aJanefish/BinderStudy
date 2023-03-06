package com.zy.feat.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zy.zlog.ZLog;

public class ZBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ZLog.d("ZBroadcast", "" + intent);
    }
}
