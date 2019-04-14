package com.genericgroupname.supperplannerandroid.Service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.audiofx.Equalizer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.genericgroupname.supperplannerandroid.Activity.WelcomeActivity;
import com.genericgroupname.supperplannerandroid.Interval.Interval;
import com.genericgroupname.supperplannerandroid.Plan.Plan;
import com.genericgroupname.supperplannerandroid.Plan.Plans;
import com.genericgroupname.supperplannerandroid.R;
import com.genericgroupname.supperplannerandroid.User.User;
import com.genericgroupname.supperplannerandroid.Utils.Clock;
import com.genericgroupname.supperplannerandroid.Utils.JsonParser;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class PlanService extends Service {
    public static Interval currentInterval;
    public static Boolean isRunning = false;
    private long time;
    private int i;
    public static Clock timmer;
    private Plans plan;
    CountDownTimer countDownTimer;
    private Ringtone ringtone;

    @Override
    public void onCreate() {
        super.onCreate();
        timmer = new Clock();
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isRunning = true;
        User user = WelcomeActivity.getUser;

        plan = Plans.FIRST;
        switch (user.getNumberOfTrainingDay()) {
            case 1:
                plan = Plans.FIRST;
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
            default:
                plan = chooseBest(user);
                break;
        }
        i = 0;

        currentInterval = plan.getIntervals()[i];

        time = currentInterval.getDuration() * 6000;
        startTimer();


        return START_STICKY;

    }

    private Plans chooseBest(User user) {
        ArrayList<Double> wart = new ArrayList<>();
        wart.add(user.getFirstDayGrade());
        wart.add(user.getSecondDayGrade());
        wart.add(user.getThirdDayGrade());
        wart.add(user.getFourthDayGrade());
        wart.add(user.getFifthDayGrade());
        double max = Collections.max(wart);
        if(user.getFirstDayGrade() == max)
            return Plans.FIRST;
        else if(user.getSecondDayGrade() == max)
            return Plans.SECOND;
        else if(user.getThirdDayGrade() == max)
            return Plans.THIRD;
        else if(user.getFourthDayGrade() == max)
            return Plans.FOURTH;
        else
            return Plans.FIFTH;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
        User user = WelcomeActivity.getUser;
        //user.setNumberOfTrainingDay(user.getNumberOfTrainingDay() + 1);
        JsonParser jsonParser = new JsonParser(getApplicationContext());
        try {
            jsonParser.saveJson(user);
        } catch (JSONException | IOException e) {
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
        timmer.setTime(time);
        int minutes = (int) time / 60000;
        int sec = (int) time % 60000 / 1000;
        timmer.setMin(minutes);
        timmer.setSec(sec);

    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(time, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                ringtone.play();
                addNotification();
                i++;
                currentInterval = plan.getIntervals()[i];
                System.out.println("halko" + currentInterval);
                time = currentInterval.getDuration() * 600;
                startTimer();
            }
        }.start();

    }
    private void addNotification() {
//        // Builds your notification
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher_round)
//                .setContentTitle("halko")
//                .setContentText("kurwa dzialam xd");
//
//        // Creates the intent needed to show the notification
//        Intent notificationIntent = new Intent(this, PlanService.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(contentIntent);
//
//        // Add as notification
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(0, builder.build());
    }
}
