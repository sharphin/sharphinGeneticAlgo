package com.genetic;

import com.genetic.entity.AppParam;
import com.genetic.file.FileEdit;
import com.genetic.file.SettingFile;

public class GeneticAlgo {
    public static void main(String[] args) {
        SettingFile sf = new SettingFile();
        AppParam param = sf.loadFilePath();
        FileEdit genf = new FileEdit();
        //genf.readImageFile();
        genf.writeImageFile(param.writefilepath(),1,16);
    }
}