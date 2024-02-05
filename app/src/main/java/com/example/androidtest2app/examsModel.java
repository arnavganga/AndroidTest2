package com.example.androidtest2app;

public class examsModel {
    String examName;
    String examTime;
    String examLoc;
    String examDate;


    public examsModel(String examName, String examTime, String examLoc, String examDate) {
        this.examName = examName;
        this.examTime = examTime;
        this.examLoc = examLoc;
        this.examDate = examDate;
    }

    public String getExamName() {
        return examName;
    }

    public String getExamTime() {
        return examTime;
    }

    public String getExamLoc() {
        return examLoc;
    }

    public String getExamDate() {
        return examDate;
    }
}
