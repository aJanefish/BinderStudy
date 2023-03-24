package com.zy.algorithm.sort.insertion;

import com.zy.algorithm.Sort;
import com.zy.algorithm.bean.SortStepBean;

import java.util.List;

public class ShellInsertionSortV3Fragment extends InsertionSortBaseFragment {


    @Override
    protected List<SortStepBean> getStepBean() {
        int[] array = {99, 88, 77, 66, 55, 44, 33, 22, 11, 5};
        return Sort.shellSortBaseV1(array);
    }

    private static final String title = "希尔排序";

    @Override
    protected String getDataDes() {
        return "特殊case之\n逆序数组";
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
