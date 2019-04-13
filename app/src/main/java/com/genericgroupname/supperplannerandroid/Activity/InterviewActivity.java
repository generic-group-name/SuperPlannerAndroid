package com.genericgroupname.supperplannerandroid.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.genericgroupname.supperplannerandroid.R;

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
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



}
