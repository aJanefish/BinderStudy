package com.zy.algorithm.fragment;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.TextView;


import com.zy.algorithm.R;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.view.SortSquareView;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseAlgorithmColumnChartFragment extends BaseAlgorithmFragment {

    private static final String TAG = "BaseAlgorithmColumnChart";

    //数据内容
    protected SortSquareView sort_index_0;
    protected SortSquareView sort_index_1;
    protected SortSquareView sort_index_2;
    protected SortSquareView sort_index_3;
    protected SortSquareView sort_index_4;
    protected SortSquareView sort_index_5;
    protected SortSquareView sort_index_6;
    protected SortSquareView sort_index_7;
    protected SortSquareView sort_index_8;
    protected SortSquareView sort_index_9;

    protected SortSquareView[] dataTVS = new SortSquareView[10];

    protected TextView sort_step;
    protected TextView sort_step_index;

    @Override
    protected View[] getDataViewS() {
        return dataTVS;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_algorithm_column_chart_base;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        initEnteringContainer(view);
        initDataTV(view);
    }


    private View entering_container;
    private View algorithm_context_container;

    private void initEnteringContainer(View view) {
        entering_container = view.findViewById(R.id.entering_container);
        algorithm_context_container = view.findViewById(R.id.algorithm_context_container);
    }

    @Override
    protected View getEnterContainer() {
        return entering_container;
    }

    @Override
    protected View getContextContainer() {
        return algorithm_context_container;
    }

    protected void initDataTV(View view) {
        sort_step = view.findViewById(R.id.sort_step);
        sort_step_index = view.findViewById(R.id.sort_step_index);



        sort_index_0 = view.findViewById(R.id.sort_index_0);
        sort_index_1 = view.findViewById(R.id.sort_index_1);
        sort_index_2 = view.findViewById(R.id.sort_index_2);
        sort_index_3 = view.findViewById(R.id.sort_index_3);
        sort_index_4 = view.findViewById(R.id.sort_index_4);
        sort_index_5 = view.findViewById(R.id.sort_index_5);
        sort_index_6 = view.findViewById(R.id.sort_index_6);
        sort_index_7 = view.findViewById(R.id.sort_index_7);
        sort_index_8 = view.findViewById(R.id.sort_index_8);
        sort_index_9 = view.findViewById(R.id.sort_index_9);

        dataTVS[0] = sort_index_0;
        dataTVS[1] = sort_index_1;
        dataTVS[2] = sort_index_2;
        dataTVS[3] = sort_index_3;
        dataTVS[4] = sort_index_4;
        dataTVS[5] = sort_index_5;
        dataTVS[6] = sort_index_6;
        dataTVS[7] = sort_index_7;
        dataTVS[8] = sort_index_8;
        dataTVS[9] = sort_index_9;


    }

    @Override
    protected void initSetData() {
        super.initSetData();
        sort_step.setText("第(" + 0 + ")趟,第(" + 0 + ")次比较");
        sort_step_index.setText("比较次数:(" + 0 + ")\n交换次数:(" + 0 + ")");
    }

    protected void setSortData(SortStepBean bean, boolean start) {
        //设置颜色
        List<Integer> sorted = bean.getStepStartSorted();
        //设置数据
        for (int index = 0; index < dataTVS.length; index++) {
            SortSquareView sortSquareView = dataTVS[index];
            if (start) {
                sortSquareView.setNum(bean.getStepStart()[index]);
            } else {
                sortSquareView.setNum(bean.getStepEnd()[index]);
            }

            if (sorted.contains(index)) {
                sortSquareView.setStatues(STATUES_SORTED);
            } else {
                sortSquareView.setStatues(STATUES_UNSORTED);
            }
        }
    }

    protected void setDataItemSorting(int index) {
        dataTVS[index].setStatues(STATUES_SORTING);
    }

    @Override
    protected void sortAnimation(int index, SortStepBean curStepBean, StepListener listener) {
        //设置当前操作位置-标识
        setDataItemSorting(curStepBean.getOpFirstIndex());
        setDataItemSorting(curStepBean.getOpSecondIndex());

        View originFirstTV = dataTVS[curStepBean.getOpFirstIndex()];
        View originSecondTV = dataTVS[curStepBean.getOpSecondIndex()];
        //比较动画


        List<Animator> animatorList = new ArrayList<>();
        if (curStepBean.isResult()) {
            //交换
            int translationXWidth = originFirstTV.getWidth();
            List<Animator> move = new ArrayList<>();
            move.add(ObjectAnimator.ofFloat(originFirstTV, "translationX", 0, translationXWidth));
            move.add(ObjectAnimator.ofFloat(originSecondTV, "translationX", 0, -translationXWidth));


            AnimatorSet moveAnimatorSet = new AnimatorSet();
            moveAnimatorSet.playTogether(move);
            moveAnimatorSet.setDuration(500);
            animatorList.add(moveAnimatorSet);

        } else {//后一个大
            //不交换

            ObjectAnimator empty = ObjectAnimator.ofFloat(originSecondTV, "scaleX", 1f, 1f);
            empty.setDuration(500);
            animatorList.add(empty);
        }

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatorList);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                super.onAnimationEnd(animation, isReverse);
                listener.nextStep();
            }
        });
        animatorSet.start();

        //设置步骤内容
        sort_step.setText("第(" + (curStepBean.getFirstIndex() + 1) + ")趟,第(" + (curStepBean.getSecondIndex() + 1) + ")次比较");
        sort_step_index.setText("比较次数:(" + curStepBean.getCompareSize() + ")\n交换次数:(" + curStepBean.getExchangeSize() + ")");
    }
}
