package com.example.binderservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class IPCMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void test_bind_service_activity(View view) {
        startActivity(new Intent(this, IPCBindServiceActivity.class));
    }

    public void test_start_service_activity(View view) {
        startActivity(new Intent(this, IPCStartServiceActivity.class));
    }
}