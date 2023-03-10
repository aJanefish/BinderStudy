package com.zy.animation.animator;

import android.animation.Animator;

public abstract class AnimatorFactory {

    Animator.AnimatorListener mAnimatorListener = null;

    public abstract String getName();

    public abstract Animator.AnimatorListener createAnimatorListener();

    protected Animator.AnimatorListener getAnimatorListener() {
        if (mAnimatorListener == null) {
            mAnimatorListener = createAnimatorListener();
        }

        return mAnimatorListener;
    }

    public abstract Animator createAnimator();
}
