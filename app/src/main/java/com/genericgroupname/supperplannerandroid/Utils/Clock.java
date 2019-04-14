package com.genericgroupname.supperplannerandroid.Utils;

public class Clock {
    private int min;
    private int sec;
    private long time;

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public String getTimmer(){
        String timeLeft;
        timeLeft = ""+ min+":";
        if(sec<10)
            timeLeft+="0";
        timeLeft+=sec;
        return timeLeft;
    }
}
