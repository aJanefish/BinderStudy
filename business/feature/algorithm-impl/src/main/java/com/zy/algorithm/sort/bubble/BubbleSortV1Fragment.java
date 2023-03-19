package com.zy.algorithm.sort.bubble;

import com.zy.algorithm.fragment.BaseAlgorithmBallFragment;
import com.zy.algorithm.Sort;
import com.zy.algorithm.bean.SortStepBean;

import java.util.List;

public class BubbleSortV1Fragment extends BaseAlgorithmBallFragment {

    private static final String TAG = "BubbleSortV1Fragment";

    @Override
    protected List<SortStepBean> getStepBean() {
        return Sort.bubbleSortAnimationV1();
    }

    @Override
    protected String getTitle() {
        return "排序-冒泡排序(V1)";
    }

    @Override
    protected String getEnterTitle() {
        return "排序-冒泡排序(V1)ssss";
    }

    @Override
    protected String getDataDes() {
        return "冒泡";
    }
}
