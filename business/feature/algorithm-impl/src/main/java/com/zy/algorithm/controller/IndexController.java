package com.zy.algorithm.controller;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.zy.algorithm.R;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.algorithm.fragment.BaseAlgorithmFragment;
import com.zy.utils.AnimatorUtils;

import java.util.ArrayList;
import java.util.List;

public class IndexController {

    //下标
    protected View sort_first_index;
    protected View sort_second_index;
    protected View sort_min_index;

    protected float stepOne;

    public IndexController(View root, float stepOne) {
        this.stepOne = stepOne;
        sort_first_index = root.findViewById(R.id.sort_first_index);
        sort_second_index = root.findViewById(R.id.sort_second_index);
        sort_min_index = root.findViewById(R.id.sort_min_index);
    }

    //下标动画
    public void sortIndexAnimation(SortStepBean curStepBean, BaseAlgorithmFragment.AnimationListener listener) {
        int firstIndex = curStepBean.getFirstIndex();
        int secondIndex = curStepBean.getSecondIndex();
        int opFirstIndex = curStepBean.getOpFirstIndex();


        Animator firstIndexAni = getFirstIndexAnimator(firstIndex);
        Animator secondIndexAni = getSecondIndexAnimator(secondIndex);
        Animator minIndexAni = getMinIndexAnimator(opFirstIndex);

        List<Animator> list = new ArrayList<>();
        list.add(firstIndexAni);
        list.add(secondIndexAni);
        list.add(minIndexAni);

        AnimatorUtils.togetherStart(list, 3, new AnimatorUtils.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                super.onAnimationEnd();
                listener.onAnimationEnd();
            }
        });
    }

    public void setMinIndex(int newIndex) {
        Animator animator = getMinIndexAnimator(newIndex);
        animator.start();
    }


    public Animator getMinIndexAnimator(int newIndex) {
        return ObjectAnimator.ofFloat(sort_min_index, "translationX", sort_min_index.getTranslationX(), newIndex * stepOne);
    }

    private Animator getFirstIndexAnimator(int newIndex) {
        return ObjectAnimator.ofFloat(sort_first_index, "translationX", sort_first_index.getTranslationX(), newIndex * stepOne);
    }

    private Animator getSecondIndexAnimator(int newIndex) {
        return ObjectAnimator.ofFloat(sort_second_index, "translationX", sort_second_index.getTranslationX(), newIndex * stepOne);
    }
}
