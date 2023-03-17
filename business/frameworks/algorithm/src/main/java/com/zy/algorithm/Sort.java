package com.zy.algorithm;

import com.zy.algorithm.bean.SortStepBean;
import com.zy.utils.DataBuildUtils;
import com.zy.zlog.ZLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sort {

    //冒泡排序
    public static void bubbleSortV1() {
        ZLog.d("Sort", "bubbleSortV1");
        int[] array = DataBuildUtils.getRandomSortArray();
        ZLog.d("Sort", "排序前数组:" + Arrays.toString(array));
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                //交换
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        ZLog.d("Sort", "排序后数组:" + Arrays.toString(array));
    }

    //优化-1
    //在第一版本的基础上优化-如果本次循环没有交换-则已经排序OK
    public static void bubbleSortV2() {
        ZLog.d("Sort", "bubbleSortV2");
        int[] array = DataBuildUtils.getRandomSortArray();
        ZLog.d("Sort", "排序前数组:" + Arrays.toString(array));
        for (int i = 0; i < array.length - 1; i++) {
            boolean exchange = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    exchange = true;
                }
            }

            if (!exchange) {
                //没有交换,退出循环
                break;
            }
        }
        ZLog.d("Sort", "排序后数组:" + Arrays.toString(array));
    }

    public static List<SortStepBean> bubbleSortAnimationV1() {
        ZLog.d("Sort", "bubbleSortAnimation");
        int[] array = DataBuildUtils.getRandomSortArray();

        //int[] array = {99, 88, 77, 66, 55, 44, 33, 22, 11, 4};

        ZLog.d("Sort", "排序前数组:" + Arrays.toString(array));

        List<SortStepBean> stepList = new ArrayList<>();

        SortStepBean.Builder builder = null;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {

                int firstOpV = array[j];
                int secondOpV = array[j + 1];

                boolean exchange = firstOpV > secondOpV;

                builder = new SortStepBean.Builder();

                builder.setFirstIndex(i);
                builder.setSecondIndex(j);
                builder.setOpFirst(j);
                builder.setOpSecond(j + 1);
                builder.setOp(">");
                builder.setFirstOpV(firstOpV);
                builder.setSecondOpV(secondOpV);
                builder.setResult(exchange);
                builder.setStepStart(Arrays.copyOf(array, array.length));

                if (exchange) {
                    array[j] = secondOpV;
                    array[j + 1] = firstOpV;
                }

                //记录过程
                //当前循环最后一次,更新已排序坐标
                if (j == array.length - 2 - i) {
                    builder.setSorted(getBubbleSortedList(i + 1, SortStepBean.SIZE));
                } else {
                    builder.setSorted(getBubbleSortedList(i, SortStepBean.SIZE));
                }
                stepList.add(builder.setStepEnd(Arrays.copyOf(array, array.length)).build());

            }

            ZLog.d("Sort", "第" + i + "轮排序后数组:" + Arrays.toString(array));

        }

        ZLog.d("Sort", "排序后数组:" + Arrays.toString(array));

        if (builder != null) {
            builder.setStepStart(builder.getStepEnd());
            builder.setNeedAnimation(false);
            builder.setSorted(getBubbleSortedList(SortStepBean.SIZE, SortStepBean.SIZE));
            stepList.add(builder.build());
        }

        return stepList;
    }


    public static List<SortStepBean> bubbleSortAnimationV2() {
        ZLog.d("Sort", "bubbleSortAnimation");
        //int[] array = DataBuildUtils.getRandomSortArray();

        int[] array = {5, 11, 22, 33, 44, 55, 66, 77, 88, 99};

        ZLog.d("Sort", "排序前数组:" + Arrays.toString(array));

        List<SortStepBean> stepList = new ArrayList<>();

        SortStepBean.Builder builder = null;
        for (int i = 0; i < array.length - 1; i++) {
            boolean exchange = false;
            for (int j = 0; j < array.length - 1 - i; j++) {

                int firstOpV = array[j];
                int secondOpV = array[j + 1];

                boolean result = firstOpV > secondOpV;

                builder = new SortStepBean.Builder();

                builder.setFirstIndex(i);
                builder.setSecondIndex(j);
                builder.setOpFirst(j);
                builder.setOpSecond(j + 1);
                builder.setOp(">");
                builder.setFirstOpV(firstOpV);
                builder.setSecondOpV(secondOpV);
                builder.setResult(result);
                builder.setStepStart(Arrays.copyOf(array, array.length));

                if (result) {
                    array[j] = secondOpV;
                    array[j + 1] = firstOpV;
                    exchange = true;
                }

                //记录过程
                //当前循环最后一次,更新已排序坐标
                if (j == array.length - 2 - i) {
                    builder.setSorted(getBubbleSortedList(i + 1, SortStepBean.SIZE));
                } else {
                    builder.setSorted(getBubbleSortedList(i, SortStepBean.SIZE));
                }
                stepList.add(builder.setStepEnd(Arrays.copyOf(array, array.length)).build());

            }

            ZLog.d("Sort", "第" + i + "轮排序后数组:" + Arrays.toString(array));
            if (!exchange) {
                //没有交换,退出循环
                break;
            }
        }

        ZLog.d("Sort", "排序后数组:" + Arrays.toString(array));

        if (builder != null) {
            builder.setStepStart(builder.getStepEnd());
            builder.setNeedAnimation(false);
            builder.setSorted(getBubbleSortedList(SortStepBean.SIZE, SortStepBean.SIZE));
            stepList.add(builder.build());
        }

        return stepList;
    }


    private static List<Integer> getBubbleSortedList(int sortedSize, int totalSize) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < totalSize && i < sortedSize; i++) {
            list.add(totalSize - i - 1);
        }

        return list;
    }
}
