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