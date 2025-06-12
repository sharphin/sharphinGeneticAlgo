package com.genetic.utility;

import java.util.concurrent.ThreadLocalRandom;

public class ImageUtil {
    private static final double MAX = Math.sqrt(3);
    public static double euclidDistance(int origincolor,int color2) {
        double rd = r(origincolor) - r(color2);
        double gd = g(origincolor) - g(color2);
        double bd = b(origincolor) - b(color2);

        return Math.sqrt(rd * rd + gd * gd + bd * bd) / MAX;
    }
    public static int randomRGB() {
        return ImageUtil.rgb(rand(),rand(),rand());
    }
    public static int colorDistance(int color1, int color2) {
        return color1 - color2;
    }
    public static int rand() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        return current.nextInt(0, 256); 
    }
    public static int r(int color){
        return color >> 16 & 0b11111111;
    }
    public static int g(int color){
        return color >> 8 & 0b11111111;
    }
    public static int b(int color){
        return color & 0b11111111;
    }
    public static int rgb(int r, int g, int b){
        return r << 16 | g << 8 | b;
    }
}
