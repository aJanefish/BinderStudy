package com.zy.animation.fragment;

import android.animation.Animator;

import com.zy.animation.animator.AnimatorFactory;

import java.util.List;

public class Two2AnimationDesFragment extends BaseAnimationDesFragment {
    @Override
    protected int getDuration() {
        return 2000;
    }

//    @Override
//    protected int getLayout() {
//        return R.layout.fragment_animation_two;
//    }

    @Override
    protected String getTitle() {
        return "缩放+旋转";
    }

    @Override
    protected void initAnimatorData(List<Animator> items) {

        items.add(getPlayTogetherAnimator(scaleXBig, rotationX));
        items.add(getPlayTogetherAnimator(scaleXBig, rotationX1));
        items.add(getPlayTogetherAnimator(scaleXBig, rotationY));
        items.add(getPlayTogetherAnimator(scaleXBig, rotationY1));
        items.add(getPlayTogetherAnimator(scaleXBig, rotation));
        items.add(getPlayTogetherAnimator(scaleXBig, rotation1));
        items.add(getPlayTogetherAnimator(scaleXBig, alpha));


    }

    private Animator getPlayTogetherAnimator(AnimatorFactory... animators) {
        return getPlayTogetherAnimatorSet(animators).createAnimator();
    }
}