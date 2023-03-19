package com.zy.algorithm.sort.select;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import com.zy.algorithm.bean.ExchangeBean;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.algorithm.fragment.BaseAlgorithmBallFragment;
import com.zy.utils.AnimatorUtils;


//选择排序
public abstract class SelectSortBaseFragment extends BaseAlgorithmBallFragment {

    private static final String TAG = "SelectSortBaseFragment";


    @Override
    protected void showPK(SortStepBean curStepBean, StepListener listener) {
        //当前比较的数字View
        View originFirstTV = dataTVS[curStepBean.getOpFirstIndex()];
        View originSecondTV = dataTVS[curStepBean.getOpSecondIndex()];


        pk_result.setText("");

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
                                    exchangeAnimation(curStepBean.getExchangeBean(), listener);
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

                pk_result.setText(curStepBean.isResult() ? "交换" : "不交换");
            }
        });
    }

    private void exchangeAnimation(ExchangeBean exchangeBean, StepListener listener) {
        //当前交换bean
        View originFirstTV = dataTVS[exchangeBean.getFirstIndex()];
        View originSecondTV = dataTVS[exchangeBean.getSecondIndex()];


        Rect originFirstTVRect = AnimatorUtils.getGlobalVisibleRect(originFirstTV);
        Rect originSecondTVRect = AnimatorUtils.getGlobalVisibleRect(originSecondTV);

        Animator up = AnimatorUtils.moveArc(originFirstTV, originSecondTVRect, true);
        Animator down = AnimatorUtils.moveArc(originSecondTV, originFirstTVRect, false);


        Animator animator = AnimatorUtils.getTogetherStart(up, down);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                super.onAnimationEnd(animation, isReverse);
                listener.nextStep();
            }
        });
        animator.start();
    }

}
