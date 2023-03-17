package com.zy.algorithm.sort;

import com.zy.algorithm.fragment.BaseAlgorithmBallFragment;
import com.zy.algorithm.Sort;
import com.zy.algorithm.bean.SortStepBean;

import java.util.List;

public class BubbleSortV2Fragment extends BaseAlgorithmBallFragment {

    private static final String TAG = "BubbleSortV2Fragment";

    @Override
    protected List<SortStepBean> getStepBean() {
        return Sort.bubbleSortAnimationV2();
    }

    @Override
    protected String getTitle() {
        return "排序-冒泡排序(V2)";
    }

    @Override
    protected String getEnterTitle() {
        return "排序-冒泡排序(V1)aaaa";
    }
}
