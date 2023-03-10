package com.zy.animation.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zy.animation.R;
import com.zy.animation.animator.AnimatorFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAnimationDesFragment extends Fragment {


    public BaseAnimationDesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DURATION = getDuration();
        float STEP = getResources().getDimension(R.dimen.STEP_TWO);
        TRANSLATION_RB = new float[]{0, STEP, STEP, 0};
        TRANSLATION_LT = new float[]{0, -STEP, -STEP, 0};
    }

    protected abstract int getDuration();

    //动画时长
    int DURATION = 2000;


    //动画参数
    float[] TRANSLATION_RB;
    float[] TRANSLATION_LT;
    float[] SCALEBIG = new float[]{1, 2, 2, 1};
    float[] SCALESMALL = new float[]{1, 0.1f, 0.1f, 1};
    float[] ROTATIONV = new float[]{0, 180, 180, 360};
    float[] ROTATIONV1 = new float[]{0, -180, -180, -360};

    //基础动画
    protected AnimatorFactory translationXL;
    protected AnimatorFactory translationXR;
    protected AnimatorFactory translationYT;
    protected AnimatorFactory translationYB;

    protected AnimatorFactory scaleXBig;
    protected AnimatorFactory scaleXSmall;
    protected AnimatorFactory scaleYBig;
    protected AnimatorFactory scaleYSmall;
    protected AnimatorFactory scaleBig;
    protected AnimatorFactory scaleSmall;

    protected AnimatorFactory rotationX;
    protected AnimatorFactory rotationX1;
    protected AnimatorFactory rotationY;
    protected AnimatorFactory rotationY1;
    protected AnimatorFactory rotation;
    protected AnimatorFactory rotation1;

    protected AnimatorFactory alpha;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    protected int getLayout() {
        return R.layout.fragment_animation_base;
    }

    View target;
    TextView desTV;
    TextView title;
    View animation_container;
    View ending_container;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        target = view.findViewById(R.id.target);
        desTV = view.findViewById(R.id.des);
        title = view.findViewById(R.id.title);
        animation_container = view.findViewById(R.id.animation_container);
        ending_container = view.findViewById(R.id.ending_container);
        ending_container.setVisibility(View.INVISIBLE);
        title.setText(getTitle());


        initAnimation();
        List<Animator> items = new ArrayList<>();
        initAnimatorData(items);

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                final AnimatorSet animatorSet = new AnimatorSet();
                //先后动画
                animatorSet.playSequentially(items);
                animatorSet.addListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        super.onAnimationEnd(animation, isReverse);
                        showEnding();
                    }
                });
                animatorSet.start();
            }
        }, 1000);
    }

    private void showEnding() {
        //下往上
        //内容区域缩小退出
        List<Animator> endingAnimationList = new ArrayList<>();
        endingAnimationList.add(ObjectAnimator.ofFloat(animation_container, "translationY", 0, -500, -500, -500));
        endingAnimationList.add(ObjectAnimator.ofFloat(animation_container, "alpha", 1, 0, 0, 0));
        endingAnimationList.add(ObjectAnimator.ofFloat(animation_container, "scaleX", 1, 0.5f, 0.5f, 0.5f));
        endingAnimationList.add(ObjectAnimator.ofFloat(animation_container, "scaleY", 1, 0.5f, 0.5f, 0.5f));


        //ending区域放大展示
        endingAnimationList.add(ObjectAnimator.ofFloat(ending_container, "translationY", -500, 0, 10, 0));
        endingAnimationList.add(ObjectAnimator.ofFloat(ending_container, "alpha", 0, 1, 0.9f, 1, 0.9f, 1));
        endingAnimationList.add(ObjectAnimator.ofFloat(ending_container, "scaleX", 0.5f, 1, 1.2f, 1, 1.2f, 1));
        endingAnimationList.add(ObjectAnimator.ofFloat(ending_container, "scaleY", 0.5f, 1, 1.2f, 1, 1.2f, 1));

        final AnimatorSet animatorSet = new AnimatorSet();
        //先后动画
        animatorSet.playTogether(endingAnimationList);
        animatorSet.setDuration(2000);
        animatorSet.start();
        ending_container.setVisibility(View.VISIBLE);
    }

    protected abstract String getTitle();

    protected abstract void initAnimatorData(List<Animator> items);


    private void initAnimation() {
        translationXL = getAnimatorFactory("平移", "translationX", TRANSLATION_LT);
        translationXR = getAnimatorFactory("平移", "translationX", TRANSLATION_RB);

        translationYT = getAnimatorFactory("平移", "translationY", TRANSLATION_LT);
        translationYB = getAnimatorFactory("平移", "translationY", TRANSLATION_RB);

        scaleXBig = getAnimatorFactory("放大", "scaleX", SCALEBIG);
        scaleXSmall = getAnimatorFactory("缩小", "scaleX", SCALESMALL);
        scaleYBig = getAnimatorFactory("放大", "scaleY", SCALEBIG);
        scaleYSmall = getAnimatorFactory("缩小", "scaleY", SCALESMALL);
        scaleBig = getPlayTogetherAnimatorSet(getAnimatorFactory("放大", "scaleX", SCALEBIG), getAnimatorFactory("", "scaleY", SCALEBIG));
        scaleSmall = getPlayTogetherAnimatorSet(getAnimatorFactory("缩小", "scaleX", SCALESMALL), getAnimatorFactory("", "scaleY", SCALESMALL));


        //旋转动画
        rotationX = getAnimatorFactory("旋转", "rotationX", ROTATIONV);
        rotationX1 = getAnimatorFactory("旋转", "rotationX", ROTATIONV1);
        rotationY = getAnimatorFactory("旋转", "rotationY", ROTATIONV);
        rotationY1 = getAnimatorFactory("旋转", "rotationY", ROTATIONV1);
        rotation = getAnimatorFactory("旋转", "rotation", ROTATIONV);
        rotation1 = getAnimatorFactory("旋转", "rotation", ROTATIONV1);

        //虚化动画
        alpha = getAnimatorFactory("透明度", "alpha", 1, 0.1F, 0.1F, 1);

        initAnimationList();
    }

    //分类
    List<AnimatorFactory> translationList = new ArrayList<>();
    List<AnimatorFactory> scaleList = new ArrayList<>();
    List<AnimatorFactory> rotationList = new ArrayList<>();
    List<AnimatorFactory> alphaList = new ArrayList<>();


    List<List<AnimatorFactory>> allAnimatorList = new ArrayList<>();


    private void initAnimationList() {
        translationList.add(translationXL);
        translationList.add(translationYT);
        translationList.add(translationXR);
        translationList.add(translationYB);

        scaleList.add(scaleXBig);
        scaleList.add(scaleXSmall);
        scaleList.add(scaleYBig);
        scaleList.add(scaleYSmall);
        scaleList.add(scaleBig);
        scaleList.add(scaleSmall);

        rotationList.add(rotationX);
        rotationList.add(rotationX1);
        rotationList.add(rotationY);
        rotationList.add(rotationY1);
        rotationList.add(rotation);
        rotationList.add(rotation1);

        alphaList.add(alpha);


        //allAnimatorList.add(translationList);
        //allAnimatorList.add(scaleList);
        allAnimatorList.add(rotationList);
        allAnimatorList.add(alphaList);
    }


    protected AnimatorFactory getPlayTogetherAnimatorSet(AnimatorFactory... animators) {
        List<Animator> list = new ArrayList<>();
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < animators.length; i++) {
            AnimatorFactory factory = animators[i];
            Animator animator = factory.createAnimator();
            animator.removeAllListeners();
            list.add(animator);


            if (!TextUtils.isEmpty(factory.getName())) {
                if (i != 0) {
                    message.append("+");
                }
                message.append(factory.getName());
            }
        }

        return new AnimatorFactory() {
            @Override
            public String getName() {
                return message.toString();
            }

            @Override
            public Animator.AnimatorListener createAnimatorListener() {
                return new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation, boolean isReverse) {
                        super.onAnimationStart(animation, isReverse);
                        desTV.setText(getName());
                    }

                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        super.onAnimationEnd(animation, isReverse);
                        reset();
                    }
                };
            }

            @Override
            public Animator createAnimator() {
                AnimatorSet animatorSet = new AnimatorSet();
                //同时动画
                animatorSet.playTogether(list);
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation, boolean isReverse) {
                        super.onAnimationStart(animation, isReverse);
                        desTV.setText(message.toString());
                    }

                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        super.onAnimationEnd(animation, isReverse);
                        reset();
                    }
                });
                return animatorSet;
            }
        };
    }

    private AnimatorFactory getAnimatorFactory(String message, String propertyName, float... values) {

        return new AnimatorFactory() {
            @Override
            public String getName() {
                return message;
            }

            @Override
            public Animator.AnimatorListener createAnimatorListener() {
                return new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation, boolean isReverse) {
                        super.onAnimationStart(animation, isReverse);
                        desTV.setText(getName());
                    }

                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        super.onAnimationEnd(animation, isReverse);
                        reset();
                    }
                };
            }

            @Override
            public Animator createAnimator() {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(target, propertyName, values);
                objectAnimator.setDuration(DURATION);
                objectAnimator.addListener(getAnimatorListener());
                return objectAnimator;
            }
        };
    }

    private void reset() {
        target.setTranslationX(0);
        target.setTranslationY(0);
        target.setScaleX(1);
        target.setScaleY(1);
        target.setRotation(0);
        target.setAlpha(1);
    }
}