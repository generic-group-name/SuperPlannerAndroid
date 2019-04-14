package com.genericgroupname.supperplannerandroid.Interval;

public class Interval {
    private IntervalType interval;
    private Integer duration; //duration in min

    public Interval() {
    }

    public Interval(IntervalType interval, Integer duration) {
        this.interval = interval;
        this.duration = duration;
    }

    public IntervalType getInterval() {
        return interval;
    }

    public Integer getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "interval=" + interval +
                ", duration=" + duration +
                '}';
    }
}
