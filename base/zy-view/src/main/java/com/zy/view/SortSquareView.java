package com.zy.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zy.utils.DPUtils;

import java.util.Random;

public class SortSquareView extends FrameLayout {

    public static final int STATUES_UNSORTED = 1;
    public static final int STATUES_SORTING = 2;
    public static final int STATUES_SORTED = 3;

    TextView textView;
    TextView columnView;

    public SortSquareView(Context context) {
        super(context);
        init(context);
    }


    public SortSquareView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SortSquareView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public SortSquareView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.sort_square_layout, this);
        textView = root.findViewById(R.id.sort_square_layout_data);
        columnView = root.findViewById(R.id.sort_square_layout_post);
//        int data = new Random().nextInt(90) + 10;
//        setNum(data);
//        setStatues(new Random().nextInt(3) + 1);
    }

    public void setNum(int num) {
        textView.setText(Integer.toString(num));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, DPUtils.dip2px(getContext(), num) * 2);
        columnView.setLayoutParams(params);
    }


    int lastStatues = STATUES_UNSORTED;

    public void setStatues(int statues) {
        if (statues != lastStatues) { //动画
            int to;
            if (statues == STATUES_SORTED) {
                to = getResources().getColor(R.color.statues_sorted);
            } else if (statues == STATUES_SORTING) {
                to = getResources().getColor(R.color.statues_sorting);
            } else {
                to = getResources().getColor(R.color.statues_unsorted);
            }

            int from;
            if (lastStatues == STATUES_SORTED) {
                from = getResources().getColor(R.color.statues_sorted);
            } else if (lastStatues == STATUES_SORTING) {
                from = getResources().getColor(R.color.statues_sorting);
            } else {
                from = getResources().getColor(R.color.statues_unsorted);
            }
            animationColor(from, to);
        } else {
            if (statues == STATUES_SORTED) {
                columnView.setBackgroundColor(getResources().getColor(R.color.statues_sorted));
            } else if (statues == STATUES_SORTING) {
                columnView.setBackgroundColor(getResources().getColor(R.color.statues_sorting));
            } else {
                columnView.setBackgroundColor(getResources().getColor(R.color.statues_unsorted));
            }
        }
        lastStatues = statues;
    }

    private void animationColor(int from, int to) {
       // Color.RED
        ValueAnimator valueAnimator = ValueAnimator.ofArgb(from, to);
        valueAnimator.setDuration(300);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                columnView.setBackgroundColor(color);
            }
        });
        valueAnimator.start();

    }
}
