package com.genetic.utility;

import java.util.concurrent.ThreadLocalRandom;

public class ImageUtil {
    public static double euclidDistance(double x1, double y1, double x2, double y2) {
        double d = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        return d;
    }
    public static int colorDistance(int color1, int color2) {
        return color1 - color2;
    }
    public static int rand() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        return current.nextInt(0, 256); 
    }
    public static int r(int color){
        return color >> 16 & 0xff;
    }
    public static int g(int color){
        return color >> 8 & 0xff;
    }
    public static int b(int color){
        return color & 0xff;
    }
    public static int rgb(int r, int g, int b){
        return 0xff000000 | r << 16 | g << 8 | b;
    }
}
