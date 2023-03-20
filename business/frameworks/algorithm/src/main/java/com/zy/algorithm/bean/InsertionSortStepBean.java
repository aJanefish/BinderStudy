package com.zy.algorithm.bean;

public class InsertionSortStepBean extends SortStepBean {

    //定义移动-是单个元素前移或者后移
    int moveSize = 0; //移动次数

    //每趟比较的第一次
    boolean compareFirstInPer = false;

    //数据后移
    boolean moveRight = false;
    int moveRightFirstIndex;
    int moveRightSecondIndex;

    //数据回设
    boolean moveBack = false;
    int moveBackIndex;

    public int getMoveSize() {
        return moveSize;
    }

    public void setMoveSize(int moveSize) {
        this.moveSize = moveSize;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public int getMoveRightFirstIndex() {
        return moveRightFirstIndex;
    }

    public void setMoveRightFirstIndex(int moveRightFirstIndex) {
        this.moveRightFirstIndex = moveRightFirstIndex;
    }

    public int getMoveRightSecondIndex() {
        return moveRightSecondIndex;
    }

    public void setMoveRightSecondIndex(int moveRightSecondIndex) {
        this.moveRightSecondIndex = moveRightSecondIndex;
    }

    public boolean isMoveBack() {
        return moveBack;
    }

    public void setMoveBack(boolean moveBack) {
        this.moveBack = moveBack;
    }

    public int getMoveBackIndex() {
        return moveBackIndex;
    }

    public void setMoveBackIndex(int moveBackIndex) {
        this.moveBackIndex = moveBackIndex;
    }

    public boolean isCompareFirstInPer() {
        return compareFirstInPer;
    }

    public void setCompareFirstInPer(boolean compareFirstInPer) {
        this.compareFirstInPer = compareFirstInPer;
    }
}
