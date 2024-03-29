package com.zy.algorithm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import com.zy.activity.BaseMainActivity;
import com.zy.algorithm.sort.bubble.BubbleSortColumnChartV1Fragment;
import com.zy.algorithm.sort.bubble.BubbleSortColumnChartV2Fragment;
import com.zy.algorithm.sort.bubble.BubbleSortColumnChartV3Fragment;
import com.zy.algorithm.sort.bubble.BubbleSortColumnChartV4Fragment;
import com.zy.algorithm.sort.bubble.BubbleSortV1Fragment;
import com.zy.algorithm.sort.bubble.BubbleSortV2Fragment;
import com.zy.algorithm.sort.insertion.InsertionSortV1Fragment;
import com.zy.algorithm.sort.insertion.InsertionSortV2Fragment;
import com.zy.algorithm.sort.insertion.InsertionSortV3Fragment;
import com.zy.algorithm.sort.insertion.ShellInsertionSortV1Fragment;
import com.zy.algorithm.sort.insertion.ShellInsertionSortV2Fragment;
import com.zy.algorithm.sort.insertion.ShellInsertionSortV3Fragment;
import com.zy.algorithm.sort.select.SelectSortV1Fragment;
import com.zy.algorithm.sort.select.SelectSortV2Fragment;
import com.zy.algorithm.sort.select.SelectSortV3Fragment;

public class AlgorithmMainActivity extends BaseMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment getContainerFragment() {
        int code = getIntent().getIntExtra("code", 0);

        Fragment fragment;
        if (code == 1) {
            fragment = new BubbleSortV1Fragment();
        } else if (code == 2) {
            fragment = new BubbleSortV2Fragment();
        } else if (code == 3) {
            fragment = new BubbleSortColumnChartV1Fragment();
        } else if (code == 4) {
            fragment = new BubbleSortColumnChartV2Fragment();
        } else if (code == 5) {
            fragment = new BubbleSortColumnChartV3Fragment();
        } else if (code == 6) {
            fragment = new BubbleSortColumnChartV4Fragment();
        } else if (code == 7) {
            fragment = new SelectSortV1Fragment();
        } else if (code == 8) {
            fragment = new SelectSortV2Fragment();
        } else if (code == 9) {
            fragment = new SelectSortV3Fragment();
        } else if (code == 10) {
            fragment = new InsertionSortV1Fragment();
        } else if (code == 11) {
            fragment = new InsertionSortV2Fragment();
        } else if (code == 12) {
            fragment = new InsertionSortV3Fragment();
        } else if (code == 13) {
            fragment = new ShellInsertionSortV1Fragment();
        } else if (code == 14) {
            fragment = new ShellInsertionSortV2Fragment();
        } else if (code == 15) {
            fragment = new ShellInsertionSortV3Fragment();
        } else {
            fragment = new BubbleSortV1Fragment();
        }

        return fragment;
    }
}