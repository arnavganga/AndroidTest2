package com.example.androidtest2app;

import java.util.ArrayList;

public class classModel {
    String className;
    String classTime;
    String classProf;

    public static ArrayList<classModel> classModels = new ArrayList<>();

    public classModel(String className, String classTime, String classProf) {
        this.className = className;
        this.classTime = classTime;
        this.classProf = classProf;
    }

    public String getClassName() {
        return className;
    }

    public String getClassTime() {
        return classTime;
    }


    public String getClassProf() {
        return classProf;
    }

}
