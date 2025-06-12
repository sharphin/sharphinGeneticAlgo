package com.genetic.mainlogic;

import com.genetic.utility.ImageUtil;

public class GenerateImage {
    public int[][][] createFirstGeneration(int individual_max) {
        int temp[][][] = new int [individual_max][50][50];
        for(int i = 0; i < individual_max; i++) {
            setRandRGB(temp[i]);
        }
        return temp;
    }
    private int[][] setRandRGB(int array2d[][]) {
        for(int y = 0;y < 50; y++){
            for(int x = 0;x < 50; x++){
                array2d[y][x] = ImageUtil.randomRGB();
            }
        }
        return array2d;
    }
}
