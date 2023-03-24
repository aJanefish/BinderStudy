package com.zy.algorithm.sort.insertion;

import com.zy.algorithm.Sort;
import com.zy.algorithm.bean.SortStepBean;

import java.util.List;

public class ShellInsertionSortV1Fragment extends InsertionSortBaseFragment {


    @Override
    protected List<SortStepBean> getStepBean() {
        int[] array = {5, 11, 22, 33, 44, 55, 66, 77, 88, 99};
        return Sort.shellSortBaseV1();
    }

    private static final String title = "希尔排序";

    @Override
    protected String getDataDes() {
        return "缩小增量排序";
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
