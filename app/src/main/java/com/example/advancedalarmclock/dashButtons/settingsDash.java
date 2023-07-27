package com.example.advancedalarmclock.dashButtons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;
import com.example.advancedalarmclock.dashButtons.userSettings.userSettings;

public class settingsDash extends AppCompatActivity {

    ImageView rtnBtn;
    ImageButton displaySetting, userSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_dash);
        rtnBtn = findViewById(R.id.rtnBtnSettings);
        rtnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        displaySetting = findViewById(R.id.displaySettings);
        displaySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplaySetting();
            }
        });

        userSetting = findViewById(R.id.userSettings);
        userSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserSetting();
            }
        });


    }

    void openDisplaySetting(){
        Intent intent = new Intent(this, com.example.advancedalarmclock.dashButtons.userSettings.displaySettings.class);
        startActivity(intent);
    }

    void openUserSetting(){
        Intent intent = new Intent(this, userSettings.class);
        startActivity(intent);
    }



}