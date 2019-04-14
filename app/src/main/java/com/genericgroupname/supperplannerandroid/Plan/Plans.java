package com.genericgroupname.supperplannerandroid.Plan;

import com.genericgroupname.supperplannerandroid.Interval.Interval;
import com.genericgroupname.supperplannerandroid.Interval.IntervalType;

public enum Plans {
    FIRST("first",new Interval[] {new Interval(IntervalType.WORK,1),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,25),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,25),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,25),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,25),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,25),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,25),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,25),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,25),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,25),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,25),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,25),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,25),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,25),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,25),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,25),new Interval(IntervalType.BREAK,5)}),
    SECOND("second",new Interval[]{new Interval(IntervalType.WORK,50),new Interval(IntervalType.BREAK,10),new Interval(IntervalType.WORK,50),new Interval(IntervalType.BREAK,10),new Interval(IntervalType.WORK,50),new Interval(IntervalType.BREAK,10),new Interval(IntervalType.WORK,50),new Interval(IntervalType.BREAK,10),new Interval(IntervalType.WORK,50),new Interval(IntervalType.BREAK,10),new Interval(IntervalType.WORK,50),new Interval(IntervalType.BREAK,10),new Interval(IntervalType.WORK,50),new Interval(IntervalType.BREAK,10),new Interval(IntervalType.WORK,50),new Interval(IntervalType.BREAK,10),new Interval(IntervalType.WORK,50),new Interval(IntervalType.BREAK,10)}),
    THIRD("third",new Interval[]{new Interval(IntervalType.WORK,45),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,45),new Interval(IntervalType.BREAK,15),new Interval(IntervalType.WORK,45),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,45),new Interval(IntervalType.BREAK,15),new Interval(IntervalType.WORK,45),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,45),new Interval(IntervalType.BREAK,15),new Interval(IntervalType.WORK,45),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,45),new Interval(IntervalType.BREAK,15),new Interval(IntervalType.WORK,45),new Interval(IntervalType.BREAK,5),new Interval(IntervalType.WORK,45),new Interval(IntervalType.BREAK,15),}),
    FOURTH("fourth",new Interval[]{new Interval(IntervalType.WORK,90),new Interval(IntervalType.BREAK,20),new Interval(IntervalType.WORK,45),new Interval(IntervalType.BREAK,10),new Interval(IntervalType.WORK,90),new Interval(IntervalType.BREAK,20),new Interval(IntervalType.WORK,45),new Interval(IntervalType.BREAK,10),new Interval(IntervalType.WORK,90),new Interval(IntervalType.BREAK,20),new Interval(IntervalType.WORK,45),new Interval(IntervalType.BREAK,10),new Interval(IntervalType.WORK,90),new Interval(IntervalType.BREAK,20),new Interval(IntervalType.WORK,45),new Interval(IntervalType.BREAK,10),new Interval(IntervalType.WORK,90),new Interval(IntervalType.BREAK,20),new Interval(IntervalType.WORK,45),new Interval(IntervalType.BREAK,10),new Interval(IntervalType.WORK,90),new Interval(IntervalType.BREAK,20),new Interval(IntervalType.WORK,45),new Interval(IntervalType.BREAK,10),}),
    FIFTH("fifth",new Interval[]{new Interval(IntervalType.WORK,210),new Interval(IntervalType.BREAK,30),new Interval(IntervalType.WORK,210),new Interval(IntervalType.BREAK,30),new Interval(IntervalType.WORK,210),new Interval(IntervalType.BREAK,30),new Interval(IntervalType.WORK,210),new Interval(IntervalType.BREAK,30)});
    private String name;
    private Interval[] intervals;

    Plans(String name, Interval[] intervals) {
        this.name = name;
        this.intervals = intervals;
    }

    public String getName() {
        return name;
    }

    public Interval[] getIntervals() {
        return intervals;
    }
}
