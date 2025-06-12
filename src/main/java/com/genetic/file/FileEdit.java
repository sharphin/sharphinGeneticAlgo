package com.genetic.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.genetic.utility.ImageUtil;

public class FileEdit {
    private static String makeGenDirectory(String directory,int gen,int i) {
        StringBuilder sb = new StringBuilder();
        if(i == -1) return directory;
        return sb.append(directory).append("\\").append(gen).toString();
    }

    private static File makeFilePath(String directory,int i) {
        StringBuilder sb = new StringBuilder();
        if(i == -1) return new File(directory);
        directory = sb.append(directory).append("\\").append("individual").append(i).append(".png").toString();
        return new File(directory);
    }
    
    public int[][] readImageFile(String filepath,int gen,int i){
        int imageRGB[][] = new int[50][50]; 
        filepath = makeGenDirectory(filepath, gen,i);
        File file = makeFilePath(filepath,i);	
        try {
            BufferedImage image = ImageIO.read(file);
            for(int y = 0;y < 50; y++){
                for(int x = 0;x < 50; x++) {
                    imageRGB[y][x] = image.getRGB(x,y);
                }
            }
        } catch(IOException e) {
            System.out.println(e);
        }
        return imageRGB;
    }
    public void writeImageFile(String path,int generation,int i, int [][] test) {
        String directory = makeGenDirectory(path, generation,i);
        File dir = new File(directory);
        dir.mkdir();
        BufferedImage write = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        
        for(int y = 0;y < 50; y++){
            for(int x = 0;x < 50; x++){
                write.setRGB(x,y,test[y][x]);
            }
        }
        File file = makeFilePath(directory,i);
        try {
            ImageIO.write(write, "png", file);
        } catch (IOException e) {
            System.out.println("ddddddd");
        }
    }
    public void createFirstGeneration(String path,int individual_max) {
        for(int i = 0; i < individual_max;i++) {
            writeFirstImageFile(path,i);
        }
    }
    private void writeFirstImageFile(String path,int i) {
        String directory = makeGenDirectory(path, 1,i);
        File dir = new File(directory);
        dir.mkdir();
        BufferedImage write = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        setRGB(write);
        File file = makeFilePath(directory,i);
        try {
            ImageIO.write(write, "png", file);
        } catch (IOException e) {
            System.out.println("ddddddd");
        }
    }
    private BufferedImage setRGB(BufferedImage write) {
        for(int y = 0;y < 50; y++){
            for(int x = 0;x < 50; x++){
                write.setRGB(x,y,ImageUtil.randomRGB());
            }
        }
        return write;
    }
}
