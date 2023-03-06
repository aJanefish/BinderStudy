package com.zy.feat.broadcast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BroadcastMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_main);
    }

    public void test_register_receiver(View view) {
        ZBroadcastManager.registerStaticReceiver(this);
    }

    public void test_unregister_receiver(View view) {
        ZBroadcastManager.unregisterStaticReceiver(this);
    }

    public void test_send_broadcast(View view) {
        ZBroadcastManager.sendBroadcast(this);
    }

    public void test_register_receiver_auto(View view) {
        ZBroadcastManager.registerReceiver(this);
    }
}