package com.genetic.mainlogic;

import com.genetic.entity.AppParam;
import com.genetic.entity.GenParam;
import com.genetic.file.FileEdit;
import com.genetic.panel.HomePanel;

public class GenerateThread implements Runnable {
    private final AppParam aparam;
    private final GenParam gparam;
    private final int originImage[][];
    public GenerateThread(AppParam aparam,GenParam gparam, int originImage[][]) {
        this.originImage = originImage;
        this.aparam = aparam;
        this.gparam = gparam;
    }
    @Override
    public void run() {
        synchronized (this) {
            int individual_max = aparam.individual_max();
            FileEdit genf = new FileEdit();
            GenerateImage genImage = new GenerateImage(gparam);
            genImage.createFirstGeneration(individual_max);
            if(aparam.file_out()) genf.fileWriteNewGeneration(aparam.learnfilepath(),1,HomePanel.sharedGeneratedImages);
            for(int gen = 2; gen <= aparam.generation_max(); gen++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("割り込みが発生");
                }
                CullIndividual cullIndividual = new CullIndividual(aparam.mating_max(),gparam);
                cullIndividual.cull(originImage);
                cullIndividual.createNextGeneration(individual_max, aparam.mutation());
                if(aparam.file_out()) genf.fileWriteNewGeneration(aparam.learnfilepath(),gen,HomePanel.sharedGeneratedImages);
                HomePanel.now_gen++;
            }
            System.out.println("処理終了");
        }
    }
}
