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

}
