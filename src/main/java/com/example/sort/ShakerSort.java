package com.example.sort;

public class ShakerSort {
    private static int swapcount = 0;
    public ShakerSort(int arr[]) {
        int array[] = new int[arr.length];
        System.arraycopy(arr, 0, array, 0, arr.length);
        System.out.println("shaker sort");

        long startTime = System.nanoTime();
        array = sort(array,0, array.length-2, true);
        long endTime = System.nanoTime();
        System.out.println("処理時間：" + (endTime - startTime) / 1000000f + "ms swap: "+swapcount);
        System.out.println("");
    }
    public static int[] sort(int array[], int start, int end,boolean incri) {
        boolean swap = false;
        int num = -1;
        if(incri) num = 1;
        for (int i = start,j = 1; j < array.length-1; i += num,j++) {
            if(array[i] > array[i+1]) {
                swap = true;
                swapcount++;
                int temp = array[i];
                array[i] = array[i+1];
                array[i+1] = temp;
            }
        }
        if(!swap) return array;
        int temp = start;
        start = end;
        end = temp;
        sort(array,start,end,!incri);

        return array;
    }
}
