package com.genetic.mainlogic;

public class GenIndividual {
    private byte images[][][];
    public GenIndividual(int individual_max) {
        images = new byte[individual_max][50][50];
    }
}
