package com.genetic.mainlogic;

import com.genetic.entity.GenParam;
import com.genetic.panel.HomePanel;
import com.genetic.utility.ImageUtil;

public class GenerateImage {
    private final GenParam gparam;
    public GenerateImage(GenParam gparam) {
        this.gparam = gparam;
    }
    public void createFirstGeneration(int individual_max) {
        for(int i = 0; i < individual_max; i++) {
            for(int y = gparam.y();y < gparam.height(); y++) {
                for(int x = gparam.x();x < gparam.width(); x++) {
                    HomePanel.sharedGeneratedImages[i][y][x] = ImageUtil.randomRGB();
                }
            }
        }
    }
}
