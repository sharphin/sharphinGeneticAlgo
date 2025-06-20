package com.example.sort;

import java.util.Arrays;

public class DefaultSort {
    public DefaultSort(int arr[]) {
        int array[] = new int[arr.length];
        System.arraycopy(arr, 0, array, 0, arr.length);
        long startTime = System.nanoTime();
        Arrays.sort(array);
        long endTime = System.nanoTime();
        System.out.println("java Arrays sort");
        System.out.println("処理時間：" + (endTime - startTime) / 1000000f + "ms swap: -1");
        System.out.println("");
    }
}
