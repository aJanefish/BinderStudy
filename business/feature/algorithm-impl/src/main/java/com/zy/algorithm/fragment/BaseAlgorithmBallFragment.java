package com.zy.algorithm.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zy.algorithm.R;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.utils.AnimatorUtils;
import com.zy.zlog.ZLog;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAlgorithmBallFragment extends BaseAlgorithmFragment {

    private static final String TAG = "BaseAlgorithmBall";

    @Override
    protected int getLayout() {
        return R.layout.fragment_algorithm_base;
    }

    //下标
    protected View sort_first_index;
    protected View sort_second_index;

    //数据内容
    protected TextView sort_index_0;
    protected TextView sort_index_1;
    protected TextView sort_index_2;
    protected TextView sort_index_3;
    protected TextView sort_index_4;
    protected TextView sort_index_5;
    protected TextView sort_index_6;
    protected TextView sort_index_7;
    protected TextView sort_index_8;
    protected TextView sort_index_9;

    protected TextView[] dataTVS = new TextView[10];


    float stepOne = 0;

    @Override
    protected View[] getDataViewS() {
        return dataTVS;
    }

    private void initDimen() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;

        float dp_8 = getResources().getDimension(R.dimen.dp_8);

        ZLog.d(TAG, "displayMetrics:" + displayMetrics);
        ZLog.d(TAG, "heightPixels:" + heightPixels);
        ZLog.d(TAG, "widthPixels:" + widthPixels);
        ZLog.d(TAG, "dp_8:" + dp_8);
        stepOne = (widthPixels - dp_8) / 10;
        ZLog.d(TAG, "stepOne:" + stepOne);
    }


    @Override
    protected void initView(View view) {
        initDimen();
        initSortIndex(view);
        initDataTV(view);
        initTitle(view);

        initAnimationContainer(view);
    }

    private void initTitle(View view) {
        TextView title = view.findViewById(R.id.sort_title);
        title.setText(getTitle());
    }

    //设置数据
    @Override
    protected void setSortData(SortStepBean bean, boolean start) {
        //设置颜色
        List<Integer> sorted = bean.getSorted();
        //设置数据
        for (int index = 0; index < dataTVS.length; index++) {
            TextView textView = dataTVS[index];
            if (start) {
                textView.setText(Integer.toString(bean.getStepStart()[index]));
            } else {
                textView.setText(Integer.toString(bean.getStepEnd()[index]));
            }

            if (sorted.contains(index)) {
                setDataItemGreen(index);
            } else {
                setDataItemGray(index);
            }
        }
    }


    protected void initDataTV(View view) {
        sort_index_0 = view.findViewById(R.id.sort_index_0);
        sort_index_1 = view.findViewById(R.id.sort_index_1);
        sort_index_2 = view.findViewById(R.id.sort_index_2);
        sort_index_3 = view.findViewById(R.id.sort_index_3);
        sort_index_4 = view.findViewById(R.id.sort_index_4);
        sort_index_5 = view.findViewById(R.id.sort_index_5);
        sort_index_6 = view.findViewById(R.id.sort_index_6);
        sort_index_7 = view.findViewById(R.id.sort_index_7);
        sort_index_8 = view.findViewById(R.id.sort_index_8);
        sort_index_9 = view.findViewById(R.id.sort_index_9);

        dataTVS[0] = sort_index_0;
        dataTVS[1] = sort_index_1;
        dataTVS[2] = sort_index_2;
        dataTVS[3] = sort_index_3;
        dataTVS[4] = sort_index_4;
        dataTVS[5] = sort_index_5;
        dataTVS[6] = sort_index_6;
        dataTVS[7] = sort_index_7;
        dataTVS[8] = sort_index_8;
        dataTVS[9] = sort_index_9;
    }

    protected void setDataItemGreen(int index) {
        dataTVS[index].setBackgroundColor(getResources().getColor(R.color.statues_sorted));
    }

    protected void setDataItemGray(int index) {
        dataTVS[index].setBackgroundColor(getResources().getColor(R.color.statues_unsorted));
    }

    protected void setDataItemRed(int index) {
        dataTVS[index].setBackgroundColor(getResources().getColor(R.color.statues_sorting));
    }

    private void initSortIndex(View view) {
        sort_first_index = view.findViewById(R.id.sort_first_index);
        sort_second_index = view.findViewById(R.id.sort_second_index);
    }

    //PK 区域View组件
    protected TextView pk_first;
    protected TextView pk_second;
    protected TextView pk_op;
    protected TextView pk_result;

    //初始化PK 区域
    private void initAnimationContainer(View view) {
        pk_first = view.findViewById(R.id.pk_first);
        pk_second = view.findViewById(R.id.pk_second);
        pk_op = view.findViewById(R.id.pk_op);
        pk_result = view.findViewById(R.id.pk_result);
    }

    protected void showPK(SortStepBean curStepBean, StepListener listener) {
        //当前比较的数字View
        TextView originFirstTV = dataTVS[curStepBean.getOpFirstIndex()];
        TextView originSecondTV = dataTVS[curStepBean.getOpSecondIndex()];


        pk_result.setText("");

        Rect originFirstTVRect = AnimatorUtils.getGlobalVisibleRect(originFirstTV);
        Rect originSecondTVRect = AnimatorUtils.getGlobalVisibleRect(originSecondTV);
        Log.d(TAG, "originFirstTVRect:" + originFirstTVRect);
        Log.d(TAG, "originFirstTVRect:" + originSecondTVRect);


        AnimatorUtils.move(originFirstTV, pk_first);
        AnimatorUtils.move(originSecondTV, pk_second, new AnimatorUtils.AnimationListener() {
            @Override
            public void onAnimationEnd() {

                //比较大小
                if (curStepBean.isResult()) {
                    AnimatorUtils.shake(originFirstTV, new AnimatorUtils.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                            super.onAnimationEnd();

                            AnimatorUtils.move(originFirstTV, originSecondTVRect);
                            AnimatorUtils.move(originSecondTV, originFirstTVRect, new AnimatorUtils.AnimationListener() {
                                @Override
                                public void onAnimationEnd() {
                                    super.onAnimationEnd();
                                    listener.nextStep();
                                }
                            });
                        }
                    });
                } else {
                    AnimatorUtils.shake(originSecondTV, new AnimatorUtils.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                            super.onAnimationEnd();
                            AnimatorUtils.reset(originFirstTV);
                            AnimatorUtils.reset(originSecondTV, new AnimatorUtils.AnimationListener() {
                                @Override
                                public void onAnimationEnd() {
                                    super.onAnimationEnd();
                                    listener.nextStep();
                                }
                            });
                        }
                    });
                }

                pk_result.setText(curStepBean.isResult() ? "交换" : "不交换");
            }
        });
    }

    //下标动画
    protected void sortIndexAnimation(SortStepBean curStepBean, AnimationListener listener) {
        int firstIndex = curStepBean.getFirstIndex();
        int secondIndex = curStepBean.getSecondIndex();

        //下标转换为偏移量

        ObjectAnimator firstIndexAni = ObjectAnimator.ofFloat(sort_first_index, "translationX", sort_first_index.getTranslationX(), firstIndex * stepOne);
        ObjectAnimator secondIndexAni = ObjectAnimator.ofFloat(sort_second_index, "translationX", sort_second_index.getTranslationX(), secondIndex * stepOne);

        List<Animator> list = new ArrayList<>();
        list.add(firstIndexAni);
        list.add(secondIndexAni);

        AnimatorUtils.togetherStart(list, 200, new AnimatorUtils.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                super.onAnimationEnd();
                listener.onAnimationEnd();
            }
        });
    }


    private void sortDataAnimation(SortStepBean curStepBean, StepListener listener) {
        if (checkActivityDestroyed()) {
            return;
        }


        //串行操作
        showPK(curStepBean, listener);
    }


    @Override
    protected void sortAnimation(int index, SortStepBean curStepBean, StepListener listener) {
        //设置当前操作位置
        setDataItemRed(curStepBean.getOpFirstIndex());
        setDataItemRed(curStepBean.getOpSecondIndex());

        //下标动画
        sortIndexAnimation(curStepBean, new AnimationListener() {
            @Override
            public void onAnimationEnd() {
                //数据动画
                sortDataAnimation(curStepBean, listener);
            }
        });
    }
}
