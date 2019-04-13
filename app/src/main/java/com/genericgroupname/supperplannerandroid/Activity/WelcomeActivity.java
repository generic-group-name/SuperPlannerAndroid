package com.genericgroupname.supperplannerandroid.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.genericgroupname.supperplannerandroid.R;
import com.genericgroupname.supperplannerandroid.Service.PlanService;
import com.genericgroupname.supperplannerandroid.User.User;
import com.genericgroupname.supperplannerandroid.Utils.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class WelcomeActivity extends AppCompatActivity {
    public static User getUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button letsGoBtn = findViewById(R.id.letsGoBtn);
        JsonParser jsonParser = new JsonParser(getApplicationContext());

        try {
            getUser = jsonParser.getUser();
        } catch (JSONException |IOException e) {
            System.out.println("cos ejst zle");
        }
        if(PlanService.isRunning){
            Intent s = new Intent(getApplicationContext(), MainMenuActivity.class);
            finish();
            startActivity(s);
        }
        //TODO zaladowanie ustawiem uzytkownika z tekstu
        // jesli sie uda wywal do main menu
        letsGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(PlanService.isRunning != null )
                   if(PlanService.isRunning)
                   Toast.makeText(WelcomeActivity.this, "true", Toast.LENGTH_SHORT).show();
                Intent s = new Intent(getApplicationContext(), InterviewActivity.class);
                finish();
                startActivity(s);
            }
        });

    }

}
