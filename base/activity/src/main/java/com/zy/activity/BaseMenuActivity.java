package com.zy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zy.activity.adapter.BaseAdapter;
import com.zy.activity.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMenuActivity extends AppCompatActivity implements BaseAdapter.ClickCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_menu);

        RecyclerView recyclerView = findViewById(R.id.base_menu_rv);
        List<BaseBean> list = new ArrayList<>();
        init(list);
        BaseAdapter adapter = new BaseAdapter(list);
        adapter.setClickCallBack(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void click(BaseBean bean) {
        clickItem(bean);
    }

    protected abstract void init(List<BaseBean> list);

    protected abstract void clickItem(BaseBean bean);
}