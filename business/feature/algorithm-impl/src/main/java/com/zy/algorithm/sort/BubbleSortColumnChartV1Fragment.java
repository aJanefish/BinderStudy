package com.zy.algorithm.sort;

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
}
