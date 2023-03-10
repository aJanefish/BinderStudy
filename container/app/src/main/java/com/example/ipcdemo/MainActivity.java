package com.example.ipcdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ipcdemo.activity.ProxyManagerDemoActivity;
import com.example.ipcdemo.classloader.ClassLoaderUtils;
import com.example.ipcdemo.study.activity.SubMainActivity;
import com.example.ipcdemo.util.BinderInternalUtils;
import com.zy.animation.AnimationMenuActivity;
import com.zy.feat.broadcast.BroadcastMainActivity;
import com.zy.feat.broadcast.ZBroadcastManager;
import com.zy.server.IPCMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册广播
        ZBroadcastManager.registerReceiver(this);
        ZBroadcastManager.registerStaticReceiver(this);
    }

    public void test_ipc_demo(View view) {
        startActivity(new Intent(this, IPCMainActivity.class));
    }

    public void test_proxy_system_manager(View view) {
        startActivity(new Intent(this, ProxyManagerDemoActivity.class));
    }

    public void test_class_loader(View view) {
        //ClassLoaderUtils.installHook();
        ClassLoaderUtils.testPathClassLoader();

    }

    public void test_getContextObject(View view) {
        BinderInternalUtils.test();
    }

    //测试Activity
    public void test_activity(View view) {
        startActivity(new Intent(this, SubMainActivity.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void test_broadcast(View view) {
        startActivity(new Intent(this, BroadcastMainActivity.class));
    }


    private void test() {
        int size = 48;
        Object[] objects = new Object[48];
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            Object actions = objects[i];
            stringBuffer.append(i).append(" ").append(actions).append("\n");
        }
        stringBuffer.toString();
    }

    public void test_animation(View view) {
        startActivity(new Intent(this, AnimationMenuActivity.class));
    }
}