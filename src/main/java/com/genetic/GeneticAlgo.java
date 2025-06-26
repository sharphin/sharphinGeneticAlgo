package com.genetic;


import com.genetic.entity.AppParam;
import com.genetic.file.FileEdit;
import com.genetic.file.SettingFile;

public class GeneticAlgo {
    public static void main(String[] args) {
        SettingFile sf = new SettingFile();

        System.err.println("parameter check");
        AppParam param = sf.loadFilePath();
        if(param.paramCheck()) {
            System.err.println("OK");
            FileEdit genf = new FileEdit();
            int originImage[][] = genf.readImageFile(param.originfilepath());

            if(originImage.length != originImage[0].length) {
                AppParam.message.add("元画像は正方形にしてください。");
                errorMessage();
                return;
            }
            if(originImage.length >300) {
                AppParam.message.add("300×300px以内の画像を用意してください。");
                errorMessage();
                return;
            }
            new BaseFrame(param,originImage);
        } else {
            errorMessage();
        }
    }
    private static void errorMessage() {
        System.err.println("ERROR");
        for (String temp : AppParam.message) {
            System.err.println(temp);
        }
    }
}