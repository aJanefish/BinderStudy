package com.example.ipcdemo.activity;

import static com.example.ipc.util.Constant.PRE_TAG;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.ipcdemo.R;
import com.example.ipcdemo.proxy.ProxyActivityManagerUtils;
import com.example.ipcdemo.proxy.ProxyBinderUtils;
import com.example.ipcdemo.proxy.ProxyClipboardManagerUtils;
import com.example.ipcdemo.proxy.ProxyPMSUtils;

public class ProxyManagerDemoActivity extends AppCompatActivity {

    private static final String TAG = PRE_TAG + "ProxyManagerDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy_manager_demo);
        //Log.d(TAG, "onCreate:" + Log.getStackTraceString(new Throwable()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Log.d(TAG, "onStart:" + Log.getStackTraceString(new Throwable()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.d(TAG, "onResume:" + Log.getStackTraceString(new Throwable()));
    }

    public void test_get_clipboard(View view) {
        ProxyClipboardManagerUtils.getClipboardManager(this);
    }

    public void test_clipboard(View view) {
        ProxyClipboardManagerUtils.hockClipboardManager(this);
    }

    public void test_activity_manager(View view) {
        ProxyActivityManagerUtils.hockActivityManager(this);
    }

    public void get_activity_manager(View view) {
        ProxyActivityManagerUtils.getActivityManager(this);
    }

    public void get_pms_manager(View view) {
        ProxyPMSUtils.getPMS(this);
    }

    public void proxy_pms_manager(View view) {
        ProxyPMSUtils.hockPMS(this);
    }

    public void proxy_binder_test(View view) {
        ProxyBinderUtils.hockBinder001(this);
    }
}