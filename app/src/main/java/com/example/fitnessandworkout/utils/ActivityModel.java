package com.example.fitnessandworkout.utils;

public class ActivityModel {
    Integer id;
    String date;
    String level;
    String activity;

    public ActivityModel(Integer id, String date, String level, String activity) {
        this.id = id;
        this.date = date;
        this.level = level;
        this.activity = activity;
    }

    public ActivityModel(String date, String level, String activity) {
        this.date = date;
        this.level = level;
        this.activity = activity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
