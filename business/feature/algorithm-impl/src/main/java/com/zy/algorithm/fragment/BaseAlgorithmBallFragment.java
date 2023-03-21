package com.zy.algorithm.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zy.algorithm.R;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.algorithm.controller.IndexController;
import com.zy.utils.AnimatorUtils;
import com.zy.zlog.ZLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseAlgorithmBallFragment extends BaseAlgorithmFragment {

    private static final String TAG = "BaseAlgorithmBall";

    @Override
    protected int getLayout() {
        return R.layout.fragment_algorithm_base;
    }

    private Map<Integer, Integer> colorMap = new ConcurrentHashMap<>();


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

    protected TextView[] dataTipsTVS = new TextView[10];
    protected IndexController indexController;


    @Override
    protected View[] getDataViewS() {
        return dataTVS;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        initEnteringContainer(view);
        indexController = new IndexController(view, stepOne);
        initUnSortView(view);
        initDataTV(view);

        initAnimationContainer(view);
    }

    private void initUnSortView(View view) {
        TextView index0 = view.findViewById(R.id.un_sort_index_0);
        TextView index1 = view.findViewById(R.id.un_sort_index_1);
        TextView index2 = view.findViewById(R.id.un_sort_index_2);
        TextView index3 = view.findViewById(R.id.un_sort_index_3);
        TextView index4 = view.findViewById(R.id.un_sort_index_4);
        TextView index5 = view.findViewById(R.id.un_sort_index_5);
        TextView index6 = view.findViewById(R.id.un_sort_index_6);
        TextView index7 = view.findViewById(R.id.un_sort_index_7);
        TextView index8 = view.findViewById(R.id.un_sort_index_8);
        TextView index9 = view.findViewById(R.id.un_sort_index_9);


        dataTipsTVS[0] = index0;
        dataTipsTVS[1] = index1;
        dataTipsTVS[2] = index2;
        dataTipsTVS[3] = index3;
        dataTipsTVS[4] = index4;
        dataTipsTVS[5] = index5;
        dataTipsTVS[6] = index6;
        dataTipsTVS[7] = index7;
        dataTipsTVS[8] = index8;
        dataTipsTVS[9] = index9;
    }

    private View entering_container;
    private View algorithm_context_container;

    private void initEnteringContainer(View view) {
        entering_container = view.findViewById(R.id.entering_container);
        algorithm_context_container = view.findViewById(R.id.algorithm_context_container);
    }

    @Override
    protected View getEnterContainer() {
        return entering_container;
    }

    @Override
    protected View getContextContainer() {
        return algorithm_context_container;
    }

    @Override
    protected void setSortDataTips(SortStepBean bean) {
        super.setSortDataTips(bean);
        for (int index = 0; index < dataTipsTVS.length; index++) {
            TextView textView = dataTipsTVS[index];
            textView.setText(Integer.toString(bean.getStepStart()[index]));
        }
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
                int num = bean.getStepEnd()[index];
                if (num == -1) {
                    textView.setText("");
                    textView.setVisibility(View.INVISIBLE);
                } else {
                    textView.setText(Integer.toString(bean.getStepEnd()[index]));
                }
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

    private int getCurStatues(int index) {
        Integer curStatus = colorMap.get(index);
        if (curStatus == null) {
            curStatus = STATUES_UNSORTED;
        }
        return curStatus;
    }

    protected void setDataItemGreen(int index) {
        int curStatus = getCurStatues(index);
        if (curStatus == STATUES_SORTED) {
            dataTVS[index].setBackgroundColor(getResources().getColor(R.color.statues_sorted));
        } else {
            //变色动画
            animationColor(dataTVS[index], curStatus, STATUES_SORTED);
        }
        colorMap.put(index, STATUES_SORTED);
    }

    private void animationColor(View view, int curStatus, int newStatues) {
        int cur;
        if (curStatus == STATUES_SORTED) {
            cur = getResources().getColor(R.color.statues_sorted);
        } else if (curStatus == STATUES_SORTING) {
            cur = getResources().getColor(R.color.statues_sorting);
        } else {
            cur = getResources().getColor(R.color.statues_unsorted);
        }

        int to;
        if (newStatues == STATUES_SORTED) {
            to = getResources().getColor(R.color.statues_sorted);
        } else if (newStatues == STATUES_SORTING) {
            to = getResources().getColor(R.color.statues_sorting);
        } else {
            to = getResources().getColor(R.color.statues_unsorted);
        }

        ValueAnimator valueAnimator = ValueAnimator.ofArgb(cur, to);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                view.setBackgroundColor(color);
            }
        });
        valueAnimator.start();
    }

    protected void setDataItemGray(int index) {
        int curStatus = getCurStatues(index);
        if (curStatus == STATUES_UNSORTED) {
            dataTVS[index].setBackgroundColor(getResources().getColor(R.color.statues_unsorted));
        } else {
            //变色动画
            animationColor(dataTVS[index], curStatus, STATUES_UNSORTED);
        }
        colorMap.put(index, STATUES_UNSORTED);
    }

    protected void setDataItemRed(int index) {
        int curStatus = getCurStatues(index);
        if (curStatus == STATUES_SORTING) {
            dataTVS[index].setBackgroundColor(getResources().getColor(R.color.statues_sorting));
        } else {
            //变色动画
            animationColor(dataTVS[index], curStatus, STATUES_SORTING);
        }
        colorMap.put(index, STATUES_SORTING);
    }

    //PK 区域View组件
    protected TextView pk_first;
    protected TextView pk_second;
    protected TextView pk_op;

    //初始化PK 区域
    private void initAnimationContainer(View view) {
        pk_first = view.findViewById(R.id.pk_first);
        pk_second = view.findViewById(R.id.pk_second);
        pk_op = view.findViewById(R.id.pk_op);
    }

    protected void showPK(SortStepBean curStepBean, StepListener listener) {
        //当前比较的数字View
        TextView originFirstTV = dataTVS[curStepBean.getOpFirstIndex()];
        TextView originSecondTV = dataTVS[curStepBean.getOpSecondIndex()];


        Rect originFirstTVRect = AnimatorUtils.getGlobalVisibleRect(originFirstTV);
        Rect originSecondTVRect = AnimatorUtils.getGlobalVisibleRect(originSecondTV);
        Log.d(TAG, "originFirstTVRect:" + originFirstTVRect);
        Log.d(TAG, "originFirstTVRect:" + originSecondTVRect);


        AnimatorUtils.move(originFirstTV, pk_first);
        AnimatorUtils.move(originSecondTV, pk_second, new AnimatorUtils.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                if (checkActivityDestroyed()) {
                    return;
                }

                //比较大小
                if (curStepBean.isResult()) {
                    AnimatorUtils.shake(originFirstTV, new AnimatorUtils.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                            super.onAnimationEnd();
                            if (checkActivityDestroyed()) {
                                return;
                            }


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
                            if (checkActivityDestroyed()) {
                                return;
                            }

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
        if (checkActivityDestroyed()) {
            return;
        }

        //设置当前操作位置
        setDataItemRed(curStepBean.getOpFirstIndex());
        setDataItemRed(curStepBean.getOpSecondIndex());

        //下标动画
        sortIndexAnimation(curStepBean, new AnimationListener() {
            @Override
            public void onAnimationEnd() {
                if (checkActivityDestroyed()) {
                    return;
                }
                //数据动画
                sortDataAnimation(curStepBean, listener);
            }
        });
    }

    protected void sortIndexAnimation(SortStepBean curStepBean, AnimationListener animationListener) {
        indexController.sortIndexAnimation(curStepBean, animationListener);
    }
}
