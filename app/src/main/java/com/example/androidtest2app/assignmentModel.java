package com.example.androidtest2app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class assignmentModel{
    String assignmentTitle, assignmentDueDate, assignmentClass;
    int progress;

    public static ArrayList<assignmentModel> assignmentModels = new ArrayList<>();


    public assignmentModel(String assignmentTitle, String assignmentDueDate, String assignmentClass, int progress) {
        this.assignmentTitle = assignmentTitle;
        this.assignmentDueDate = assignmentDueDate;
        this.assignmentClass = assignmentClass;
        this.progress = progress;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public String getAssignmentDueDate() {
        return assignmentDueDate;
    }

    public String getAssignmentClass() {
        return assignmentClass;
    }

    public int getProgress() {
        return progress;
    }

}
