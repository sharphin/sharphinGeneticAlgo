package com.genetic.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.genetic.utility.ImageUtil;

public class FileEdit {
    private static String makeGenDirectory(String directory,int gen) {
        StringBuilder sb = new StringBuilder();
        return sb.append(directory).append("\\").append(gen).toString();
    }

    private static File makeFilePath(String directory,int i) {
        StringBuilder sb = new StringBuilder();
        String filepath = sb.append(directory).append("\\").append("individual").append(i).append(".png").toString();
        return new File(filepath);
    }
    
    public int[][] readImageFile(String filepath) throws IOException{
        int imageRGB[][] = new int[50][50]; 
        File file = new File(filepath);	
        BufferedImage image = ImageIO.read(file);
        for(int y = 0;y < 50; y++){
            for(int x = 0;x < 50; x++) {
                imageRGB[y][x] = image.getRGB(x,y);
            }
        }
        return imageRGB;
    }
    public void writeImageFile(String path,int generation,int i) throws IOException {
        String directory = makeGenDirectory(path, generation);
        File dir = new File(directory);
        dir.mkdir();
        BufferedImage write = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        
        for(int y = 0;y < 50; y++){
            for(int x = 0;x < 50; x++){
                int r = ImageUtil.rand();
                int g = ImageUtil.rand();
                int b = ImageUtil.rand();
                int rgb = ImageUtil.rgb(r,g,b);
                write.setRGB(x,y,rgb);
            }
        }
        File file = makeFilePath(directory,i);
        ImageIO.write(write, "png", file);
    }
}
