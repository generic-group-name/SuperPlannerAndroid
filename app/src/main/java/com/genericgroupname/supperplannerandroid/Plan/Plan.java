package com.genericgroupname.supperplannerandroid.Plan;

import com.genericgroupname.supperplannerandroid.Interval.Interval;

import java.util.ArrayList;

public class Plan {
    private String name;
    private ArrayList<Interval> intervals;

    public Plan(String name, ArrayList<Interval> intervals) {
        this.name = name;
        this.intervals = intervals;
    }
}
