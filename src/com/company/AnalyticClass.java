package com.company;

import java.util.LinkedHashMap;

import java.util.Map;

public class AnalyticClass {
    private LinkedHashMap<Integer,UserData> dataSet = new LinkedHashMap<>();
    public int countWord(String line){
        int countWords = line.split(" ").length;
        System.out.println("Line Of Word Count: "+(countWords-1));
        return  countWords-1;
    }
}
