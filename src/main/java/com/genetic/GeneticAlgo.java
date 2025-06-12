package com.genetic;


import com.genetic.entity.AppParam;
import com.genetic.file.FileEdit;
import com.genetic.file.SettingFile;
import com.genetic.mainlogic.CullIndividual;

public class GeneticAlgo {
    public static void main(String[] args) {
        SettingFile sf = new SettingFile();

        AppParam param = sf.loadFilePath();
        //geneti(param);
        new BaseFrame(param);
    }
    static void geneti(AppParam param) {

        String originFilePath = param.originfilepath();
        String learnFilePath = param.learnfilepath();
        int individual_max = param.individual_max();
        int generation_max = param.generation_max();
        int survived_individual = param.survived_individual();

        FileEdit genf = new FileEdit();
        int originImage[][] = genf.readImageFile(originFilePath,-1,-1);
        genf.createFirstGeneration(learnFilePath,individual_max);

        for(int gen = 1; gen < generation_max; gen++) {
            CullIndividual cullIndividual = new CullIndividual(survived_individual);
            for (int i = 1; i < individual_max; i++) {
                cullIndividual.addImage(genf.readImageFile(learnFilePath,gen,i));
            }
            cullIndividual.cull(originImage);
            for(int i = 0; i < individual_max; i++) {
                int newImage [][] = cullIndividual.createNextGeneration(param.survived_individual(), param.mutation());
                genf.writeImageFile(learnFilePath,gen+1,i,newImage);
            }
        }
    }
}