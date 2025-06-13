package com.genetic.mainlogic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import com.genetic.entity.GenParam;
import com.genetic.panel.HomePanel;
import com.genetic.utility.ImageUtil;

public class CullIndividual {
    //一世代の最大個体数（2のN乗）の画像の色データをLinkedlistに格納し
    //一対一でそれぞれ戦わせ、負けたほうを間引いていく。
    //方法は元の画像と一ピクセルごとにRGB値を比較していきその色差の合計が少ないほうが勝ち。
    //勝ち残った画像から次の世代の個体を生成する
    private LinkedList<int[][]> cullImage;
    private final int survived_individual;
    private final GenParam gparam;
    public CullIndividual(int survived_individual,GenParam gparams) {
        cullImage = new LinkedList<>(Arrays.asList(HomePanel.sharedGeneratedImages));
        this.survived_individual = survived_individual;
        this.gparam = gparams;
    }
    //無数ある画像から優秀な画像をのぞき、間引き
    public void cull(int originimage[][]) {
        for(int i = 0;i < cullImage.size()-1; i+=2){
            double diff1 = 0.0;
            double diff2 = 0.0;
            if (cullImage.size() <= survived_individual) return;
            for(int y = gparam.y();y < gparam.height(); y++) {
                for(int x = gparam.x();x < gparam.width(); x++) {
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
    public void createNextGeneration(int individual_max, double mutation) {
        for(int i = 0; i < individual_max;i++) {
            for(int y = gparam.y();y < gparam.height(); y++) {
                for(int x = gparam.x();x < gparam.width(); x++) {
                    ThreadLocalRandom current = ThreadLocalRandom.current();
                    int rand = current.nextInt(0, (int)(100.0/mutation)); 
                    if(mutation > 0.0 && rand == 0) {
                        HomePanel.sharedGeneratedImages[i][y][x] = ImageUtil.randomRGB();
                    } else {
                        HomePanel.sharedGeneratedImages[i][y][x] = cullImage.get(current.nextInt(0, survived_individual))[y][x];
                    }
                }
            }
        }
    }
}
