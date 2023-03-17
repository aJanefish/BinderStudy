package com.zy.algorithm.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.zy.activity.BaseFragment;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.zlog.ZLog;

import java.util.List;

public abstract class BaseAlgorithmFragment extends BaseFragment {

    private static final String TAG = "BaseAlgorithm";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initSetData();
        view.postDelayed(new Runnable() {

            @Override
            public void run() {
                startSort();
            }
        }, 2000);
    }

    protected List<SortStepBean> stepList = null;

    protected abstract List<SortStepBean> getStepBean();

    //初始化数据
    private void initSetData() {
        stepList = getStepBean();
        setSortData(stepList.get(0), true);
    }


    //默认10个数据
    protected abstract void setSortData(SortStepBean bean, boolean start);

    //初始化View
    protected abstract void initView(View view);

    protected void resetAll() {
        View[] views = getDataViewS();
        if (views == null) {
            return;
        }
        for (View view : views) {
            view.setTranslationX(0);
            view.setTranslationY(0);
            view.setScaleX(1f);
            view.setScaleY(1f);
        }
    }

    protected abstract View[] getDataViewS();


    private void startSort() {
        startSort(0);
    }

    protected void startSort(int index) {
        if (index >= stepList.size()) {
            return;
        }

        SortStepBean curStepBean = stepList.get(index);
        ZLog.d(TAG, index + " :" + curStepBean);


        if (curStepBean.isNeedAnimation()) {
            sortAnimation(curStepBean, new StepListener() {
                @Override
                public void nextStep() {
                    if (checkActivityDestroyed()) {
                        return;
                    }
                    setSortData(curStepBean, false);
                    resetAll();
                    startSort(index + 1);

                }
            });
        } else {
            setSortData(curStepBean, false);
            startSort(index + 1);
        }
    }

    //开始动画 - 子类实现
    protected abstract void sortAnimation(SortStepBean curStepBean, StepListener listener);


    public interface StepListener {
        void nextStep();
    }

    public interface AnimationListener {
        void onAnimationEnd();
    }
}
