package com.zy.animation.fragment;

import android.animation.Animator;

import com.zy.animation.animator.AnimatorFactory;

import java.util.List;

public class Two1AnimationDesFragment extends BaseAnimationDesFragment {
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
        return "平移+旋转";
    }


    @Override
    protected void initAnimatorData(List<Animator> items) {

        items.add(getPlayTogetherAnimator(translationYT, rotationX));
        items.add(getPlayTogetherAnimator(translationYT, rotationX1));
        items.add(getPlayTogetherAnimator(translationYT, rotationY));
        items.add(getPlayTogetherAnimator(translationYT, rotationY1));
        items.add(getPlayTogetherAnimator(translationYT, rotation));
        items.add(getPlayTogetherAnimator(translationYT, rotation1));
        items.add(getPlayTogetherAnimator(translationYT, alpha));
    }

    private Animator getPlayTogetherAnimator(AnimatorFactory... animators) {
        return getPlayTogetherAnimatorSet(animators).createAnimator();
    }
}