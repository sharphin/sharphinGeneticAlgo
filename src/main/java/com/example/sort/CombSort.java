package com.example.sort;

public class CombSort {
    private static int swapcount = 0;
    public CombSort(int arr[]) {
        int array[] = new int[arr.length];
        System.arraycopy(arr, 0, array, 0, arr.length);
        long startTime = System.nanoTime();
        array = sort(array);
        long endTime = System.nanoTime();
        System.out.println("comb sort");
        System.out.println("処理時間：" + (endTime - startTime) / 1000000f + "ms swap: "+swapcount);
        System.out.println("");
    }
    public static int[] sort(int array[]) {
        int gap = array.length;
        boolean swap = true;
        while(gap !=1 && swap) {
            gap = (int)(gap / 1.3);
            swap = false;
            if (gap < 1) gap = 1;
            for (int i = 0; i < array.length-gap;i++) {
                if(array[i] > array[i+gap]) {
                    swap = true;
                    swapcount++;
                    int temp = array[i];
                    array[i] = array[i+gap];
                    array[i+gap] = temp;
                }
            }
            if(!swap) break;
        }
        return array;
    }
}
