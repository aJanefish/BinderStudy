package com.zy.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zy.utils.DPUtils;

import java.util.Random;

public class SortSquareView extends FrameLayout {

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
        int data = new Random().nextInt(90) + 10;
        textView.setText("" + data);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, DPUtils.dip2px(context, data));
        columnView.setLayoutParams(params);
    }

}
