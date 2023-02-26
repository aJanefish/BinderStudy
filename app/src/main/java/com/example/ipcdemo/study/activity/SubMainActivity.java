package com.example.ipcdemo.study.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ipc.util.Constant;
import com.example.ipcdemo.R;

public class SubMainActivity extends AppCompatActivity {

    private final String TAG = Constant.PRE_TAG + "SubMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void start_in_process_self(View view) {
        startActivity(new Intent(this, SubMainActivity.class));
    }

    public void start_in_process_activity(View view) {
        startActivity(new Intent(this, SubInProcessActivity.class));
    }

    public void start_new_process_activity(View view) {
        startActivity(new Intent(this, SubInNewProcessActivity.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}