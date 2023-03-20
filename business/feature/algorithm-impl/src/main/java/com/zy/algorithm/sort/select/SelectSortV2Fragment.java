package com.zy.algorithm.sort.select;

import com.zy.algorithm.Sort;
import com.zy.algorithm.bean.SortStepBean;

import java.util.List;

//选择排序
public class SelectSortV2Fragment extends SelectSortBaseFragment {

    @Override
    protected List<SortStepBean> getStepBean() {
        int[] array = {5, 11, 22, 33, 44, 55, 66, 77, 88, 99};
        return Sort.selectSortV1(array);
    }

    @Override
    protected String getTitle() {
        return "选择排序(顺序数组)";
    }

    @Override
    protected String getEnterTitle() {
        return "选择排序(顺序数组)";
    }

    @Override
    protected String getDataDes() {
        return "每一趟,选出最小的数字\n放到未排序数组的最前面";
    }
}
