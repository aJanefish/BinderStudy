package com.example.ipcdemo;

import android.content.Intent;
import android.os.Bundle;

import com.example.ipcdemo.activity.ProxyManagerDemoActivity;
import com.example.ipcdemo.classloader.ClassLoaderUtils;
import com.example.ipcdemo.study.activity.SubMainActivity;
import com.example.ipcdemo.util.BinderInternalUtils;
import com.zy.activity.BaseMenuActivity;
import com.zy.activity.bean.BaseBean;
import com.zy.algorithm.AlgorithmMenuActivity;
import com.zy.animation.AnimationMenuActivity;
import com.zy.feat.broadcast.BroadcastMainActivity;
import com.zy.feat.broadcast.ZBroadcastManager;
import com.zy.server.IPCMainActivity;

import java.util.List;

public class MainActivity extends BaseMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册广播
        ZBroadcastManager.registerReceiver(this);
        ZBroadcastManager.registerStaticReceiver(this);
    }

    @Override
    protected void init(List<BaseBean> list) {
        list.add(new BaseBean("IPC Client", 1));
        list.add(new BaseBean("Proxy SystemManager", 2));
        list.add(new BaseBean("Test ClassLoader", 3));
        list.add(new BaseBean("Test getContextObject", 4));
        list.add(new BaseBean("Test Activity", 5));
        list.add(new BaseBean("测试广播", 6));
        list.add(new BaseBean("动画", 7));
        list.add(new BaseBean("算法", 8));
    }

    @Override
    protected void clickItem(BaseBean bean) {
        if (bean.getCode() == 1) {
            startActivity(new Intent(this, IPCMainActivity.class));
        } else if (bean.getCode() == 2) {
            startActivity(new Intent(this, ProxyManagerDemoActivity.class));
        } else if (bean.getCode() == 3) {
            ClassLoaderUtils.testPathClassLoader();
        } else if (bean.getCode() == 4) {
            BinderInternalUtils.test();
        } else if (bean.getCode() == 5) {
            startActivity(new Intent(this, SubMainActivity.class));
        } else if (bean.getCode() == 6) {
            startActivity(new Intent(this, BroadcastMainActivity.class));
        } else if (bean.getCode() == 7) {
            startActivity(new Intent(this, AnimationMenuActivity.class));
        } else if (bean.getCode() == 8) {
            startActivity(new Intent(this, AlgorithmMenuActivity.class));
        }
    }
}