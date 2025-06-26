package com.genetic.entity;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public record AppParam(String originfilepath,
                       String learnfilepath,
                       int individual_max,
                       int generation_max,
                       int mating_max,
                       boolean file_out,
                       double mutation,
                       int thread_max) {
    public static List<String> message = new ArrayList<>();
    
    public boolean paramCheck() {
        boolean checkclear = true;
        if(individual_max > 0 && ((individual_max & (individual_max - 1)) != 0)) {
            message.add("最大個体数は2のN乗を設定してください。");
            checkclear = false;
        }
        if(mating_max > 0 && ((mating_max & (mating_max - 1)) != 0)) {
            message.add("交配する個体数は2のN乗を設定してください。");
            checkclear = false;
        }
        if(thread_max > 1 && (thread_max & 1) == 1) {
            message.add("マルチスレッドの場合は偶数を設定してください。");
            checkclear = false;
        }
        if(!Files.exists(Path.of(originfilepath))) {
            message.add("指定されたファイルが見つかりません。");
            checkclear = false;
        }
        if(!file_out) return checkclear;
        if(!Files.exists(Path.of(learnfilepath))) {
            message.add("指定されたフォルダが見つかりません。");
            checkclear = false;
        } else if(!Files.isDirectory(Path.of(learnfilepath))) {
            message.add("出力先にファイル名は指定できません。");
            checkclear = false;
        }
        return checkclear;
    }
}
