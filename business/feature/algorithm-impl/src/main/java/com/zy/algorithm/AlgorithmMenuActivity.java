package com.zy.algorithm;

import com.zy.activity.BaseMenuActivity;
import com.zy.activity.bean.BaseBean;

import java.util.List;

public class AlgorithmMenuActivity extends BaseMenuActivity {


    @Override
    protected void init(List<BaseBean> list) {
        list.add(new BaseBean("排序-冒泡 ", 1));

    }

    @Override
    protected void clickItem(BaseBean bean) {
        //        Intent intent = new Intent(this, AnimationMainActivity.class);
        //        intent.putExtra("type", bean.getType());
        //        startActivity(intent);
        if (bean == null) {
            return;
        }

        if (bean.getType() == 1) {
            Sort.bubbleSort();
        }
    }
}