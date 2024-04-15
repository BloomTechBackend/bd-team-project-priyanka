package com.healthmate.service.dynamodb.models;

import java.time.LocalTime;

public class TimeRange {
    private String startTime;
    private String endTime;
    public TimeRange(){}

    public TimeRange(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public LocalTime getLocalStartTime() {
        return LocalTime.parse(startTime);
    }
    public LocalTime getLocalEndTime() {
        return LocalTime.parse(endTime);
    }

    public boolean isTimeWithinRange(Integer secondsOfDay) {
        return getLocalEndTime().toSecondOfDay() >= secondsOfDay && getLocalStartTime().toSecondOfDay() <= secondsOfDay;
    }

}
