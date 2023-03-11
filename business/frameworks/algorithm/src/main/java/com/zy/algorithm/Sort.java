package com.zy.algorithm;

import com.zy.utils.DataBuildUtils;
import com.zy.zlog.ZLog;

import java.util.Arrays;

public class Sort {

    //冒泡排序
    public static void bubbleSort() {
        ZLog.d("Sort", "bubbleSort");
        int[] array = DataBuildUtils.getRandomSortArray();
        ZLog.d("Sort", "排序前数组:" + Arrays.toString(array));
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        ZLog.d("Sort", "排序后数组:" + Arrays.toString(array));
    }


}
