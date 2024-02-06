package com.example.androidtest2app;

public class assignmentModel {
    String assignmentTitle, assignmentDueDate, assignmentClass;
    int progress;

    public assignmentModel(String assignmentTitle, String assignmentDueDate, String assignmentClass) {
        this.assignmentTitle = assignmentTitle;
        this.assignmentDueDate = assignmentDueDate;
        this.assignmentClass = assignmentClass;
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
