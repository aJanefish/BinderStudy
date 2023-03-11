package com.zy.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class BaseMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_main);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.base_main_container, getContainerFragment())
                .commitNowAllowingStateLoss();
    }

    protected abstract Fragment getContainerFragment();
}