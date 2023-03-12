package com.zy.algorithm.sort;

import com.zy.algorithm.BaseAlgorithmFragment;
import com.zy.algorithm.Sort;
import com.zy.algorithm.bean.SortStepBean;

import java.util.List;

public class BubbleSortV1Fragment extends BaseAlgorithmFragment {

    private static final String TAG = "BubbleSortV1Fragment";

    @Override
    protected List<SortStepBean> getStepBean() {
        return Sort.bubbleSortAnimationV1();
    }

}
