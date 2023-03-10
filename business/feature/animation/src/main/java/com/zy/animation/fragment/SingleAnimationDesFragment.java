package com.zy.animation.fragment;

import android.animation.Animator;

import com.zy.animation.animator.AnimatorFactory;

import java.util.List;

public class SingleAnimationDesFragment extends BaseAnimationDesFragment {
    @Override
    protected int getDuration() {
        return 1000;
    }

    @Override
    protected String getTitle() {
        return "单一动画";
    }

    @Override
    protected void initAnimatorData(List<Animator> items) {
        for (AnimatorFactory animatorFactory : translationList) {
            items.add(animatorFactory.createAnimator());
        }
        for (AnimatorFactory animatorFactory : rotationList) {
            items.add(animatorFactory.createAnimator());
        }
        for (AnimatorFactory animatorFactory : alphaList) {
            items.add(animatorFactory.createAnimator());
        }
        for (AnimatorFactory animatorFactory : scaleList) {
            items.add(animatorFactory.createAnimator());
        }
    }
}