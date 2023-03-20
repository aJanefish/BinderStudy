package com.zy.algorithm.sort.insertion;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import com.zy.algorithm.bean.InsertionSortStepBean;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.algorithm.fragment.BaseAlgorithmBallFragment;
import com.zy.utils.AnimatorUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class InsertionSortBaseFragment extends BaseAlgorithmBallFragment {

    @Override
    protected void sortAnimation(int index, SortStepBean curStepBean, StepListener listener) {
        //super.sortAnimation(index, curStepBean, listener);

        //设置当前操作位置
        setDataItemRed(curStepBean.getOpFirstIndex());
        setDataItemRed(curStepBean.getOpSecondIndex());

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

        //出来
        // 1. 第一次出来 - 两个
        if (bean.isCompareFirstInPer()) {
            AnimatorUtils.move(originFirstTV, pk_first, new AnimatorUtils.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    super.onAnimationEnd();

                    listener.nextStep();
                }
            });
            AnimatorUtils.move(originSecondTV, pk_second);
            pk_second.setText(bean.getOpSecondV());
        } else {
            AnimatorUtils.move(originFirstTV, pk_first, new AnimatorUtils.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    super.onAnimationEnd();

                    listener.nextStep();
                }
            });
        }
        // 2. 后续就是 - 一个出来，key值保持不动


        //比较

        //后移

        //如果找到位置-数据回设


    }
}
