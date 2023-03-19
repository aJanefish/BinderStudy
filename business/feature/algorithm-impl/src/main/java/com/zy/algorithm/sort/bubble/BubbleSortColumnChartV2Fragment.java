package com.zy.algorithm.sort.bubble;

import com.zy.algorithm.Sort;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.algorithm.fragment.BaseAlgorithmColumnChartFragment;

import java.util.List;

public class BubbleSortColumnChartV2Fragment extends BaseAlgorithmColumnChartFragment {

    private static final String TAG = "BubbleSortColumnChartV1Fragment";

    @Override
    protected List<SortStepBean> getStepBean() {
        int[] array = {99, 88, 5, 11, 22, 33, 44, 55, 66, 77};
        return Sort.bubbleSortAnimationV2(array);
    }

    @Override
    protected String getDataDes() {
        return "某一趟没有移动数字\n则排序完成";
    }

    @Override
    protected String getTitle() {
        return "冒泡排序(V2)";
    }

    @Override
    protected String getEnterTitle() {
        return "冒泡排序(V2)";
    }
}
