package com.zy.algorithm;

import com.zy.algorithm.bean.ExchangeBean;
import com.zy.algorithm.bean.InsertionSortStepBean;
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

        SortStepBean stepBean = null;

        int compareSize = 0; //比较次数
        int exchangeSize = 0; //交换次数

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {

                int firstOpV = array[j];
                int secondOpV = array[j + 1];

                boolean result = firstOpV > secondOpV;

                stepBean = new SortStepBean();

                stepBean.setFirstIndex(i);
                stepBean.setSecondIndex(j);
                stepBean.setOpFirstIndex(j);
                stepBean.setOpSecondIndex(j + 1);
                stepBean.setOp(">");
                stepBean.setOpFirstV(firstOpV);
                stepBean.setOpSecondV(secondOpV);
                stepBean.setResult(result);
                stepBean.setStepStart(Arrays.copyOf(array, array.length));
                stepBean.setCompareSize(compareSize++);
                stepBean.setExchangeSize(result ? ++exchangeSize : exchangeSize);

                stepBean.setStepStartSorted(i, SortStepBean.SIZE);

                if (result) {
                    array[j] = secondOpV;
                    array[j + 1] = firstOpV;
                }

                //记录过程
                //当前循环最后一次,更新已排序坐标
                if (j == array.length - 2 - i) {
                    stepBean.setStepEndSorted(i + 1, SortStepBean.SIZE);
                } else {
                    stepBean.setStepEndSorted(i, SortStepBean.SIZE);
                }

                stepBean.setStepEnd(Arrays.copyOf(array, array.length));
                stepList.add(stepBean);

            }

            ZLog.d("Sort", "第" + i + "轮排序后数组:" + Arrays.toString(array));

        }

        ZLog.d("Sort", "排序后数组:" + Arrays.toString(array));

        if (stepBean != null) {
            stepBean = stepBean.clone();
            stepBean.setStepStart(stepBean.getStepEnd());
            stepBean.setNeedAnimation(false);
            stepBean.setStepEndSorted(SortStepBean.SIZE, SortStepBean.SIZE);
            stepList.add(stepBean);
        }

        return stepList;
    }


    public static List<SortStepBean> bubbleSortAnimationV2() {
        ZLog.d("Sort", "bubbleSortAnimation");
        int[] array = DataBuildUtils.getRandomSortArray();
        return bubbleSortAnimationV2(array);
    }

    public static List<SortStepBean> bubbleSortAnimationV2(int[] array) {
        ZLog.d("Sort", "排序前数组:" + Arrays.toString(array));

        List<SortStepBean> stepList = new ArrayList<>();

        SortStepBean stepBean = null;

        int compareSize = 0; //比较次数
        int exchangeSize = 0; //交换次数


        for (int i = 0; i < array.length - 1; i++) {
            boolean exchange = false;
            for (int j = 0; j < array.length - 1 - i; j++) {

                int firstOpV = array[j];
                int secondOpV = array[j + 1];

                boolean result = firstOpV > secondOpV;

                stepBean = new SortStepBean();
                stepBean.setFirstIndex(i);
                stepBean.setSecondIndex(j);
                stepBean.setOpFirstIndex(j);
                stepBean.setOpSecondIndex(j + 1);
                stepBean.setOp(">");
                stepBean.setOpFirstV(firstOpV);
                stepBean.setOpSecondV(secondOpV);
                stepBean.setResult(result);
                stepBean.setStepStart(Arrays.copyOf(array, array.length));
                stepBean.setCompareSize(++compareSize);
                stepBean.setExchangeSize(result ? ++exchangeSize : exchangeSize);

                stepBean.setStepStartSorted(i, SortStepBean.SIZE);

                if (result) {
                    array[j] = secondOpV;
                    array[j + 1] = firstOpV;
                    exchange = true;
                }

                //记录过程
                //当前循环最后一次,更新已排序坐标
                if (j == array.length - 2 - i) {
                    stepBean.setStepEndSorted(i + 1, SortStepBean.SIZE);
                } else {
                    stepBean.setStepEndSorted(i, SortStepBean.SIZE);
                }

                stepBean.setStepEnd(Arrays.copyOf(array, array.length));
                stepList.add(stepBean);

            }

            ZLog.d("Sort", "第" + i + "轮排序后数组:" + Arrays.toString(array));
            if (!exchange) {
                //没有交换,退出循环
                break;
            }
        }

        ZLog.d("Sort", "排序后数组:" + Arrays.toString(array));

        if (stepBean != null) {
            stepBean = stepBean.clone();
            stepBean.setStepStart(stepBean.getStepEnd());
            stepBean.setNeedAnimation(false);
            stepBean.setStepEndSorted(SortStepBean.SIZE, SortStepBean.SIZE);
            stepList.add(stepBean);
        }
        return stepList;
    }


    //获取冒泡排序中已排序的位置下标List    [list.length - sortedSize,list.length]
    private static List<Integer> getBubbleSortedList(int sortedSize, int totalSize) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < totalSize && i < sortedSize; i++) {
            list.add(totalSize - i - 1);
        }
        return list;
    }


    public static List<SortStepBean> selectSortV1() {
        int[] array = DataBuildUtils.getRandomSortArray();
        return selectSortV1(array);
    }

    //选择排序
    public static List<SortStepBean> selectSortV1(int[] sort) {
        ZLog.d("Sort", "排序前数组:" + Arrays.toString(sort));

        List<SortStepBean> stepList = new ArrayList<>();

        int compareSize = 0; //比较次数
        int exchangeSize = 0; //交换次数

        int length = sort.length;
        SortStepBean stepBean = null;
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;//初始待排序的第一个元素为最小元素

            for (int j = i + 1; j < length; j++) {

                int firstOpV = sort[minIndex];
                int secondOpV = sort[j];
                boolean compareResult = firstOpV > secondOpV;


                stepBean = new SortStepBean();
                stepBean.setFirstIndex(i);
                stepBean.setSecondIndex(j);
                stepBean.setOpFirstIndex(minIndex);
                stepBean.setOpSecondIndex(j);
                stepBean.setOp(">");
                stepBean.setOpFirstV(firstOpV);
                stepBean.setOpSecondV(secondOpV);
                stepBean.setResult(compareResult);
                stepBean.setStepStart(Arrays.copyOf(sort, sort.length));
                stepBean.setCompareSize(++compareSize);
                stepBean.setExchangeSize(exchangeSize);
                stepBean.setStepStartSorted(i);

                if (compareResult) {
                    minIndex = j;//记录最小元素位置
                }

                if (j == length - 1) {
                    //每趟的最后一步-本来在循环外-处理，移到这里-便于记录step状态
                    //把最小元素移动到sort[i]
                    if (minIndex != i) { //不是本次默认下标，则交换位置
                        //这种情况下需要交换动画，反之不需要
                        //一趟结束才会存在交换-并不会每次都需要交换
                        stepBean.setExchangeSize(++exchangeSize);
                        stepBean.setExchangeAnimation(true);
                        stepBean.setExchangeBean(new ExchangeBean(minIndex, i));

                        //exchange start
                        int tmp = sort[i];
                        sort[i] = sort[minIndex];
                        sort[minIndex] = tmp;
                        //exchange end
                    } else {
                        stepBean.setExchangeSize(exchangeSize);
                    }

                    //每一趟最后一个-已排序个数+1
                    stepBean.setStepEndSorted(i + 1);
                } else {
                    stepBean.setStepEndSorted(i);
                }
                stepBean.setStepEnd(Arrays.copyOf(sort, sort.length));
                stepList.add(stepBean);
            }

            //System.out.println("第" + i + "趟简单选择排序结果:" + Arrays.toString(sort));
        }

        if (stepBean != null) {
            stepBean = stepBean.clone();
            stepBean.setStepStart(stepBean.getStepEnd());
            stepBean.setNeedAnimation(false);
            stepBean.setStepEndSorted(sort.length);
            stepList.add(stepBean);
        }
        return stepList;
    }


    public static List<SortStepBean> insertionSort() {
        int[] array = DataBuildUtils.getRandomSortArray();
        return insertionSort(array);
    }

    //选择排序
    public static List<SortStepBean> insertionSort(int[] sort) {
        ZLog.d("Sort", "排序前数组:" + Arrays.toString(sort));

        List<SortStepBean> stepList = new ArrayList<>();

        int compareSize = 0; //比较次数
        int exchangeSize = 0; //交换次数
        int moveSize = 0; //移动次数

        int length = sort.length;

        for (int firstIndex = 1; firstIndex < length; firstIndex++) {
            //保存当前比较数字
            int key = sort[firstIndex];
            boolean hasMoveRight = false;

            for (int secondIndex = firstIndex - 1; secondIndex >= 0; secondIndex--) {

                int opFirstIndex = secondIndex;
                int opSecondIndex = firstIndex;

                int firstOpV = sort[secondIndex];
                int secondOpV = key;

                boolean compareResult = firstOpV > secondOpV;

                InsertionSortStepBean stepBean = new InsertionSortStepBean();
                stepBean.setFirstIndex(firstIndex);
                stepBean.setSecondIndex(secondIndex);
                stepBean.setOpFirstIndex(opFirstIndex);
                stepBean.setOpSecondIndex(opSecondIndex);
                stepBean.setOpFirstV(firstOpV);
                stepBean.setOpSecondV(secondOpV);
                stepBean.setResult(compareResult);
                stepBean.setStepStart(Arrays.copyOf(sort, sort.length)); //移动之前的数组
                stepBean.setCompareSize(++compareSize);

                stepBean.setMoveSize(moveSize);

                stepBean.setCompareFirstInPer(secondIndex == firstIndex - 1);
                stepBean.setStepStartSorted(firstIndex + (hasMoveRight ? 1 : 0));

                if (compareResult) {
                    //当前位置的数字往后移一格
                    sort[secondIndex + 1] = sort[secondIndex];
                    //当前位置的数据设置为-1
                    sort[secondIndex] = -1;

                    stepBean.setMoveSize(++moveSize);
                    //move动画 -> 向后移动
                    stepBean.setMoveRight(true);
                    stepBean.setMoveRightFirstIndex(secondIndex);
                    stepBean.setMoveRightSecondIndex(secondIndex + 1);

                    hasMoveRight = true;
                } else {
                    //本次循环结束
                    sort[secondIndex + 1] = key;


                    //move动画 -> 数据填充
                    stepBean.setMoveBack(true);
                    stepBean.setMoveBackIndex(secondIndex + 1);
                    stepBean.setMoveSize(++moveSize);

                    stepBean.setStepEndSorted(firstIndex + 1);
                    stepBean.setStepEnd(Arrays.copyOf(sort, sort.length));
                    stepList.add(stepBean);

                    hasMoveRight = false;
                    break;
                }

                if (secondIndex == 0) {
                    //本次循环结束
                    sort[0] = key;


                    //move动画-> 数据填充
                    stepBean.setMoveBack(true);
                    stepBean.setMoveBackIndex(secondIndex);
                    stepBean.setMoveSize(++moveSize);

                    stepBean.setStepEndSorted(firstIndex + 1);
                    stepBean.setStepEnd(Arrays.copyOf(sort, sort.length));
                    stepList.add(stepBean);

                    hasMoveRight = false;
                    break;
                }

                //普通结束-还要开启下一次循环
                stepBean.setStepEndSorted(firstIndex + 1);
                stepBean.setStepEnd(Arrays.copyOf(sort, sort.length));//移动之后的数组
                stepList.add(stepBean);
            }
        }
        return stepList;
    }


    public static List<SortStepBean> shellSortBaseV1() {
        int[] sort = DataBuildUtils.getRandomSortArray();
        return shellSortBaseV1(sort);
    }

    public static List<SortStepBean> shellSortBaseV1(int[] sort) {
        List<SortStepBean> stepList = new ArrayList<>();
        int compareSize = 0; //比较次数
        int exchangeSize = 0; //交换次数
        int moveSize = 0; //移动次数

        int length = sort.length;

        int gap = length;
        do {
            gap = gap / 2;
            System.out.println("gap=" + gap + "      .........");
            //排序-分组
            for (int gapIndex = 0; gapIndex < gap; gapIndex++) {
                //0 5
                //1 6
                //2 7
                //3 8
                //4 9
                //0 2 4 6 8
                //1 3 5 7 9
                //0 1 2 3 4 5 6 7 8 9
                //分组内插入排序

                for (int firstIndex = gapIndex + gap; firstIndex < length; firstIndex = firstIndex + gap) {
                    //System.out.print(firstIndex + " ");


                    int key = sort[firstIndex];
                    boolean hasMoveRight = false;
                    for (int secondIndex = firstIndex - gap; secondIndex >= 0; secondIndex = secondIndex - gap) {
                        //操作数
                        int opFirstIndex = secondIndex;
                        int opSecondIndex = firstIndex;

                        int opSecondV = key;
                        int opFirstV = sort[secondIndex];

                        boolean compareResult = opFirstV > opSecondV;


                        InsertionSortStepBean stepBean = new InsertionSortStepBean();
                        stepBean.setFirstIndex(firstIndex);
                        stepBean.setSecondIndex(secondIndex);
                        stepBean.setOpFirstIndex(opFirstIndex);
                        stepBean.setOpSecondIndex(opSecondIndex);
                        stepBean.setOpFirstV(opFirstV);
                        stepBean.setOpSecondV(opSecondV);
                        stepBean.setResult(compareResult);
                        stepBean.setStepStart(Arrays.copyOf(sort, sort.length)); //移动之前的数组
                        stepBean.setCompareSize(++compareSize);

                        stepBean.setMoveSize(moveSize);

                        stepBean.setCompareFirstInPer(secondIndex == firstIndex - gap);

                        //需要仔细考虑一下
                        //stepBean.setStepStartSorted(firstIndex + (hasMoveRight ? 1 : 0));


                        if (compareResult) {
                            sort[secondIndex + gap] = sort[secondIndex];
                            //当前位置的数据设置为-1
                            sort[secondIndex] = -1;

                            hasMoveRight = true;


                            stepBean.setCompareSize(++moveSize);
                        } else {
                            sort[secondIndex + gap] = key;


                            stepBean.setCompareSize(++moveSize);
                            stepBean.setMoveBackIndex(secondIndex + gap);
                            stepBean.setMoveBack(true);

                            stepBean.setStepEnd(Arrays.copyOf(sort, sort.length));
                            stepList.add(stepBean);
                            hasMoveRight = false;
                            break;
                        }

                        if (secondIndex - gap < 0) {
                            sort[secondIndex] = key;


                            stepBean.setCompareSize(++moveSize);
                            stepBean.setMoveBack(true);
                            stepBean.setMoveBackIndex(secondIndex);

                            stepBean.setStepEnd(Arrays.copyOf(sort, sort.length));
                            stepList.add(stepBean);

                            hasMoveRight = false;
                            break;
                        }


                        stepBean.setStepEnd(Arrays.copyOf(sort, sort.length));//移动之后的数组
                        stepList.add(stepBean);
                    }


                }
            }
        } while (gap > 1);

        return stepList;
    }
}
