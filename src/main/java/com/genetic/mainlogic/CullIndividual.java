package com.genetic.mainlogic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import com.genetic.utility.ImageUtil;

public class CullIndividual {
    //一世代の最大個体数（2のN乗）の画像の色データをLinkedlistに格納し
    //一対一でそれぞれ戦わせ、負けたほうを間引いていく。
    //方法は元の画像と一ピクセルごとにRGB値を比較していきその色差の合計が少ないほうが勝ち。
    //勝ち残った画像から次の世代の個体を生成する
    private LinkedList<int[][]> cullImage;
    private final int survived_individual;

    public CullIndividual(int [][][] imagergb, int survived_individual) {
        cullImage = new LinkedList<>(Arrays.asList(imagergb));
        this.survived_individual = survived_individual;
    }
    //無数ある画像から優秀な画像をのぞき、間引き
    public void cull(int originimage[][]) {
        for(int i = 0;i < cullImage.size()-1; i+=2){
            double diff1 = 0.0;
            double diff2 = 0.0;
            if (cullImage.size() <= survived_individual) return;
            for(int y = 0;y < 50; y++) {
                for(int x = 0;x < 50; x++) {
                    diff1 += ImageUtil.euclidDistance(originimage[y][x],cullImage.get(i)[y][x]);
                    diff2 += ImageUtil.euclidDistance(originimage[y][x],cullImage.get(i+1)[y][x]);
                }
            }
            if (diff1 >= diff2) {
                cullImage.remove(i);
            } else {
                cullImage.remove(i+1);
            }
        }
        cull(originimage);
    }
    // 生き残った画像を交配させ新しい画像を生成
    private int[][] matingImage(double mutation) {
        int newImage[][] = new int[50][50];
        for(int y = 0;y < 50; y++) {
            for(int x = 0;x < 50; x++) {
                ThreadLocalRandom current = ThreadLocalRandom.current();
                double rand = current.nextInt(0, (int)(100.0/mutation)); 
                if(rand == 0) {
                    newImage[y][x] = ImageUtil.randomRGB();
                } else {
                    newImage[y][x] = cullImage.get(current.nextInt(0, survived_individual))[y][x];
                }
            }
        }
        return newImage;
    } 
    public int[][][] createNextGeneration(int individual_max, double mutation) {
        int [][][] nextgen = new int[individual_max][50][50];
        for(int i = 0; i < individual_max;i++) {
            nextgen[i] = matingImage(mutation);
        }
        return nextgen;
    }
}
