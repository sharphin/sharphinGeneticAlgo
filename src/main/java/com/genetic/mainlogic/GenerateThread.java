package com.genetic.mainlogic;

import com.genetic.entity.AppParam;
import com.genetic.entity.GenParam;
import com.genetic.panel.HomePanel;

public class GenerateThread implements Runnable {
    private final AppParam aparam;
    private final GenParam gparam;
    private final int originImage[][];
    private final int thread_num;
    public GenerateThread(AppParam aparam,GenParam gparam, int originImage[][],int thread_num) {
        this.originImage = originImage;
        this.aparam = aparam;
        this.gparam = gparam;
        this.thread_num = thread_num;
    }
    @Override
    public void run() {
        synchronized (this) {
            int individual_max = aparam.individual_max();
            GenerateImage genImage = new GenerateImage(gparam);
            genImage.createFirstGeneration(individual_max);
            for(int gen = 2; gen <= aparam.generation_max(); gen++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("割り込みが発生");
                }
                CullIndividual cullIndividual = new CullIndividual(aparam.mating_max(),gparam);
                cullIndividual.cull(originImage);
                cullIndividual.createNextGeneration(individual_max, aparam.mutation());
                if(thread_num == 0) HomePanel.now_gen = gen;
            }
            System.out.println("処理終了");
        }
    }
}
