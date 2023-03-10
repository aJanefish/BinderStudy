package com.zy.animation.fragment;

import android.animation.Animator;

import com.zy.animation.R;
import com.zy.animation.animator.AnimatorFactory;

import java.util.List;

public class TwoAnimationDesFragment extends BaseAnimationDesFragment {
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
        return "平移+缩放";
    }

    @Override
    protected void initAnimatorData(List<Animator> items) {

        items.add(getPlayTogetherAnimator(translationYT, scaleXBig));
        items.add(getPlayTogetherAnimator(translationYT, scaleYBig));
        items.add(getPlayTogetherAnimator(translationYT, scaleBig));
        items.add(getPlayTogetherAnimator(translationYT, scaleXSmall));
        items.add(getPlayTogetherAnimator(translationYT, scaleYSmall));
        items.add(getPlayTogetherAnimator(translationYT, scaleSmall));


    }

    private Animator getPlayTogetherAnimator(AnimatorFactory... animators) {
        return getPlayTogetherAnimatorSet(animators).createAnimator();
    }
}