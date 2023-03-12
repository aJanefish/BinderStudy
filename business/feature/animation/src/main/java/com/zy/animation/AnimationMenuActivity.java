package com.zy.animation;

import android.content.Intent;

import com.zy.activity.BaseMenuActivity;
import com.zy.activity.bean.BaseBean;

import java.util.List;

public class AnimationMenuActivity extends BaseMenuActivity {

    @Override
    protected void init(List<BaseBean> list) {
        list.add(new BaseBean("单一动画介绍", 1));
        list.add(new BaseBean("两个动画组合", 2));
        list.add(new BaseBean("两个动画组合(1)", 3));
        list.add(new BaseBean("两个动画组合(2)", 4));
    }

    @Override
    protected void clickItem(BaseBean bean) {
        Intent intent = new Intent(this, AnimationMainActivity.class);
        intent.putExtra("code", bean.getCode());
        startActivity(intent);
    }
}