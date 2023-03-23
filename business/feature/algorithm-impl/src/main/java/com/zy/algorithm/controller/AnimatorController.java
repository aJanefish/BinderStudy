package com.zy.algorithm.controller;

import android.view.View;

import com.zy.algorithm.R;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.algorithm.mvp.IAlgorithmControllerView;

import java.util.List;

public class AnimatorController implements View.OnClickListener {

    private List<SortStepBean> stepList;
    private IAlgorithmControllerView view;
    private int length;
    private int curIndex = 0;

    private boolean auto = true;
    private boolean single_play = false;

    public AnimatorController(View root, IAlgorithmControllerView view) {
        this.view = view;
        root.findViewById(R.id.sort_controller_pause).setOnClickListener(this);
        root.findViewById(R.id.sort_controller_continue).setOnClickListener(this);
        root.findViewById(R.id.sort_controller_single_play).setOnClickListener(this);
        root.findViewById(R.id.sort_controller_from_zero).setOnClickListener(this);

        root.findViewById(R.id.sort_controller_last).setOnClickListener(this);
        root.findViewById(R.id.sort_controller_next).setOnClickListener(this);
        root.findViewById(R.id.sort_controller_fast).setOnClickListener(this);
        root.findViewById(R.id.sort_controller_low).setOnClickListener(this);
    }

    public void setStepList(List<SortStepBean> stepList) {
        this.stepList = stepList;
    }

    public int getCurIndex() {
        return curIndex;
    }

    public int geNextIndex() {
        updateCurIndex();
        return curIndex;
    }

    public void updateCurIndex() {
        if (auto) {
            curIndex++;
        }
    }

    public boolean isAuto() {
        return auto;
    }

    public boolean isSingle_play() {
        return single_play;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.sort_controller_pause) {
            //当前step结束后,不进入下一步
            auto = false;
            view.ani_pause();
        } else if (id == R.id.sort_controller_continue) {
            auto = true;
            view.ani_continue();
        } else if (id == R.id.sort_controller_single_play) {
            auto = false;
            single_play = true;
            view.ani_play_single();
            single_play = false;
        } else if (id == R.id.sort_controller_from_zero) {
            auto = true;
            curIndex = 0;
            view.ani_play_zero();
        } else if (id == R.id.sort_controller_last) {
            auto = false;
            curIndex--;
            if (curIndex < 0) {
                curIndex = 0;
            }
            view.ani_last();
        } else if (id == R.id.sort_controller_next) {
            auto = false;
            curIndex++;
            view.ani_next();
        } else if (id == R.id.sort_controller_fast) {
            //改变动画时间间隔
            view.ani_fast();
        } else if (id == R.id.sort_controller_low) {
            //改变动画时间间隔
            view.ani_low();
        }
    }
}