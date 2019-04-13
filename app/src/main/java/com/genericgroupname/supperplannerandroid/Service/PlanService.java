package com.genericgroupname.supperplannerandroid.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;

import android.os.CountDownTimer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

import com.genericgroupname.supperplannerandroid.Activity.WelcomeActivity;
import com.genericgroupname.supperplannerandroid.Interval.Interval;
import com.genericgroupname.supperplannerandroid.Plan.Plan;
import com.genericgroupname.supperplannerandroid.Plan.Plans;
import com.genericgroupname.supperplannerandroid.User.User;
import com.genericgroupname.supperplannerandroid.Utils.Clock;
import com.genericgroupname.supperplannerandroid.Utils.JsonParser;

import org.json.JSONException;

import java.io.IOException;

public class PlanService extends Service {
    public static Interval currentInterval;
    public static Boolean isRunning =false;
    private long time;
    public static Clock timmer;
    CountDownTimer countDownTimer;
    private MediaPlayer player;
    @Override
    public void onCreate() {
        super.onCreate();
        timmer = new Clock();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isRunning = true;
        User user = WelcomeActivity.getUser;
        Plans plan = Plans.FIRST;
        switch(user.getNumberOfTrainingDay()){
            case 1 :
                plan = Plans.FIFTH;
                break;
            case 2:
                plan = Plans.SECOND;
                break;
            case 3:
                plan = Plans.THIRD;
                break;
            case 4:
                plan = Plans.FOURTH;
                break;
            case 5:
                plan = Plans.FIFTH;
                break;

        }
        for (Interval interval:plan.getIntervals()) {
            currentInterval = interval;
             time = interval.getDuration() * 60000;
             countDownTimer = new CountDownTimer(time,1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    time = millisUntilFinished;
                    updateTimer();
                }

                @Override
                public void onFinish() {

                }
            }.start();

        }
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
        User user = WelcomeActivity.getUser;
        user.setNumberOfTrainingDay(user.getNumberOfTrainingDay()+1);
        JsonParser jsonParser= new JsonParser(getApplicationContext());
        try {
            jsonParser.saveJson(user);
        } catch (JSONException |IOException e) {
            e.printStackTrace();
        }
        countDownTimer.cancel();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void updateTimer() {
        int minutes = (int) time/60000;
        int sec = (int) time %60000/1000;
        timmer.setMin(minutes);
        timmer.setSec(sec);
        System.out.println(timmer.getTime());

    }
}
