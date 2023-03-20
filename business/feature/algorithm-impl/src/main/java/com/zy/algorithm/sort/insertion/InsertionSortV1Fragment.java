package com.zy.algorithm.sort.insertion;

import com.zy.algorithm.Sort;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.algorithm.fragment.BaseAlgorithmBallFragment;

import java.util.List;

public class InsertionSortV1Fragment extends InsertionSortBaseFragment {


    @Override
    protected List<SortStepBean> getStepBean() {
        return Sort.insertionSort();

    }

    @Override
    protected String getDataDes() {
        return "每一趟,两两比较\n较大的数向后移动";
    }

    @Override
    protected String getTitle() {
        return "插入排序";
    }

    @Override
    protected String getEnterTitle() {
        return "插入排序";
    }
}
