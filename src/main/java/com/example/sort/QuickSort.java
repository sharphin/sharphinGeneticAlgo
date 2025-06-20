package com.example.sort;

public class QuickSort {
    private static int swapcount = 0;
    public QuickSort(int arr[]) {
        int array[] = new int[arr.length];
        System.arraycopy(arr, 0, array, 0, arr.length);
        System.out.println("quick sort");
        //GenRandArray.printArray(array);
        long startTime = System.nanoTime();
        array = sort(array, 0,array.length-1);
        long endTime = System.nanoTime();
        //GenRandArray.printArray(array);
        System.out.println("処理時間：" + (endTime - startTime) / 1000000f + "ms swap: "+swapcount);
        System.out.println("");
    }
    public static int[] sort(int array[], int begin,int end) {
        if(end-begin < 2) return array;
        int pivot = array[(begin + end) / 2];
        int b = begin;
        int e = end;
        while (true) {
            while(pivot > array[b]) b++;
            while(pivot < array[e]) e--;

            if(b >= e) break;
            if(array[b] == array[e]) {
                b++;
                continue;
            }
            swapcount++;
            int temp = array[b];
            array[b] = array[e];
            array[e] = temp;
        }

        sort(array, begin, b - 1);
        sort(array, e + 1, end);
        return array;
    }
}
