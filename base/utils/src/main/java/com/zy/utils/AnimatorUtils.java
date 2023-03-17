package com.zy.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class AnimatorUtils {
    //translationX
    //translationY
    //scaleX
    //scaleY
    //rotationX
    //rotationY
    //rotation
    //alpha

    private static final int D = 1000;


    public static void reset(View origin) {
        reset(origin, null);
    }

    public static void reset(View origin, AnimationListener listener) {
        if (origin == null) {
            return;
        }
        List<Animator> list = new ArrayList<>();
        list.add(ObjectAnimator.ofFloat(origin, "scaleX", origin.getScaleX(), 1));
        list.add(ObjectAnimator.ofFloat(origin, "scaleY", origin.getScaleY(), 1));
        list.add(ObjectAnimator.ofFloat(origin, "translationX", origin.getTranslationX(), 0));
        list.add(ObjectAnimator.ofFloat(origin, "translationY", origin.getTranslationY(), 0));
        list.add(ObjectAnimator.ofFloat(origin, "rotation", origin.getRotation(), 0));

        togetherStart(list, listener);
    }

    public static void shake(View origin) {
        shake(origin, null);
    }

    public static void shake(View origin, AnimationListener listener) {
        if (origin == null) {
            return;
        }
        List<Animator> list = new ArrayList<>();
        float curScaleX = origin.getScaleX();
        float curScaleY = origin.getScaleY();
        list.add(ObjectAnimator.ofFloat(origin, "scaleX", curScaleX, curScaleX * 1.2F, curScaleX * 0.8F, curScaleX));
        list.add(ObjectAnimator.ofFloat(origin, "scaleY", curScaleY, curScaleY * 1.2F, curScaleY * 0.8F, curScaleY));

        togetherStart(list, 500, listener);
    }

    public static Animator getShakeAnimator(View origin) {
        List<Animator> list = new ArrayList<>();
        float curScaleX = origin.getScaleX();
        float curScaleY = origin.getScaleY();
        list.add(ObjectAnimator.ofFloat(origin, "scaleX", curScaleX, curScaleX * 1.2F, curScaleX * 0.8F, curScaleX));
        list.add(ObjectAnimator.ofFloat(origin, "scaleY", curScaleY, curScaleY * 1.2F, curScaleY * 0.8F, curScaleY));

        return getTogetherStart(list);
    }


    public static Rect getGlobalVisibleRect(View origin) {
        Rect r = new Rect();
        if (origin == null) {
            return r;
        }
        origin.getGlobalVisibleRect(r);
        return r;
    }

    public static void move(View origin, View target) {
        move(origin, getGlobalVisibleRect(target), null);
    }

    public static void move(View origin, Rect target) {
        move(origin, target, null);

    }

    public static void move(View origin, View target, AnimationListener listener) {
        move(origin, getGlobalVisibleRect(target), listener);
    }

    public static void move(View origin, Rect targetR, AnimationListener listener) {
        if (origin == null || targetR == null) {
            return;
        }

        Rect originR = getGlobalVisibleRect(origin);

        float curScaleX = origin.getScaleX();
        if (curScaleX == 0) {
            curScaleX = 1;
        }

        float curScaleY = origin.getScaleY();
        if (curScaleY == 0) {
            curScaleY = 1;
        }

        float originWidth = originR.width() / curScaleX;
        float originHeight = originR.height() / curScaleY;

        int targetWidth = targetR.width();
        int targetHeight = targetR.height();

        //缩放动画
        List<Animator> list = new ArrayList<>();
        list.add(ObjectAnimator.ofFloat(origin, "scaleX", curScaleX, ((float) targetWidth / (float) originWidth)));
        list.add(ObjectAnimator.ofFloat(origin, "scaleY", curScaleY, ((float) targetHeight / (float) originHeight)));

        int originXCenter = originR.centerX();
        int originYCenter = originR.centerY();

        int targetXCenter = targetR.centerX();
        int targetYCenter = targetR.centerY();


        list.add(ObjectAnimator.ofFloat(origin, "translationX", origin.getTranslationX(), origin.getTranslationX() + (targetXCenter - originXCenter)));
        list.add(ObjectAnimator.ofFloat(origin, "translationY", origin.getTranslationY(), origin.getTranslationY() + (targetYCenter - originYCenter)));

        //平移-中心点
        togetherStart(list, listener);
    }

    public static void togetherStart(List<Animator> list) {
        togetherStart(list, null);
    }

    public static Animator getTogetherStart(Animator... animators) {
        AnimatorSet animatorSet = new AnimatorSet();
        //同时动画
        animatorSet.playTogether(animators);
        animatorSet.setDuration(300);
        return animatorSet;
    }

    public static Animator getTogetherStart(List<Animator> list) {
        AnimatorSet animatorSet = new AnimatorSet();
        //同时动画
        animatorSet.playTogether(list);
        animatorSet.setDuration(300);
        return animatorSet;
    }

    public static void togetherStart(List<Animator> list, AnimationListener listener) {
        togetherStart(list, D, listener);
    }

    public static void togetherStart(List<Animator> list, int duration, AnimationListener listener) {
        AnimatorSet animatorSet = new AnimatorSet();
        //同时动画
        animatorSet.playTogether(list);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
                super.onAnimationStart(animation, isReverse);
                if (listener != null) {
                    listener.onAnimationStart();
                }
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                super.onAnimationEnd(animation, isReverse);
                if (listener != null) {
                    listener.onAnimationEnd();
                }
            }
        });
        animatorSet.setDuration(duration);
        animatorSet.start();
    }

    public static class AnimationListener {
        public void onAnimationStart() {
        }

        public void onAnimationEnd() {
        }
    }


}
