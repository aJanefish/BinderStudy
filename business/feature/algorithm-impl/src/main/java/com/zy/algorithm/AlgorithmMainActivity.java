package com.zy.algorithm;

import android.support.v4.app.Fragment;

import com.zy.activity.BaseMainActivity;
import com.zy.algorithm.sort.BubbleSortV1Fragment;
import com.zy.algorithm.sort.BubbleSortV2Fragment;

public class AlgorithmMainActivity extends BaseMainActivity {

    @Override
    protected Fragment getContainerFragment() {
        int code = getIntent().getIntExtra("code", 0);

        Fragment fragment;
        if (code == 1) {
            fragment = new BubbleSortV1Fragment();
        } else if (code == 2) {
            fragment = new BubbleSortV2Fragment();
        } else {
            fragment = new BubbleSortV1Fragment();
        }

        return fragment;
    }
}