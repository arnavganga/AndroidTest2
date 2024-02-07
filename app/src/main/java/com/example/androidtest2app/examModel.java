package com.example.androidtest2app;

import java.util.ArrayList;

public class examModel {
    public static ArrayList<examModel> examModels = new ArrayList<>();
    String examName;
    String examTime;
    String examLoc;
    String examDate;

    public examModel(String examName, String examDate, String examTime, String examLoc) {
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
