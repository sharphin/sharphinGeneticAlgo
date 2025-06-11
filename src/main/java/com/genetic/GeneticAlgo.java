package com.genetic;

import java.io.IOException;

import com.genetic.entity.AppParam;
import com.genetic.file.FileEdit;
import com.genetic.file.SettingFile;

public class GeneticAlgo {
    public static void main(String[] args) {
        SettingFile sf = new SettingFile();
        AppParam param = sf.loadFilePath();
        FileEdit genf = new FileEdit();
        try {
            //genf.readImageFile(param.readfilepath());
            for (int i = 1; i <= 16; i++) {
                genf.writeImageFile(param.writefilepath(),2,i);
            }
        } catch (IOException e) {
        }
    }
}