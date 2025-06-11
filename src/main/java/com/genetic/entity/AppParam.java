package com.genetic.entity;

public record AppParam(String readfilepath,
                       String writefilepath,
                       int individual_max,
                       double mutation) {
    
}
