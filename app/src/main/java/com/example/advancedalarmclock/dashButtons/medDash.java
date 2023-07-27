package com.example.advancedalarmclock.dashButtons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;
import com.example.advancedalarmclock.dashButtons.medInfo.medInfo;
import com.example.advancedalarmclock.dashButtons.medJournal.medJournal;

public class medDash extends AppCompatActivity {

    ImageButton medJournal, medInfo;
    ImageView rtnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_dash);

        medJournal = findViewById(R.id.medJournal);
        medJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMedJournal();
            }
        });

        medInfo = findViewById(R.id.medInfo);
        medInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMedInfo();
            }
        });

        rtnButton = findViewById(R.id.rtnBtnMed);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    void openMedJournal(){
        Intent intent = new Intent(this, com.example.advancedalarmclock.dashButtons.medJournal.medJournal.class);
        startActivity(intent);
    }

    void openMedInfo(){
        Intent intent = new Intent(this, com.example.advancedalarmclock.dashButtons.medInfo.medInfo.class);
        startActivity(intent);
    }
}