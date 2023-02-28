package com.example.ipcdemo.study;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ipcdemo.R;
import com.example.ipcdemo.manager.DemoManager;
import com.example.ipcdemo.util.Utils;
import com.zy.zlog.ZLog;

public class TwoActivity extends AppCompatActivity {

    private final String TAG =  "TwoActivity";
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
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
    }


    @Override
    protected void onStart() {
        super.onStart();
        title.setText("sss:" + DemoManager.getValues());
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