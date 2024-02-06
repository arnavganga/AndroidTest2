package com.example.androidtest2app;

import java.util.ArrayList;

public class toDoListModel{
    String toDoListName;
    public static ArrayList<toDoListModel> toDoListModels = new ArrayList<>();
    
    public toDoListModel(String toDoListName) {
        this.toDoListName = toDoListName;
    }

    public String getToDoListName() {
        return toDoListName;
    }
}
