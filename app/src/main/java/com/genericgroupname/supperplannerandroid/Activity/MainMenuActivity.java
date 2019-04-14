package com.genericgroupname.supperplannerandroid.Activity;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.genericgroupname.supperplannerandroid.Drink.CustomAdapter;
import com.genericgroupname.supperplannerandroid.Drink.Drink;
import com.genericgroupname.supperplannerandroid.Drink.DrinkManager;
import com.genericgroupname.supperplannerandroid.Exercise.Exercise;
import com.genericgroupname.supperplannerandroid.Exercise.ExerciseManager;
import com.genericgroupname.supperplannerandroid.Interval.IntervalType;
import com.genericgroupname.supperplannerandroid.R;
import com.genericgroupname.supperplannerandroid.Service.PlanService;
import com.genericgroupname.supperplannerandroid.User.ThemeName;
import com.genericgroupname.supperplannerandroid.User.User;
import com.genericgroupname.supperplannerandroid.Utils.JsonParser;

import org.json.JSONException;

import java.io.IOException;

public class MainMenuActivity extends AppCompatActivity {
    public static Boolean isRunning = false;
    public static DrinkManager drinkManager;
    private RadioButton plan, drinks, settings;
    private ConstraintLayout content;
    private CountDownTimer countDownTimer;
    private TextView timerView, intervalView, exerciseNameView, exerciseDescriptionView, exerciseLink;
    private ExerciseManager exerciseManager;
    private long time;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isRunning = true;
        drinkManager = new DrinkManager();
        setContentView(R.layout.activity_main_menu);
        if(WelcomeActivity.getUser.getThemeName()==ThemeName.LIGHT)
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        else
            getWindow().getDecorView().setBackgroundColor(Color.parseColor("#43434E"));
        content = findViewById(R.id.asdMenuView);
        plan = findViewById(R.id.togglePlan);
        drinks = findViewById(R.id.toggleDrinks);
        settings = findViewById(R.id.toggleSettings);
        content.removeAllViews();
        content.addView(View.inflate(getApplicationContext(), R.layout.plan_layout, null));
        Button btn = findViewById(R.id.start);
        Button stop = findViewById(R.id.stop);
        timerView = findViewById(R.id.timmerView);
        intervalView = findViewById(R.id.intervalTypeView);
        dialog = new Dialog(this);
        exerciseManager = new ExerciseManager();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PlanService.isRunning && isRunning) {
                    showInterval();
                    startTimmer();
                    addNotification();

                } else {
                    Intent serviceIntent = new Intent(getApplicationContext(), PlanService.class);
                    startService(serviceIntent);
                    System.out.println(PlanService.currentInterval);
                    content.removeAllViews();
                    content.addView(View.inflate(getApplicationContext(), R.layout.drink_layout, null));
                    drinks.setChecked(true);
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResume();
                stopTimer();
                Intent serviceIntent = new Intent(getApplicationContext(), PlanService.class);
                stopService(serviceIntent);
                stopTimer();
                if (WelcomeActivity.getUser.getNumberOfTrainingDay() < 6 && isRunning) {
                    dialog.setContentView(R.layout.rate_dialog);
                    dialog.show();
                    stopTimer();
                    isRunning = false;
                    Button rateBtn = dialog.findViewById(R.id.rateBtn);
                    rateBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                stopTimer();
                                getRate();
                                dialog.dismiss();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    if (isRunning) {
                        dialog.setContentView(R.layout.thanks_dialog);
                        dialog.show();
                    }
                }
            }
        });


        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content.removeAllViews();
                content.addView(View.inflate(getApplicationContext(), R.layout.plan_layout, null));
                Button btn = findViewById(R.id.start);
                timerView = findViewById(R.id.timmerView);
                intervalView = findViewById(R.id.intervalTypeView);
                Button stop = findViewById(R.id.stop);
                if (PlanService.isRunning && isRunning) {

                    startTimmer();
                    showInterval();

                }
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent serviceIntent = new Intent(getApplicationContext(), PlanService.class);
                        startService(serviceIntent);
                        content.removeAllViews();
                        content.addView(View.inflate(getApplicationContext(), R.layout.drink_layout, null));
                        drinks.setChecked(true);
                        Button addDrink = findViewById(R.id.addBtn);
                        Button showChart = findViewById(R.id.showChart);
                        final ListView listView = findViewById(R.id.listDrink);
                        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), drinkManager.getDri());
                        listView.setAdapter(customAdapter);
                        showChart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.setContentView(R.layout.chart_dialog);
                                dialog.show();
                                TextView woda = dialog.findViewById(R.id.woda);
                                TextView cukier = dialog.findViewById(R.id.cukier);
                                TextView kofeina = dialog.findViewById(R.id.kofeina);
                                TextView warning = dialog.findViewById(R.id.warning);
                                warning.setText("");
                                woda.setText("Water intake: " + drinkManager.getAmount().toString() + " / " + WelcomeActivity.getUser.getWaterAmount() + " ml");
                                cukier.setText("Sugar intake: " + drinkManager.getSugar().toString() + " / " + WelcomeActivity.getUser.getSugarMax() + " g");
                                kofeina.setText("Cafeine intake: " + drinkManager.getCofeine().toString() + " / " + WelcomeActivity.getUser.getCofeineMax() + " mg");
                                if (drinkManager.getAmount() < WelcomeActivity.getUser.getWaterAmount())
                                    warning.append("Drink more water " + System.getProperty("line.separator"));
                                if (drinkManager.getCofeine() > WelcomeActivity.getUser.getCofeineMax())
                                    warning.append("Avoid cafeine" + System.getProperty("line.separator"));
                                if (drinkManager.getSugar() > WelcomeActivity.getUser.getSugarMax())
                                    warning.append("Avoid sweetened drinks" + System.getProperty("line.separator"));
                                Button close = dialog.findViewById(R.id.closeChart);
                                close.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });

                            }
                        });
                        addDrink.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.setContentView(R.layout.add_dialog);
                                dialog.show();
                                addDrink();
                                content.removeAllViews();
                                content.addView(View.inflate(getApplicationContext(), R.layout.plan_layout, null));
                                plan.setChecked(true);
                            }
                        });

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Drink drink = drinkManager.getDri().get(position);
                                drinkManager.getDrunkDrunks().add(drink);
                            }
                        });
                    }
                });
                stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onResume();
                        stopTimer();
                        Intent serviceIntent = new Intent(getApplicationContext(), PlanService.class);
                        stopService(serviceIntent);
                        stopTimer();
                        if (WelcomeActivity.getUser.getNumberOfTrainingDay() < 6 && isRunning) {
                            dialog.setContentView(R.layout.rate_dialog);
                            dialog.show();
                            stopTimer();
                            isRunning = false;
                            Button rateBtn = dialog.findViewById(R.id.rateBtn);
                            rateBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        stopTimer();
                                        getRate();
                                        dialog.dismiss();
                                        dialog.setContentView(R.layout.thanks_dialog);
                                        dialog.show();
                                        Button closeBtn = dialog.findViewById(R.id.closeApp);
                                        closeBtn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                finish();
                                                System.exit(0);
                                            }
                                        });
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        } else {
                            if (isRunning) {
                                dialog.setContentView(R.layout.thanks_dialog);
                                dialog.show();
                                Button closeBtn = dialog.findViewById(R.id.closeApp);
                                closeBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        finish();
                                        System.exit(0);
                                    }
                                });
                            }
                        }
                    }
                });

            }
        });


        drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content.removeAllViews();
                content.addView(View.inflate(getApplicationContext(), R.layout.drink_layout, null));
                Button addDrink = findViewById(R.id.addBtn);
                Button showChart = findViewById(R.id.showChart);
                final ListView listView = findViewById(R.id.listDrink);
                CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), drinkManager.getDri());
                listView.setAdapter(customAdapter);
                showChart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.setContentView(R.layout.chart_dialog);
                        dialog.show();
                        TextView woda = dialog.findViewById(R.id.woda);
                        TextView cukier = dialog.findViewById(R.id.cukier);
                        TextView kofeina = dialog.findViewById(R.id.kofeina);
                        TextView warning = dialog.findViewById(R.id.warning);
                        warning.setText("");
                        woda.setText("Water intake: " + drinkManager.getAmount().toString() + " / " + WelcomeActivity.getUser.getWaterAmount() + " ml");
                        cukier.setText("Sugar intake: " + drinkManager.getSugar().toString() + " / " + WelcomeActivity.getUser.getSugarMax() + " g");
                        kofeina.setText("Cafeine intake: " + drinkManager.getCofeine().toString() + " / " + WelcomeActivity.getUser.getCofeineMax() + " mg");
                        if (drinkManager.getAmount() < WelcomeActivity.getUser.getWaterAmount())
                            warning.append("Drink more water " + System.getProperty("line.separator"));
                        if (drinkManager.getCofeine() > WelcomeActivity.getUser.getCofeineMax())
                            warning.append("Avoid cafeine" + System.getProperty("line.separator"));
                        if (drinkManager.getSugar() > WelcomeActivity.getUser.getSugarMax())
                            warning.append("Avoid sweetened drinks" + System.getProperty("line.separator"));
                        Button close = dialog.findViewById(R.id.closeChart);
                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                    }
                });

                addDrink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.setContentView(R.layout.add_dialog);
                        dialog.show();
                        addDrink();
                        content.removeAllViews();
                        content.addView(View.inflate(getApplicationContext(), R.layout.plan_layout, null));
                        plan.setChecked(true);
                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Drink drink = drinkManager.getDri().get(position);
                        drinkManager.getDrunkDrunks().add(drink);
                    }
                });

            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content.removeAllViews();
                content.addView(View.inflate(getApplicationContext(), R.layout.settings_layout, null));
                RadioButton lightTheme = findViewById(R.id.setlig);
                RadioButton darkTheme = findViewById(R.id.setdar);
                final JsonParser jsonParser = new JsonParser(getApplicationContext());
                if(WelcomeActivity.getUser.getThemeName()== ThemeName.LIGHT) {
                    lightTheme.setChecked(true);
                    darkTheme.setChecked(false);
                }
                else{
                    lightTheme.setChecked(false);
                    darkTheme.setChecked(true);
                }
                lightTheme.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WelcomeActivity.getUser.setThemeName(ThemeName.LIGHT);
                        try {
                            jsonParser.saveJson(WelcomeActivity.getUser);
                            if(WelcomeActivity.getUser.getThemeName()==ThemeName.LIGHT)
                                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                            else
                                getWindow().getDecorView().setBackgroundColor(Color.parseColor("#43434E"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                darkTheme.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WelcomeActivity.getUser.setThemeName(ThemeName.DARK);
                        try {
                            jsonParser.saveJson(WelcomeActivity.getUser);
                            if(WelcomeActivity.getUser.getThemeName()==ThemeName.LIGHT)
                                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                            else
                                getWindow().getDecorView().setBackgroundColor(Color.parseColor("#43434E"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    private void addDrink() {
        Button add = dialog.findViewById(R.id.addDrink);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenuActivity.this, "Drink added", Toast.LENGTH_SHORT).show();
                EditText name = dialog.findViewById(R.id.drinkName);
                EditText sugar = dialog.findViewById(R.id.sugarDrink);
                EditText cofeine = dialog.findViewById(R.id.drinkCofeine);
                EditText amount = dialog.findViewById(R.id.drinkAmount);
                addToTheList(name.getText().toString(), sugar.getText().toString(), cofeine.getText().toString(), amount.getText().toString());
            }
        });

    }

    private void addToTheList(String toString, String toString1, String toString2, String toString3) {
        drinkManager.getDri().add(new Drink(toString, Double.parseDouble(toString1), Double.parseDouble(toString2), Double.parseDouble(toString3)));
        dialog.dismiss();
    }

    private void getRate() throws IOException, JSONException {
        stopTimer();
        RatingBar prodRating = dialog.findViewById(R.id.prodRating);
        RatingBar psyRating = dialog.findViewById(R.id.psychRating);
        RatingBar menRating = dialog.findViewById(R.id.menRating);
        RatingBar overRating = dialog.findViewById(R.id.satRating);
        float rate = (prodRating.getRating() + psyRating.getRating() + menRating.getRating() + overRating.getRating()) / 4;
        User user = WelcomeActivity.getUser;
        JsonParser jsonParser = new JsonParser(getApplicationContext());
        if (user.getNumberOfTrainingDay() == 1) {
            user.setFirstDayGrade((double) rate);
            jsonParser.saveJson(user);
        }
        if (user.getNumberOfTrainingDay() == 2) {
            user.setSecondDayGrade((double) rate);
            jsonParser.saveJson(user);
        }
        if (user.getNumberOfTrainingDay() == 3) {
            user.setThirdDayGrade((double) rate);
            jsonParser.saveJson(user);
        }
        if (user.getNumberOfTrainingDay() == 4) {
            user.setFourthDayGrade((double) rate);
            jsonParser.saveJson(user);
        }
        if (user.getNumberOfTrainingDay() == 5) {
            user.setFifthDayGrade((double) rate);
            jsonParser.saveJson(user);
        }

    }

    private void showInterval() {
        System.out.println("interval" + PlanService.currentInterval.getInterval());
        if (PlanService.currentInterval.getInterval() == IntervalType.WORK) {
            intervalView.setText("work");
        } else {
            intervalView.setText("break");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;
        if (PlanService.isRunning) {
            startTimmer();
            showInterval();
        }
    }

    public void startTimmer() {
        time = PlanService.timmer.getTime();
        countDownTimer = new CountDownTimer(PlanService.timmer.getTime(), 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                startTimmer();
                showDialog();
            }
        }.start();
    }

    public void stopTimer() {
        countDownTimer.cancel();
    }

    private void updateTimer() {
        int min = (int) time / 60000;
        int sec = (int) time % 60000 / 1000;

        String timeLeft;
        timeLeft = min + ":";
        if (sec < 10) timeLeft += "0";
        timeLeft += sec;

        timerView.setText(timeLeft);
    }

    private void showDialog() {
        if (PlanService.currentInterval.getInterval() == IntervalType.WORK) {
            intervalView.setText("work");
            if (isRunning) {
                dialog.setContentView(R.layout.work_dialog);
                dialog.show();
                Button okBtn = dialog.findViewById(R.id.workOk);
                okBtn.setOnClickListener(dismissDialog);
            }
        } else {
            intervalView.setText("break");
            if (isRunning) {
                dialog.setContentView(R.layout.break_dialog);
                dialog.show();
                Button closeBtn = dialog.findViewById(R.id.breakClose);
                Button okBtn = dialog.findViewById(R.id.breakOk);
                closeBtn.setOnClickListener(dismissDialog);

                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        dialog.setContentView(R.layout.exercise_dialog);
                        dialog.show();
                        Button doneBtn = dialog.findViewById(R.id.exerciseDone);
                        doneBtn.setOnClickListener(dismissDialog);
                        if (exerciseManager == null)
                            exerciseManager = new ExerciseManager();
                        Exercise exercise = exerciseManager.getRandomExercise(WelcomeActivity.getUser);
                        showExercise(exercise);
                    }
                });

            }
        }
    }

    private void showExercise(Exercise exercise) {
        exerciseNameView = dialog.findViewById(R.id.exerciseName);
        exerciseDescriptionView = dialog.findViewById(R.id.description);
        exerciseLink = dialog.findViewById(R.id.exerciseVideoLink);
        exerciseNameView.setText(exercise.getName());
        exerciseDescriptionView.setText(exercise.getDescription());
        exerciseLink.setText(exercise.getMedia());
    }

    public View.OnClickListener dismissDialog = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;
    }

    private void addNotification() {
//        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel("123","asd",NotificationManager.IMPORTANCE_HIGH);
//            notificationManager.createNotificationChannel(channel);
//        }
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "123")
//                .setSmallIcon(R.drawable.ic_launcher_foreground)//R.mipmap.ic_launcher
//                .setContentTitle("halo")
//                .setContentText("kurwa")
//                .setVibrate(new long[]{100, 250})
//                .setLights(Color.YELLOW, 500, 5000)
//                .setAutoCancel(true)
//                .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
//
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
//        stackBuilder.addNextIntent(new Intent(getApplicationContext(), MainMenuActivity.class));
//        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
//        mBuilder.setContentIntent(resultPendingIntent);
//
//        notificationManager.notify(123, mBuilder.build());

    }
}

