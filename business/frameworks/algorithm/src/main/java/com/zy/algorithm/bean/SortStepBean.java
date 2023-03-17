package com.zy.algorithm.bean;

import java.util.List;

public class SortStepBean {
    public static final int SIZE = 10;

    List<Integer> sorted;//已排序数据下标
    int firstIndex; //第一层循环下标
    int secondIndex; //第二层循环下标
    int firstOpV; //第一个操作数
    int secondOpV; //第二个操作数
    String op; //操作符
    int opFirst; //本次操作位下标
    int opSecond; //本次操作位下标
    boolean result; //本次操作结果
    int[] stepStart; //本次操作前
    int[] stepEnd; //本次操作后
    boolean needAnimation = true; //是否动画
    int compareSize = 0; //比较次数
    int exchangeSize = 0; //交换次数


    public List<Integer> getSorted() {
        return sorted;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public int getSecondIndex() {
        return secondIndex;
    }

    public int getFirstOpV() {
        return firstOpV;
    }

    public int getSecondOpV() {
        return secondOpV;
    }

    public String getOp() {
        return op;
    }

    public boolean isResult() {
        return result;
    }

    public int[] getStepStart() {
        return stepStart;
    }

    public int[] getStepEnd() {
        return stepEnd;
    }

    public boolean isNeedAnimation() {
        return needAnimation;
    }

    public int getOpFirst() {
        return opFirst;
    }

    public int getOpSecond() {
        return opSecond;
    }

    public int getCompareSize() {
        return compareSize;
    }

    public int getExchangeSize() {
        return exchangeSize;
    }

    @Override
    public String toString() {
        return "SortStepBean{" +
                "sorted=" + sorted +
                ", firstIndex=" + firstIndex +
                ", secondIndex=" + secondIndex +
                ", firstOpV=" + firstOpV +
                ", secondOpV=" + secondOpV +
                ", op='" + op + '\'' +
                ", opFirst='" + opFirst + '\'' +
                ", opSecond='" + opSecond + '\'' +
                ", result=" + result +
                '}';
    }

    public SortStepBean(Builder builder) {
        this.sorted = builder.sorted;
        this.firstIndex = builder.firstIndex;
        this.secondIndex = builder.secondIndex;
        this.firstOpV = builder.firstOpV;
        this.secondOpV = builder.secondOpV;
        this.op = builder.op;
        this.opFirst = builder.opFirst;
        this.opSecond = builder.opSecond;
        this.result = builder.result;
        this.stepStart = builder.stepStart;
        this.stepEnd = builder.stepEnd;
        this.needAnimation = builder.needAnimation;
        this.compareSize = builder.compareSize;
        this.exchangeSize = builder.exchangeSize;
    }

    public static class Builder {
        List<Integer> sorted;
        int firstIndex;
        int secondIndex;
        int firstOpV;
        int secondOpV;
        String op;
        boolean result;
        int[] stepStart; //本次操作前
        int[] stepEnd; //本次操作后
        int opFirst; //本次操作位下标
        int opSecond; //本次操作位下标
        boolean needAnimation = true; //是否动画
        int compareSize = 0; //比较次数
        int exchangeSize = 0; //交换次数


        public Builder setSorted(List<Integer> sorted) {
            this.sorted = sorted;
            return this;
        }

        public Builder setFirstIndex(int firstIndex) {
            this.firstIndex = firstIndex;
            return this;
        }

        public Builder setSecondIndex(int secondIndex) {
            this.secondIndex = secondIndex;
            return this;
        }

        public Builder setFirstOpV(int firstOpV) {
            this.firstOpV = firstOpV;
            return this;
        }

        public Builder setSecondOpV(int secondOpV) {
            this.secondOpV = secondOpV;
            return this;
        }

        public Builder setOp(String op) {
            this.op = op;
            return this;
        }

        public Builder setResult(boolean result) {
            this.result = result;
            return this;
        }

        public Builder setStepStart(int[] stepStart) {
            this.stepStart = stepStart;
            return this;
        }

        public Builder setStepEnd(int[] stepEnd) {
            this.stepEnd = stepEnd;
            return this;
        }

        public Builder setNeedAnimation(boolean needAnimation) {
            this.needAnimation = needAnimation;
            return this;
        }

        public void setOpFirst(int opFirst) {
            this.opFirst = opFirst;
        }

        public void setOpSecond(int opSecond) {
            this.opSecond = opSecond;
        }

        public int[] getStepEnd() {
            return this.stepEnd;
        }

        public void setCompareSize(int compareSize) {
            this.compareSize = compareSize;
        }

        public void setExchangeSize(int exchangeSize) {
            this.exchangeSize = exchangeSize;
        }

        public SortStepBean build() {
            return new SortStepBean(this);
        }

    }
}
