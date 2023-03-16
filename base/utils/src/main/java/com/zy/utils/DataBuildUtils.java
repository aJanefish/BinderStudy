package com.zy.utils;

import java.util.Random;

public class DataBuildUtils {
    private static final int SIZE = 10;
    private static final int RANGE = 90;

    //10-99
    public static int[] getRandomSortArray() {
        Random random = new Random();
        int[] list = new int[SIZE];
        for (int index = 0; index < SIZE; index++) {
            list[index] = random.nextInt(RANGE) + 10;
        }
        return list;
    }
}