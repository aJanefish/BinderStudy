package com.zy.algorithm.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortStepBean implements Cloneable {
    public static final int SIZE = 10;

    List<Integer> stepStartSorted;//已排序数据下标
    List<Integer> stepEndSorted;//已排序数据下标
    int firstIndex; //第一层循环下标
    int secondIndex; //第二层循环下标
    String op; //操作符
    int opFirstV; //第一个操作数
    int opSecondV; //第二个操作数
    int opFirstIndex; //本次操作位下标
    int opSecondIndex; //本次操作位下标
    boolean result; //本次操作结果
    int[] stepStart; //本次操作前
    int[] stepEnd; //本次操作后
    boolean needAnimation = true; //是否动画
    int compareSize = 0; //比较次数
    //定义交换-是两个位置交换
    int exchangeSize = 0; //交换次数
    boolean exchangeAnimation = false; //交换动画
    ExchangeBean exchangeBean;

    public List<Integer> getStepStartSorted() {
        return stepStartSorted;
    }

    public List<Integer> getStepEndSorted() {
        return stepEndSorted;
    }


    public void setStepStartSorted(int sortedSize, int length) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < length && i < sortedSize; i++) {
            list.add(length - i - 1);
        }

        this.stepStartSorted = list;
    }

    public void setStepStartSorted(int sortedSize) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < sortedSize; i++) {
            list.add(i);
        }
        this.stepStartSorted = list;
    }


    public void setStepEndSorted(int sortedSize, int length) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < length && i < sortedSize; i++) {
            list.add(length - i - 1);
        }

        this.stepEndSorted = list;
    }

    public void setStepEndSorted(int sortedSize) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < sortedSize; i++) {
            list.add(i);
        }
        this.stepEndSorted = list;
    }


    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }

    public int getSecondIndex() {
        return secondIndex;
    }

    public void setSecondIndex(int secondIndex) {
        this.secondIndex = secondIndex;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public int getOpFirstV() {
        return opFirstV;
    }

    public void setOpFirstV(int opFirstV) {
        this.opFirstV = opFirstV;
    }

    public int getOpSecondV() {
        return opSecondV;
    }

    public void setOpSecondV(int opSecondV) {
        this.opSecondV = opSecondV;
    }

    public int getOpFirstIndex() {
        return opFirstIndex;
    }

    public void setOpFirstIndex(int opFirstIndex) {
        this.opFirstIndex = opFirstIndex;
    }

    public int getOpSecondIndex() {
        return opSecondIndex;
    }

    public void setOpSecondIndex(int opSecondIndex) {
        this.opSecondIndex = opSecondIndex;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int[] getStepStart() {
        return stepStart;
    }

    public void setStepStart(int[] stepStart) {
        this.stepStart = stepStart;
    }

    public int[] getStepEnd() {
        return stepEnd;
    }

    public void setStepEnd(int[] stepEnd) {
        this.stepEnd = stepEnd;
    }

    public boolean isNeedAnimation() {
        return needAnimation;
    }

    public void setNeedAnimation(boolean needAnimation) {
        this.needAnimation = needAnimation;
    }

    public int getCompareSize() {
        return compareSize;
    }

    public void setCompareSize(int compareSize) {
        this.compareSize = compareSize;
    }

    public int getExchangeSize() {
        return exchangeSize;
    }

    public void setExchangeSize(int exchangeSize) {
        this.exchangeSize = exchangeSize;
    }

    public boolean isExchangeAnimation() {
        return exchangeAnimation;
    }

    public void setExchangeAnimation(boolean exchangeAnimation) {
        this.exchangeAnimation = exchangeAnimation;
    }

    public ExchangeBean getExchangeBean() {
        return exchangeBean;
    }

    public void setExchangeBean(ExchangeBean exchangeBean) {
        this.exchangeBean = exchangeBean;
    }

    @Override
    public String toString() {
        return "SortStepBean{" +
                "firstIndex=" + firstIndex +
                ", secondIndex=" + secondIndex +
                ", stepStart=" + Arrays.toString(stepStart) +
                ", stepEnd=" + Arrays.toString(stepEnd) +
                ", stepStartSorted=" + stepStartSorted +
                ", stepEndSorted=" + stepEndSorted +
                '}';
    }

    @Override
    public SortStepBean clone() {
        try {
            SortStepBean clone = (SortStepBean) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
