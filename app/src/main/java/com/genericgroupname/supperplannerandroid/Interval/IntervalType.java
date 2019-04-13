package com.genericgroupname.supperplannerandroid.Interval;

public enum IntervalType {
    BREAK("Break"),WORK("Work");

    private String intervalName;

    IntervalType(String intervalName) {
    }

    public String getIntervalName() {
        return intervalName;
    }
}
