package com.example.genarray;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GenRandArray {
    final int length,randtype;
    public GenRandArray(int length,int randtype) {
        this.length = length;
        this.randtype = randtype;
    }
    public int [] generate() {
        if(randtype == 1) return generateStepRandArray();
        else return generateRandArray();
    }
    private int[] generateRandArray() {
        int array[] = new int[length];
        ThreadLocalRandom current = ThreadLocalRandom.current();
        for (int i = 0;i < length; i++) {
            array[i] = current.nextInt(0,length);
        }
        return array;
    }
    private int [] generateStepRandArray() {
        int array[] = new int[length];
        for (int i = 0 ;i < length;i++) {
            array[i] = i;
        }
        Random rnd = ThreadLocalRandom.current();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int tmp = array[index];
            array[index] = array[i];
            array[i] = tmp;
        }
        return array;
    }
    public static void printArray(int array[]) {
        for (int elem :array) {
            System.out.print(elem+" ");
        }
        System.out.println("");
    }
}
