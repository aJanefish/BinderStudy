package com.zy.algorithm.sort.select;

import com.zy.algorithm.Sort;
import com.zy.algorithm.bean.SortStepBean;

import java.util.List;

//选择排序
public class SelectSortV1Fragment extends SelectSortBaseFragment {

    @Override
    protected List<SortStepBean> getStepBean() {
        return Sort.selectSortV1();
    }

    @Override
    protected String getTitle() {
        return "选择排序";
    }

    @Override
    protected String getEnterTitle() {
        return "选择排序";
    }
}
