package com.zy.algorithm.sort.bubble;

import com.zy.algorithm.Sort;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.algorithm.fragment.BaseAlgorithmColumnChartFragment;

import java.util.List;

public class BubbleSortColumnChartV4Fragment extends BaseAlgorithmColumnChartFragment {

    private static final String TAG = "BubbleSortColumnChartV1Fragment";

    @Override
    protected List<SortStepBean> getStepBean() {
        int[] array = {99, 88, 77, 66, 55, 44, 33, 22, 11, 5};
        return Sort.bubbleSortAnimationV2(array);
    }

    @Override
    protected String getDataDes() {
        return "数组已经是逆序排序的状态";
    }

    @Override
    protected String getTitle() {
        return "冒泡排序(最坏情况)";
    }

    @Override
    protected String getEnterTitle() {
        return "冒泡排序(最坏情况)";
    }
}
