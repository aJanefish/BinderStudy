package com.zy.algorithm.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.zy.activity.BaseFragment;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.zlog.ZLog;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAlgorithmFragment extends BaseFragment {

    private static final String TAG = "BaseAlgorithm";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initSetData();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                //入场动画
                startEnterAnimation(new AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startSort();
                            }
                        }, 500);
                    }
                });
            }
        }, 2000);
    }

    protected View getEnterContainer() {
        return null;
    }

    protected View getContextContainer() {
        return null;
    }

    private void startEnterAnimation(AnimationListener animationListener) {
        View enterContainer = getEnterContainer();
        View contextContainer = getContextContainer();
        if (enterContainer == null || contextContainer == null) {
            animationListener.onAnimationEnd();
            return;
        }

        List<Animator> animatorList = new ArrayList<>();

        float[] shakes = new float[]{1f, 1.05f, 1, 0.95F, 1f, 1.05f, 1f};

        List<Animator> shakeList = new ArrayList<>();
        shakeList.add(ObjectAnimator.ofFloat(enterContainer, "scaleX", shakes));
        shakeList.add(ObjectAnimator.ofFloat(enterContainer, "scaleY", shakes));

        AnimatorSet shakeAnimatorSet = new AnimatorSet();
        shakeAnimatorSet.playTogether(shakeList);
        shakeAnimatorSet.setDuration(500);


        animatorList.add(shakeAnimatorSet);

        //平移
        List<Animator> translationList = new ArrayList<>();
        translationList.add(ObjectAnimator.ofFloat(enterContainer, "translationY", 0, -800));
        translationList.add(ObjectAnimator.ofFloat(enterContainer, "alpha", 1, 0));

        translationList.add(ObjectAnimator.ofFloat(contextContainer, "translationY", 800, 0));
        translationList.add(ObjectAnimator.ofFloat(contextContainer, "alpha", 0, 1));


        AnimatorSet translation = new AnimatorSet();
        translation.playTogether(translationList);
        translation.setDuration(500);


        animatorList.add(translation);

        contextContainer.setTranslationY(-800);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatorList);
        animatorSet.setDuration(500);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                contextContainer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                super.onAnimationEnd(animation, isReverse);
                enterContainer.setVisibility(View.GONE);
                animationListener.onAnimationEnd();
            }
        });
        animatorSet.start();

    }

    protected List<SortStepBean> stepList = null;

    protected abstract List<SortStepBean> getStepBean();

    protected abstract String getTitle();

    protected abstract String getEnterTitle();

    //初始化数据
    protected void initSetData() {
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
            sortAnimation(index, curStepBean, new StepListener() {
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
    protected abstract void sortAnimation(int index, SortStepBean curStepBean, StepListener listener);


    protected final void startStepAnimator(Animator animator, StepListener listener) {
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                super.onAnimationEnd(animation, isReverse);
                listener.nextStep();
            }
        });
        animator.start();
    }

    public interface StepListener {
        void nextStep();
    }

    public interface AnimationListener {
        void onAnimationEnd();
    }
}
