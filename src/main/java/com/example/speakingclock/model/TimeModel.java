package com.example.speakingclock.model;

public class TimeModel {
    
    private int hour;
    private int minute;

    public TimeModel(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
    
}
