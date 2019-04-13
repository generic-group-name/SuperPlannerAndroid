package com.genericgroupname.supperplannerandroid.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.genericgroupname.supperplannerandroid.R;


public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button letsGoBtn = findViewById(R.id.letsGoBtn);


        //TODO zaladowanie ustawiem uzytkownika z tekstu
        // jesli sie uda wywal do main menu
        letsGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(getApplicationContext(), InterviewActivity.class);
                finish();
                startActivity(s);
            }
        });

    }

}
