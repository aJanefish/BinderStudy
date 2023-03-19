package com.zy.algorithm.bean;

public class ExchangeBean {
    private final int firstIndex;
    private final int secondIndex;

    public ExchangeBean(int firstIndex, int secondIndex) {
        this.firstIndex = firstIndex;
        this.secondIndex = secondIndex;
    }


    public int getFirstIndex() {
        return firstIndex;
    }

    public int getSecondIndex() {
        return secondIndex;
    }
}
