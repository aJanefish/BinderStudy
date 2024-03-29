package com.zy.algorithm;

import android.content.Intent;

import com.zy.activity.BaseMenuActivity;
import com.zy.activity.bean.BaseBean;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.zlog.ZLog;

import java.util.List;

public class AlgorithmMenuActivity extends BaseMenuActivity {


    @Override
    protected void init(List<BaseBean> list) {
        list.add(new BaseBean("排序算法"));
        list.add(new BaseBean("排序-冒泡", 1));
        list.add(new BaseBean("排序-冒泡(优化一)", 2));
        list.add(new BaseBean("排序-冒泡(柱形图)V1", 3));
        list.add(new BaseBean("排序-冒泡(柱形图)V2", 4));
        list.add(new BaseBean("排序-冒泡(柱形图)最好情况", 5));
        list.add(new BaseBean("排序-冒泡(柱形图)最差情况", 6));
        list.add(new BaseBean("排序-选择", 7));
        list.add(new BaseBean("排序-选择-最好情况", 8));
        list.add(new BaseBean("排序-选择-最坏情况", 9));
        list.add(new BaseBean("排序-插入排序", 10));
        list.add(new BaseBean("排序-插入排序-最坏情况", 11));
        list.add(new BaseBean("排序-插入排序-最坏情况", 12));
        list.add(new BaseBean("排序-希尔排序", 13));
        list.add(new BaseBean("排序-希尔排序-顺序数组", 14));
        list.add(new BaseBean("排序-希尔排序-逆序数组", 15));

    }

    @Override
    protected void clickItem(BaseBean bean) {
        if (bean == null) {
            return;
        }
        Intent intent = new Intent(this, AlgorithmMainActivity.class);
        intent.putExtra("code", bean.getCode());
        startActivity(intent);
    }
}