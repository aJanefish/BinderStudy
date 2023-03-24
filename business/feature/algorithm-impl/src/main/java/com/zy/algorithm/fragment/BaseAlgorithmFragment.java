package com.zy.algorithm.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.zy.activity.BaseFragment;
import com.zy.algorithm.R;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.algorithm.controller.AnimatorController;
import com.zy.algorithm.mvp.IAlgorithmControllerView;
import com.zy.utils.ActivityUtils;
import com.zy.utils.AnimatorUtils;
import com.zy.zlog.ZLog;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAlgorithmFragment extends BaseFragment implements IAlgorithmControllerView {

    private static final String TAG = "BaseAlgorithm";

    protected static final int STATUES_UNSORTED = 1;
    protected static final int STATUES_SORTING = 2;
    protected static final int STATUES_SORTED = 3;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initSetData();

        startEnterPage();
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

    private void startEnterPage() {
        View enterContainer = getEnterContainer();
        View contextContainer = getContextContainer();
        enterContainer.setVisibility(View.VISIBLE);
        contextContainer.setVisibility(View.GONE);
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
        translationList.add(ObjectAnimator.ofFloat(enterContainer, "translationY", 0, -400));
        translationList.add(ObjectAnimator.ofFloat(enterContainer, "alpha", 1, 0));

        translationList.add(ObjectAnimator.ofFloat(contextContainer, "translationY", heightPixels, 0));
        translationList.add(ObjectAnimator.ofFloat(contextContainer, "alpha", 0, 1));


        AnimatorSet translation = new AnimatorSet();
        translation.playTogether(translationList);
        translation.setDuration(500);


        animatorList.add(translation);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatorList);
        animatorSet.setDuration(500);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                contextContainer.setVisibility(View.VISIBLE);
                contextContainer.setTranslationY(heightPixels);
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
    protected AnimatorController animatorController = null;

    protected abstract List<SortStepBean> getStepBean();

    protected abstract String getTitle();

    protected abstract String getEnterTitle();

    protected abstract String getDataDes();

    protected boolean getColorAni() {
        return true;
    }

    //初始化数据
    protected void initSetData() {
        stepList = getStepBean();
        animatorController.setStepList(stepList);
        setSortDataTips(stepList.get(0));
        setSortData(stepList.get(0), true);
    }

    protected void setSortDataTips(SortStepBean bean) {
    }


    //默认10个数据
    protected abstract void setSortData(SortStepBean bean, boolean start);

    //初始化View
    protected void initView(View view) {
        initTitle(view);
        initAnimatorController(view);
        initLayoutContainer(view);
    }

    private void initLayoutContainer(View view) {
        View sort_step_des = view.findViewById(R.id.sort_step_des);
        View sort_index_tips_container = view.findViewById(R.id.sort_index_tips_container);
        if(ActivityUtils.isScreenOrientationPortrait(getActivity())){
            sort_step_des.setVisibility(View.VISIBLE);
            sort_index_tips_container.setVisibility(View.VISIBLE);
        }else {
            sort_step_des.setVisibility(View.GONE);
            sort_index_tips_container.setVisibility(View.GONE);
        }
    }

    private void initAnimatorController(View view) {
        animatorController = new AnimatorController(view, this);
    }

    private void initTitle(View view) {
        TextView title = view.findViewById(R.id.sort_title);
        title.setText(getTitle());
        TextView enterTitle = view.findViewById(R.id.enter_title);
        enterTitle.setText(getEnterTitle());

        TextView sort_step_des = view.findViewById(R.id.sort_step_des);
        sort_step_des.setText(getDataDes());
    }

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
            view.setAlpha(1f);
            view.setVisibility(View.VISIBLE);
        }
    }

    protected abstract View[] getDataViewS();


    private void startSort() {
        startSort(animatorController.getCurIndex());
    }

    boolean animating = false;

    protected void startSort(int index) {
        if (index >= stepList.size()) {
            return;
        }
        if (animating) {
            ZLog.d(TAG, "animating  :" + true);
            return;
        }

        SortStepBean curStepBean = stepList.get(index);
        ZLog.d(TAG, index + " :" + curStepBean);

        setSortData(curStepBean, true);

        if (animatorController.isAuto()) { //如果是自动播放，就自动开始下一个
            play(index, curStepBean, true);
        } else if (animatorController.isSingle_play()) {
            play(index, curStepBean, false);
        } else { //debug 模式-只设置数据
            resetAll();
            setSortData(curStepBean, true);
        }
    }

    private void play(int index, SortStepBean curStepBean, boolean auto) {
        if (curStepBean.isNeedAnimation()) {
            animating = true;
            sortAnimation(index, curStepBean, new StepListener() {
                @Override
                public void nextStep() {
                    animating = false;

                    if (checkActivityDestroyed()) {
                        return;
                    }
                    resetAll();
                    setSortData(curStepBean, false);
                    if (auto) {
                        startSort(animatorController.geNextIndex());
                    }

                }
            });
        } else {
            resetAll();
            setSortData(curStepBean, false);
            if (auto) {
                startSort(animatorController.geNextIndex());
            }
        }
    }

    //开始动画 - 子类实现
    protected abstract void sortAnimation(int index, SortStepBean curStepBean, StepListener listener);


    protected final void startStepAnimator(List<Animator> animatorList, StepListener listener) {
        Animator animator = AnimatorUtils.getPlaySequentially(animatorList);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                super.onAnimationEnd(animation, isReverse);
                listener.nextStep();
            }
        });
        animator.start();
    }


    //动画控制 - start
    @Override
    public void ani_pause() {

    }

    @Override
    public void ani_continue() {
        startSort();
    }

    @Override
    public void ani_play_single() {
        startSort();
    }

    @Override
    public void ani_play_zero() {
        startSort();
    }

    @Override
    public void ani_last() {
        startSort();
    }

    @Override
    public void ani_next() {
        startSort();
    }

    @Override
    public void ani_fast() {

    }

    @Override
    public void ani_low() {

    }
    //动画控制 - end


    public interface StepListener {
        void nextStep();
    }

    public interface AnimationListener {
        void onAnimationEnd();
    }
}
