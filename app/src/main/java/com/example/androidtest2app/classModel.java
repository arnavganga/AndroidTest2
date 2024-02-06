package com.example.androidtest2app;

public class classModel {
    String className;
    String classTime;
    String classProf;


    public classModel(String className, String classTime, String classProf) {
        this.className = className;
        this.classTime = classTime;
        this.classProf = classProf;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getClassProf() {
        return classProf;
    }

    public void setClassProf(String classProf) {
        this.classProf = classProf;
    }
}
