package com.genetic.entity;

public record AppParam(String readfilepath,
                       String writefilepath,
                       int nodeCount,
                       double mutation) {
    
}
