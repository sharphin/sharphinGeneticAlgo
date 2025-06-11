package com.genetic.mainlogic;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import com.genetic.file.FileEdit;
import com.genetic.utility.ImageUtil;

public class CullIndividual {
    //use linkedList reason id isnert remove fast data structure
    private LinkedList<int[][]> cullImage;

    public CullIndividual() {
        cullImage = new LinkedList<int[][]>();
    }
    public void addImage(int [][] imagergb) {
        cullImage.add(imagergb);
    }
    //cull Type1
    public void cull(int individual_max,int originimage[][]) {
        for(int i = 0;i < cullImage.size()-1; i+=2){
            double diff1 = 0.0;
            double diff2 = 0.0;
            if (cullImage.size() <= 4) return;
            for(int y = 0;y < 50; y++){
                for(int x = 0;x < 50; x++) {
                    diff1 += ImageUtil.euclidDistance(originimage[y][x],cullImage.get(i)[y][x]);
                    diff2 += ImageUtil.euclidDistance(originimage[y][x],cullImage.get(i+1)[y][x]);
                }
            }
            if (diff1 >= diff2) {
                killImage(i);
            } else {
                killImage(i+1);
            }
        }
        cull(individual_max,originimage);
    }
    //cull type2 coming soon

    //
    public void createNextGeneration(String path,int gen, int individual_max,double mutation) {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        for(int i = 0; i < individual_max; i++) {
            int newImage[][] = new int[50][50];
            for(int y = 0;y < 50; y++){
                for(int x = 0;x < 50; x++) {
                    double rand = current.nextInt(0, 100); 
                    if(rand == 0) {
                        int r = ImageUtil.rand();
                        int g = ImageUtil.rand();
                        int b = ImageUtil.rand();
                        int rgb = ImageUtil.rgb(r,g,b);
                        newImage[y][x] = rgb;
                    } else {
                        newImage[y][x] = cullImage.get(ImageUtil.rand2())[y][x];
                    }
                }
            }
            FileEdit fe = new FileEdit();
            fe.writeImageFile(path,gen,i,newImage);
        }
    }
    private void killImage(int index) {
        cullImage.remove(index);
    }
}
