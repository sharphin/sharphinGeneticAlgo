package com.genetic.entity;

public record AppParam(String originfilepath,
                       String learnfilepath,
                       int individual_max,
                       int generation_max,
                       int survived_individual,
                       boolean file_out,
                       int mutation) {
    
}
