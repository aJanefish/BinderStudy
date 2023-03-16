package com.zy.algorithm;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zy.activity.BaseFragment;
import com.zy.algorithm.bean.SortStepBean;
import com.zy.utils.AnimatorUtils;
import com.zy.zlog.ZLog;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAlgorithmFragment extends BaseFragment {

    private static final String TAG = "BaseAlgorithm";

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


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDimen();
        initSortIndex(view);
        initDataTV(view);
        initAnimationContainer(view);

        initSetData();
        view.postDelayed(new Runnable() {

            @Override
            public void run() {
                startSort();
            }
        }, 2000);
    }

    float stepOne = 0;

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

    List<SortStepBean> stepList = null;

    protected abstract List<SortStepBean> getStepBean();

    protected void initSetData() {
        stepList = getStepBean();
        setSortData(stepList.get(0), true);
    }


    //默认10个数据
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

    private void initDataTV(View view) {
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

    protected void resetAll() {
        for (TextView dataTV : dataTVS) {
            dataTV.setTranslationX(0);
            dataTV.setTranslationY(0);
            dataTV.setScaleX(1f);
            dataTV.setScaleY(1f);
        }
    }

    protected void setDataItemGreen(int index) {
        dataTVS[index].setBackgroundColor(getResources().getColor(com.zy.activity.R.color.green));
    }

    protected void setDataItemGray(int index) {
        dataTVS[index].setBackgroundColor(getResources().getColor(com.zy.activity.R.color.gray));
    }

    protected void setDataItemRed(int index) {
        dataTVS[index].setBackgroundColor(getResources().getColor(com.zy.activity.R.color.red));
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
        //定位
        pk_first.setVisibility(View.VISIBLE);
        pk_second.setVisibility(View.VISIBLE);

//        pk_first.setText(Integer.toString(curStepBean.getFirstOpV()));
//        pk_second.setText(Integer.toString(curStepBean.getSecondOpV()));


        TextView originFirstTV = dataTVS[curStepBean.getSecondIndex()];
        TextView originSecondTV = dataTVS[curStepBean.getSecondIndex() + 1];


        ZLog.d(TAG, pk_first);
        ZLog.d(TAG, pk_second);
        ZLog.d(TAG, originFirstTV);
        ZLog.d(TAG, originSecondTV);

        pk_result.setText("pk");
        pk_op.setText(curStepBean.getOp());

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

    protected void startSort() {
        startSort(0);
    }

    protected void startSort(int index) {
        if (index >= stepList.size()) {
            return;
        }

        SortStepBean curStepBean = stepList.get(index);
        ZLog.d(TAG, index + " :" + curStepBean);


        if (curStepBean.isNeedAnimation()) {
            sortAnimation(curStepBean, new StepListener() {
                @Override
                public void nextStep() {
                    if (checkActivityDestroyed()) {
                        return;
                    }
                    setSortData(curStepBean, false);
                    resetAll();
                    startSort(index + 1);

                }
            });
        } else {
            setSortData(curStepBean, false);
            startSort(index + 1);
        }
    }

    protected boolean checkActivityDestroyed() {
        if (getActivity() == null || getActivity().isFinishing() || getActivity().isDestroyed()) {
            ZLog.e(TAG, "getActivity() == null || getActivity().isFinishing() || getActivity().isDestroyed()");
            return true;
        }
        return false;
    }


    private void sortDataAnimation(SortStepBean curStepBean, StepListener listener) {
        if (checkActivityDestroyed()) {
            return;
        }

        //设置当前操作位置
        setDataItemRed(curStepBean.getSecondIndex());
        setDataItemRed(curStepBean.getSecondIndex() + 1);

        //串行操作
        showPK(curStepBean, listener);
    }


    private void sortAnimation(SortStepBean curStepBean, StepListener listener) {
        //下标动画
        sortIndexAnimation(curStepBean, new AnimationListener() {
            @Override
            public void onAnimationEnd() {
                //数据动画
                sortDataAnimation(curStepBean, listener);
            }
        });
    }


    public interface StepListener {
        void nextStep();
    }

    public interface AnimationListener {
        void onAnimationEnd();
    }
}
