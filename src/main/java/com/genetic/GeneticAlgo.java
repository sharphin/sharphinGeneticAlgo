package com.genetic;


import com.genetic.entity.AppParam;
import com.genetic.file.FileEdit;
import com.genetic.file.SettingFile;
import com.genetic.mainlogic.CullIndividual;

public class GeneticAlgo {
    public static void main(String[] args) {
        SettingFile sf = new SettingFile();
        AppParam param = sf.loadFilePath();
        FileEdit genf = new FileEdit();
        for (int i = 1; i <= param.individual_max(); i++) {
            genf.writeFirstImageFile(param.writefilepath(),1,i);
        }
        for(int ii = 1; ii < 500; ii++) {
            CullIndividual cullIndividual = new CullIndividual();
            int originImage[][] = genf.readImageFile(param.readfilepath(),-1,-1);
            for (int i = 1; i < param.individual_max(); i++) {
                cullIndividual.addImage(genf.readImageFile(param.writefilepath(),ii,i));
            }
            cullIndividual.cull(param.individual_max(), originImage);
            cullIndividual.createNextGeneration(param.writefilepath(),ii+1,param.individual_max(),param.mutation());
        }
    }
}