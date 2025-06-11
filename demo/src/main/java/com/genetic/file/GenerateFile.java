package com.genetic.file;

import java.io.FileOutputStream;

public class GenerateFile {
    public void writeImageFile() {
        byte byteVal[] = { -119,80,78,71,13,10,26,10,0,0,0,13,73,72,68,82,0,0,0,2,0,0,0,1,8,2,0,0,0,123,64,-24,-35,0,0,0,1,115,82,71,66,0,-82,-50,28,-23,0,0,0,4,103,65,77,65,0,0,-79,-113,11,
            -4,97,5,0,0,0,9,112,72,89,115,0,0,14,-61,0,0,14,-61,1,-57,111,-88,100,0,0,0,15,73,68,65,84,
            24,87,9,-4,-1,-1,63,3,3,3,0,14,-1,2,-1,119,9,-8,-124,0,0,0,0,73,69,78,68,-82,66,96,-126 };
        try {
            FileOutputStream fileOutoutStream = new FileOutputStream("./image/generateImage"); 
            fileOutoutStream.write(byteVal);
            fileOutoutStream.close();
        } catch (Exception e) {
            System.out.println("エラー");
            e.printStackTrace();
        }
    }
}
