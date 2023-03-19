package com.zy.algorithm.sort;

import com.zy.algorithm.Sort;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.algorithm.fragment.BaseAlgorithmBallFragment;

import java.util.List;

//选择排序
public class SelectSortV3Fragment extends BaseAlgorithmBallFragment {

    @Override
    protected List<SortStepBean> getStepBean() {
        int[] array = {99, 88, 77, 66, 55, 44, 33, 22, 11, 5};
        return Sort.selectSortV1(array);
    }

    @Override
    protected String getTitle() {
        return "选择排序\n最坏情况";
    }

    @Override
    protected String getEnterTitle() {
        return "选择排序\n最坏情况";
    }
}
