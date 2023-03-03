package com.example.binderservice;

import static com.example.ipc.IPCConstant.APK_TEST_ACTION;
import static com.example.ipc.IPCConstant.APK_TEST_ACTION_NEW_PROCESS;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class IPCStartServiceActivity extends AppCompatActivity {

    private static final String TAG = "IPCMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc_start_service);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void test_start_service(View view) {

        Intent intent = new Intent(APK_TEST_ACTION);
        //Caused by: java.lang.IllegalArgumentException: Service Intent must be explicit: Intent { act=com.test.zy.APK_INSTALL_ACTION }
        //设置服务端的包名
        intent.setPackage("com.example.binderservice");
        startService(intent);
    }

    public void test_stop_service(View view) {
        Intent intent = new Intent(APK_TEST_ACTION);
        //Caused by: java.lang.IllegalArgumentException: Service Intent must be explicit: Intent { act=com.test.zy.APK_INSTALL_ACTION }
        //设置服务端的包名
        intent.setPackage("com.example.binderservice");
        stopService(intent);
    }


    public void test_start_service_new_process(View view) {
        Intent intent = new Intent(APK_TEST_ACTION_NEW_PROCESS);
        //Caused by: java.lang.IllegalArgumentException: Service Intent must be explicit: Intent { act=com.test.zy.APK_INSTALL_ACTION }
        //设置服务端的包名
        intent.setPackage("com.example.binderservice");
        startService(intent);
    }

    public void test_stop_service_new_process(View view) {
        Intent intent = new Intent(APK_TEST_ACTION_NEW_PROCESS);
        //Caused by: java.lang.IllegalArgumentException: Service Intent must be explicit: Intent { act=com.test.zy.APK_INSTALL_ACTION }
        //设置服务端的包名
        intent.setPackage("com.example.binderservice");
        stopService(intent);
    }
}