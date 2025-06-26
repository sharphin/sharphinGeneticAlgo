package com.genetic.mainlogic;

import com.genetic.entity.GenParam;

public class AreaSplit {
    private final int split_num;
    private final int width,height;
    public AreaSplit(int split_num,int width,int height) {
        this.split_num = split_num;
        this.width = width;
        this.height = height;
    }
    public GenParam[] splitResponsibleArea() {
        GenParam params[] = {new GenParam(0,0,width,height)};
        if(split_num < 2) return params;
        int horiSplit = (int)Math.sqrt(split_num);
        int vertSplit = split_num/horiSplit;
        params = new GenParam[split_num];
        int xBounds = width/horiSplit;
        int yBounds = height/vertSplit;

        for (int i = 0; i < vertSplit; i++) {
            for (int j = 0; j < horiSplit;j++) {
                int x = j*xBounds;
                int y = i*yBounds;
                int ii = i*horiSplit;
                params[ii+j] = new GenParam(x,y,x+(width/horiSplit),y+(height/vertSplit));
            }
        }
        return params;
    }
}
