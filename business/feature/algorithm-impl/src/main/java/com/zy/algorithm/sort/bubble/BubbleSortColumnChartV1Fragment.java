package com.zy.algorithm.sort.bubble;

import com.zy.algorithm.fragment.BaseAlgorithmColumnChartFragment;
import com.zy.algorithm.Sort;
import com.zy.algorithm.bean.SortStepBean;

import java.util.List;

public class BubbleSortColumnChartV1Fragment extends BaseAlgorithmColumnChartFragment {

    private static final String TAG = "BubbleSortColumnChartV1Fragment";

    @Override
    protected List<SortStepBean> getStepBean() {
        return Sort.bubbleSortAnimationV1();

    }

    @Override
    protected String getDataDes() {
        return "每一趟,两两比较\n较大的数向后移动";
    }

    @Override
    protected String getTitle() {
        return "冒泡排序(V1)";
    }

    @Override
    protected String getEnterTitle() {
        return "冒泡排序\n基础版";
    }
}
