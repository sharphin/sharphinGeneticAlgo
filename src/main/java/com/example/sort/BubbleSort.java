package com.example.sort;

public class BubbleSort {
    private static int swapcount = 0;
    public BubbleSort(int arr[]) {
        int array[] = new int[arr.length];
        System.arraycopy(arr, 0, array, 0, arr.length);
        System.out.println("bubble sort");
        long startTime = System.nanoTime();
        array = sort(array);
        long endTime = System.nanoTime();
        System.out.println("処理時間：" + (endTime - startTime) / 1000000f + "ms swap: "+swapcount);
        System.out.println("");
    }
    public static int[] sort(int array[]) {
        boolean swap = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length;j++) {
                if(array[i] < array[j]) {
                    swap = true;
                    swapcount++;
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            if(!swap) break;
        }
        return array;
    }
}
