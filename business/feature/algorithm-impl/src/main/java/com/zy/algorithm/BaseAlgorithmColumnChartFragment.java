package com.zy.algorithm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.View;


import com.zy.activity.BaseFragment;

public abstract class BaseAlgorithmColumnChartFragment extends BaseFragment {

    private static final String TAG = "BaseAlgorithmColumnChart";

    @Override
    protected int getLayout() {
        return R.layout.fragment_algorithm_column_chart_base;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    public interface StepListener {
        void nextStep();
    }

    public interface AnimationListener {
        void onAnimationEnd();
    }
}
