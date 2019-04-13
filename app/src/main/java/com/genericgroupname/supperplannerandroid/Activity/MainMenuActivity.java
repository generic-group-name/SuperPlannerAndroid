package com.genericgroupname.supperplannerandroid.Activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.genericgroupname.supperplannerandroid.R;
import com.genericgroupname.supperplannerandroid.Service.PlanService;

public class MainMenuActivity extends AppCompatActivity {
    private RadioButton plan, drinks, settings;
    private ConstraintLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        content = findViewById(R.id.asdMenuView);
        plan = findViewById(R.id.togglePlan);
        drinks = findViewById(R.id.toggleDrinks);
        settings = findViewById(R.id.toggleSettings);
        content.removeAllViews();
        content.addView(View.inflate(getApplicationContext(), R.layout.plan_layout, null));
        Button btn = findViewById(R.id.start);
        Button stop = findViewById(R.id.stop);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PlanService.isRunning)
                    Toast.makeText(MainMenuActivity.this, "dziala", Toast.LENGTH_SHORT).show();
                else {
                    Intent serviceIntent = new Intent(getApplicationContext(), PlanService.class);
                    startService(serviceIntent);
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(getApplicationContext(), PlanService.class);
                stopService(serviceIntent);
            }
        });


        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content.removeAllViews();
                content.addView(View.inflate(getApplicationContext(), R.layout.plan_layout, null));
                Button btn = findViewById(R.id.start);
                Button stop = findViewById(R.id.stop);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent serviceIntent = new Intent(getApplicationContext(), PlanService.class);
                        startService(serviceIntent);
                    }
                });
                stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent serviceIntent = new Intent(getApplicationContext(), PlanService.class);
                        stopService(serviceIntent);
                    }
                });

            }
        });


        drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content.removeAllViews();
                content.addView(View.inflate(getApplicationContext(), R.layout.drink_layout, null));
            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content.removeAllViews();
                content.addView(View.inflate(getApplicationContext(), R.layout.settings_layout, null));
            }
        });
    }
}

