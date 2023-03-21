package com.zy.algorithm.sort.select;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import com.zy.algorithm.bean.ExchangeBean;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.algorithm.fragment.BaseAlgorithmBallFragment;
import com.zy.utils.AnimatorUtils;

import java.util.ArrayList;
import java.util.List;


//选择排序
public abstract class SelectSortBaseFragment extends BaseAlgorithmBallFragment {

    private static final String TAG = "SelectSortBaseFragment";

//    @Override
//    protected int getLayout() {
//        return R.layout.fragment_algorithm_select_base;
//    }

    @Override
    protected void showPK(SortStepBean curStepBean, StepListener listener) {
        //当前比较的数字View
        View originFirstTV = dataTVS[curStepBean.getOpFirstIndex()];
        View originSecondTV = dataTVS[curStepBean.getOpSecondIndex()];


        Rect originFirstTVRect = AnimatorUtils.getGlobalVisibleRect(originFirstTV);
        Rect originSecondTVRect = AnimatorUtils.getGlobalVisibleRect(originSecondTV);
        Log.d(TAG, "originFirstTVRect:" + originFirstTVRect);
        Log.d(TAG, "originFirstTVRect:" + originSecondTVRect);


        AnimatorUtils.move(originFirstTV, pk_first);
        AnimatorUtils.move(originSecondTV, pk_second, new AnimatorUtils.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                AnimatorUtils.AnimationListener animationListener = new AnimatorUtils.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        super.onAnimationEnd();
                        AnimatorUtils.reset(originFirstTV);
                        AnimatorUtils.reset(originSecondTV, new AnimatorUtils.AnimationListener() {
                            @Override
                            public void onAnimationEnd() {
                                super.onAnimationEnd();
                                //结束后需要交换动画
                                if (curStepBean.isExchangeAnimation() && curStepBean.getExchangeBean() != null) {
                                    exchangeAnimation(curStepBean, curStepBean.getExchangeBean(), listener);
                                } else {
                                    listener.nextStep();
                                }
                            }
                        });
                    }
                };

                //比较大小
                if (curStepBean.isResult()) {
                    AnimatorUtils.shake(originSecondTV, animationListener);
                } else {
                    AnimatorUtils.shake(originFirstTV, animationListener);
                }
            }
        });
    }

    private void exchangeAnimation(SortStepBean sortStepBean, ExchangeBean exchangeBean, StepListener listener) {

        //当前交换bean
        View originFirstTV = dataTVS[exchangeBean.getFirstIndex()];
        View originSecondTV = dataTVS[exchangeBean.getSecondIndex()];

        setDataItemRed(exchangeBean.getFirstIndex());
        setDataItemRed(exchangeBean.getSecondIndex());

        List<Animator> animatorList = new ArrayList<>();

        if (sortStepBean.getOpFirstIndex() != exchangeBean.getFirstIndex()) {
            //不相等
            //则说明本次循环的最后一比比较的最小下标 发生改变，需要补上其动画
            Animator minIndexAni = indexController.getMinIndexAnimator(exchangeBean.getFirstIndex());
            animatorList.add(minIndexAni);
        }

        animatorList.add(AnimatorUtils.getTogetherStart(AnimatorUtils.getShakeAnimator(originFirstTV), AnimatorUtils.getShakeAnimator(originSecondTV)));


        Rect originFirstTVRect = AnimatorUtils.getGlobalVisibleRect(originFirstTV);
        Rect originSecondTVRect = AnimatorUtils.getGlobalVisibleRect(originSecondTV);
        Animator up = AnimatorUtils.moveArc(originFirstTV, originSecondTVRect, true);
        Animator down = AnimatorUtils.moveArc(originSecondTV, originFirstTVRect, false);


        Animator move = AnimatorUtils.getTogetherStart(up, down);
        move.setDuration(1000);

        animatorList.add(move);


        startStepAnimator(animatorList, listener);
    }

}
