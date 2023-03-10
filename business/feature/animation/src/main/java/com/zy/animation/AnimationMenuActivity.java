package com.zy.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zy.animation.adapter.AnimationMenuAdapter;
import com.zy.animation.bean.AnimationMenuBean;

import java.util.ArrayList;
import java.util.List;

public class AnimationMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_menu);

        RecyclerView recyclerView = findViewById(R.id.activity_animation_menu_rv);

        List<AnimationMenuBean> list = new ArrayList<>();
        init(list);
        AnimationMenuAdapter adapter = new AnimationMenuAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void init(List<AnimationMenuBean> list) {
        list.add(new AnimationMenuBean("单一动画介绍", 1));
        list.add(new AnimationMenuBean("两个动画组合", 2));
        list.add(new AnimationMenuBean("两个动画组合(1)", 3));
        list.add(new AnimationMenuBean("两个动画组合(2)", 4));
    }

}