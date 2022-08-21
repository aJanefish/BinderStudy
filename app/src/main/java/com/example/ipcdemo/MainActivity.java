package com.example.ipcdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ipcdemo.activity.IPCDemoActivity;
import com.example.ipcdemo.activity.ProxyManagerDemoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test_ipc_demo(View view) {
        startActivity(new Intent(this, IPCDemoActivity.class));
    }

    public void test_proxy_system_manager(View view) {
        startActivity(new Intent(this, ProxyManagerDemoActivity.class));
    }
}