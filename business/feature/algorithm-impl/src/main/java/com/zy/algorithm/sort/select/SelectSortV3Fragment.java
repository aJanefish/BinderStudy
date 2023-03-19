package com.zy.algorithm.sort.select;

import com.zy.algorithm.Sort;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.zlog.ZLog;

import java.util.List;

//选择排序
public class SelectSortV3Fragment extends SelectSortBaseFragment {

    @Override
    protected List<SortStepBean> getStepBean() {
        int[] array = {99, 88, 77, 66, 55, 44, 33, 22, 11, 5};
        List<SortStepBean> sortStepBeans = Sort.selectSortV1(array);
        for (SortStepBean sortStepBean : sortStepBeans) {
            ZLog.d("SelectSortV3Fragment",""+sortStepBean);
        }
        return sortStepBeans;
    }

    @Override
    protected String getTitle() {
        return "选择排序(最坏情况)";
    }

    @Override
    protected String getEnterTitle() {
        return "选择排序\n最坏情况";
    }

    @Override
    protected String getDataDes() {
        return "每一趟,选出最小的数字\n放到未排序数组的最前面";
    }
}
