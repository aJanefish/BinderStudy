package com.zy.algorithm.sort;

import com.zy.algorithm.Sort;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.algorithm.fragment.BaseAlgorithmColumnChartFragment;

import java.util.List;

public class BubbleSortColumnChartV3Fragment extends BaseAlgorithmColumnChartFragment {

    private static final String TAG = "BubbleSortColumnChartV1Fragment";

    @Override
    protected List<SortStepBean> getStepBean() {
        int[] array = {5, 11, 22, 33, 44, 55, 66, 77, 88, 99};
        return Sort.bubbleSortAnimationV2(array);
    }

    @Override
    protected String getDataDes() {
        return "数组已经是排序OK的状态";
    }

    @Override
    protected String getTitle() {
        return "冒泡排序(最好情况)";
    }

    @Override
    protected String getEnterTitle() {
        return "冒泡排序(最好情况)";
    }
}
