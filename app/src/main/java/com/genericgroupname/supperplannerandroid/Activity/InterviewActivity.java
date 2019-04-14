package com.genericgroupname.supperplannerandroid.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.genericgroupname.supperplannerandroid.R;
import com.genericgroupname.supperplannerandroid.User.User;
import com.genericgroupname.supperplannerandroid.User.UserBuilder;
import com.genericgroupname.supperplannerandroid.Utils.JsonParser;

import org.json.JSONException;

import java.io.IOException;

public class InterviewActivity extends AppCompatActivity {
private EditText name,age,weight,height;
private CheckBox man,woman,backProblems,eyeProblems,mindProblems;
private RadioButton lightTheme,darkTheme;
private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);
        name = findViewById(R.id.nameEditText);
        age = findViewById(R.id.ageEditText);
        weight = findViewById(R.id.weightEditText);
        height = findViewById(R.id.heightEditText);
        man = findViewById(R.id.manCheckBox);
        woman = findViewById(R.id.womanCheckBox);
        backProblems = findViewById(R.id.problemsWithBack);
        eyeProblems = findViewById(R.id.problemsWithEyes);
        mindProblems = findViewById(R.id.problemsWithMind);
        lightTheme = findViewById(R.id.toggleLight);
        darkTheme = findViewById(R.id.toggleDark);
        nextBtn = findViewById(R.id.goNext);

        name.requestFocus();
        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                woman.setChecked(false);
            }
        });
        woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                man.setChecked(false);
            }
        });
        lightTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            }
        });
        darkTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().getDecorView().setBackgroundColor(Color.parseColor("#43434E"));
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBuilder userBuilder = new UserBuilder(name,age,weight,height,man,woman,lightTheme,darkTheme,eyeProblems,backProblems,mindProblems);
                User user = null;
                try {
                    user = userBuilder.build();
                } catch (Exception e) {
                    Toast.makeText(InterviewActivity.this, "Wrong input data", Toast.LENGTH_SHORT).show();
                }
                if(user == null)
                    Toast.makeText(InterviewActivity.this, "Wrong input data", Toast.LENGTH_SHORT).show();
                    else{
                    JsonParser jsonParser = new JsonParser(getApplicationContext());
                    try {
                        jsonParser.saveJson(user);
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                    WelcomeActivity.getUser = user;
                    Intent s = new Intent(getApplicationContext(), MainMenuActivity.class);
                        finish();
                        startActivity(s);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }

            }
        });
    }



}
