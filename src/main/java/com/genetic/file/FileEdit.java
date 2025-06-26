package com.genetic.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileEdit {

    private static File makeFilePath(String directory) {
        StringBuilder sb = new StringBuilder();
        directory = sb.append(directory).append("\\").append("result").append(".png").toString();
        return new File(directory);
    }
    
    public int[][] readImageFile(String filepath){
        File file = new File(filepath);
        try {
            BufferedImage image = ImageIO.read(file);
            int imageRGB[][] = new int[image.getWidth()][image.getHeight()];
            for(int y = 0;y < image.getHeight(); y++){
                for(int x = 0;x < image.getWidth(); x++) {
                    imageRGB[y][x] = image.getRGB(x,y);
                }
            }
            return imageRGB;
        } catch(IOException e) {
            System.out.println(e);
            return null;
        }
    }
    public void fileWriteImage(String path,int image[][]) {
        File dir = new File(path);
        dir.mkdir();
        BufferedImage write = new BufferedImage(image[0].length, image.length, BufferedImage.TYPE_INT_RGB);
        setRGB(write,image);
        File file = makeFilePath(path);
        try {
            ImageIO.write(write, "png", file);
        } catch (IOException e) {
            System.out.println("ddddddd");
        }
    }
    private BufferedImage setRGB(BufferedImage write, int[][] image) {
        for(int y = 0;y < image.length; y++){
            for(int x = 0;x < image[y].length; x++){
                write.setRGB(x,y,image[y][x]);
            }
        }
        return write;
    }
}
