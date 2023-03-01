package com.example.ipcdemo.study.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ipcdemo.R;
import com.example.ipcdemo.util.Utils;
import com.zy.zlog.ZLog;

public class SubInNewProcessActivity extends AppCompatActivity {

    private String TAG = "SubInNewProcessActivity";
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_new_process);
        title = findViewById(R.id.two_title);
        Button button_two = findViewById(R.id.two_button);
        button_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZLog.d(TAG, "showTips");
                Utils.showProcess();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Utils.showProcess();
                    }
                }).start();
            }
        });
        findViewById(R.id.one_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SubInNewProcessActivity.this, SubMainActivity.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    public void twoShowTips(View view) {
        Utils.showProcess();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Utils.showProcess();
            }
        }).start();
    }
}