package com.example.androidtest2app;

import java.util.ArrayList;

public class toDoListModel{
    String eventName, eventDate;
    public static ArrayList<toDoListModel> toDoListModels = new ArrayList<>();

    public toDoListModel(String eventName, String eventDate) {
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }
}
