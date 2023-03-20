package com.zy.algorithm.sort.insertion;

import com.zy.algorithm.Sort;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.algorithm.fragment.BaseAlgorithmBallFragment;

import java.util.List;

public class InsertionSortV2Fragment extends InsertionSortBaseFragment {


    @Override
    protected List<SortStepBean> getStepBean() {
        int[] array = {99, 88, 77, 66, 55, 44, 33, 22, 11, 5};
        return Sort.insertionSort(array);

    }

    private static String title = "插入排序(最坏情况)";

    @Override
    protected String getDataDes() {
        return "每一趟,两两比较\n较大的数向后移动";
    }

    @Override
    protected String getTitle() {
        return title;
    }

    @Override
    protected String getEnterTitle() {
        return title;
    }
}
