package com.zy.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

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

    private static final int D = 500;


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
        list.add(ObjectAnimator.ofFloat(origin, "scaleX", curScaleX, curScaleX * 1.1F, curScaleX * 0.9F, curScaleX));
        list.add(ObjectAnimator.ofFloat(origin, "scaleY", curScaleY, curScaleY * 1.1F, curScaleY * 0.9F, curScaleY));

        togetherStart(list, 500, listener);
    }

    public static Animator getShakeAnimator(View origin) {
        List<Animator> list = new ArrayList<>();
        float curScaleX = origin.getScaleX();
        float curScaleY = origin.getScaleY();
        list.add(ObjectAnimator.ofFloat(origin, "scaleX", curScaleX, curScaleX * 1.1F, curScaleX * 0.9F, curScaleX));
        list.add(ObjectAnimator.ofFloat(origin, "scaleY", curScaleY, curScaleY * 1.1F, curScaleY * 0.9F, curScaleY));

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

    public static Animator getAlpha(View origin, boolean show) {
        List<Animator> list = new ArrayList<>();
        if (show) {
            list.add(ObjectAnimator.ofFloat(origin, "alpha", 0, 1));
        } else {
            list.add(ObjectAnimator.ofFloat(origin, "alpha", 1, 0));
        }
        return getTogetherStart(list);
    }


    public static Animator getMove(View origin, View target) {
        return getMove(origin, getGlobalVisibleRect(target));
    }

    public static Animator getMove(View origin, Rect targetR) {
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
        return getTogetherStart(list);
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


    //水平方向上的交换
    public static Animator moveArc(View origin, Rect targetR, boolean up) {
        Rect originR = getGlobalVisibleRect(origin);

        //缩放动画
        List<Animator> list = new ArrayList<>();
        int originXCenter = originR.centerX();
        int targetXCenter = targetR.centerX();


        list.add(ObjectAnimator.ofFloat(origin, "translationX", origin.getTranslationX(), origin.getTranslationX() + (targetXCenter - originXCenter)));


        ValueAnimator upDown = ValueAnimator.ofFloat(0, up ? 200 : 200, 0);
        upDown.setInterpolator(new DecelerateInterpolator());
        upDown.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float translationY = (float) animation.getAnimatedValue();
                origin.setTranslationY(translationY);
            }
        });

        list.add(upDown);

        //平移-中心点
        return getTogetherStart(list);
    }

    public static void togetherStart(List<Animator> list) {
        togetherStart(list, null);
    }

    public static Animator getTogetherStart(Animator... animators) {
        AnimatorSet animatorSet = new AnimatorSet();
        //同时动画
        animatorSet.playTogether(animators);
        return animatorSet;
    }

    public static Animator getTogetherStart(List<Animator> list) {
        AnimatorSet animatorSet = new AnimatorSet();
        //同时动画
        animatorSet.playTogether(list);
        animatorSet.setDuration(D);
        return animatorSet;
    }

    public static Animator getPlaySequentially(Animator... animators) {
        AnimatorSet animatorSet = new AnimatorSet();
        //同时动画
        animatorSet.playSequentially(animators);
        return animatorSet;
    }

    public static Animator getPlaySequentially(List<Animator> list) {
        AnimatorSet animatorSet = new AnimatorSet();
        //同时动画
        animatorSet.playSequentially(list);
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
