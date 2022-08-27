package com.example.ipcdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ipcdemo.R;
import com.example.ipcdemo.proxy.ProxyActivityManagerUtils;
import com.example.ipcdemo.proxy.ProxyClipboardManagerUtils;

public class ProxyManagerDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy_manager_demo);
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
}