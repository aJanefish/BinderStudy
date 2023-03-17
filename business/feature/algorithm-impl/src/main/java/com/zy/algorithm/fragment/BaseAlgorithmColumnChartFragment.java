package com.zy.algorithm.fragment;


import static com.zy.view.SortSquareView.STATUES_SORTED;
import static com.zy.view.SortSquareView.STATUES_SORTING;
import static com.zy.view.SortSquareView.STATUES_UNSORTED;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;
import android.widget.TextView;


import com.zy.algorithm.R;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.utils.AnimatorUtils;
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
        initDataTV(view);
    }

    protected void initDataTV(View view) {
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

    protected void setSortData(SortStepBean bean, boolean start) {
        //设置颜色
        List<Integer> sorted = bean.getSorted();
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
    protected void sortAnimation(SortStepBean curStepBean, StepListener listener) {
        //设置当前操作位置-标识
        setDataItemSorting(curStepBean.getOpFirst());
        setDataItemSorting(curStepBean.getOpSecond());


        View originFirstTV = dataTVS[curStepBean.getOpFirst()];
        View originSecondTV = dataTVS[curStepBean.getOpSecond()];
        //比较动画


        List<Animator> animatorList = new ArrayList<>();
        if (curStepBean.isResult()) {
            //前一个大
            animatorList.add(AnimatorUtils.getShakeAnimator(originFirstTV));
        } else {//后一个大
            animatorList.add(AnimatorUtils.getShakeAnimator(originSecondTV));
        }


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorList);
        animatorSet.start();
        //移动动画
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.nextStep();
            }
        }, 500);
    }
}
