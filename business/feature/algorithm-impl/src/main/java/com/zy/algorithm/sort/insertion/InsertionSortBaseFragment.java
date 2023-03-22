package com.zy.algorithm.sort.insertion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import com.zy.algorithm.R;
import com.zy.algorithm.bean.InsertionSortStepBean;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.algorithm.fragment.BaseAlgorithmBallFragment;
import com.zy.utils.AnimatorUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class InsertionSortBaseFragment extends BaseAlgorithmBallFragment {

    @Override
    protected boolean getColorAni() {
        return false;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        pk_first.setVisibility(View.GONE);
        pk_second.setVisibility(View.GONE);
    }

    @Override
    protected void sortAnimation(int index, SortStepBean curStepBean, StepListener listener) {
        //super.sortAnimation(index, curStepBean, listener);

        //设置当前操作位置
//        setDataItemRed(curStepBean.getOpFirstIndex());
//        setDataItemRed(curStepBean.getOpSecondIndex());

        //下标动画
        sortIndexAnimation(curStepBean, new AnimationListener() {
            @Override
            public void onAnimationEnd() {
                //比较动画
                showPK(curStepBean, listener);
            }
        });
    }


    @Override
    protected void showPK(SortStepBean curStepBean, StepListener listener) {
        //super.showPK(curStepBean, listener);
        if (!(curStepBean instanceof InsertionSortStepBean)) {
            listener.nextStep();
            return;
        }
        InsertionSortStepBean bean = (InsertionSortStepBean) curStepBean;

        //当前比较的数字View
        View originFirstTV = dataTVS[curStepBean.getOpFirstIndex()];
        View originSecondTV = dataTVS[curStepBean.getOpSecondIndex()];

        Rect originFirstTVRect = AnimatorUtils.getGlobalVisibleRect(originFirstTV);
        Rect originSecondTVRect = AnimatorUtils.getGlobalVisibleRect(originSecondTV);


        List<Animator> animatorList = new ArrayList<>();

        //出来
        // 1. 第一次出来 - 两个
        if (bean.isCompareFirstInPer()) {
            Animator move = AnimatorUtils.getMove(originSecondTV, pk_op);
            Animator hide = AnimatorUtils.getAlpha(originSecondTV, false);
            Animator show = AnimatorUtils.getAlpha(pk_op, true);


            show.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation, boolean isReverse) {
                    super.onAnimationStart(animation, isReverse);
                    pk_op.setText("" + bean.getOpSecondV());
                    pk_op.setVisibility(View.VISIBLE);
                    pk_op.setBackgroundColor(getResources().getColor(R.color.statues_sorting));
                }
            });


            Animator moveOut = AnimatorUtils.getTogetherStart(move, hide, show);

            animatorList.add(moveOut);
        }

        if (true) {
            if (bean.isResult()) {
                animatorList.add(AnimatorUtils.getShakeAnimator(originFirstTV));
            } else {
                animatorList.add(AnimatorUtils.getShakeAnimator(pk_op));
            }
        }

        if (bean.isMoveRight()) {
            View moveOrigin = dataTVS[bean.getMoveRightFirstIndex()];
            View moveTarget = dataTVS[bean.getMoveRightSecondIndex()];

            Animator moveRight = AnimatorUtils.getMove(moveOrigin, moveTarget);
            animatorList.add(moveRight);
        }

        if (bean.isMoveBack()) {
            View moveTarget = dataTVS[bean.getMoveBackIndex()];
            Animator moveBack = AnimatorUtils.getMove(pk_op, moveTarget);
            moveBack.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation, boolean isReverse) {
                    super.onAnimationEnd(animation, isReverse);
                    pk_op.setTranslationX(0);
                    pk_op.setTranslationY(0);
                    pk_op.setScaleX(1f);
                    pk_op.setScaleY(1f);
                    pk_op.setVisibility(View.VISIBLE);
                    pk_op.setText("");
                    pk_op.setBackground(getResources().getDrawable(R.drawable.background_line));
                }
            });

            animatorList.add(indexController.getMinIndexAnimator(bean.getMoveBackIndex()));


            animatorList.add(moveBack);
        }


        // 2. 后续就是 - 一个出来，key值保持不动


        //比较

        //后移

        //如果找到位置-数据回设
        startStepAnimator(animatorList, listener);

    }
}
