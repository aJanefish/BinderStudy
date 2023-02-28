package com.example.ipcdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ipcdemo.R;
import com.example.ipcdemo.manager.DemoManager;
import com.example.ipcdemo.util.Utils;
import com.zy.zlog.ZLog;

public class OneActivity extends AppCompatActivity {

    private final String TAG = "OneActivity";
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        title = findViewById(R.id.one_title);
        Button button_one = findViewById(R.id.one_button);
        button_one.setOnClickListener(new View.OnClickListener() {
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

}