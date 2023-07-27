package com.example.advancedalarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.advancedalarmclock.dashButtons.bpDash;
import com.example.advancedalarmclock.dashButtons.gcDash;
import com.example.advancedalarmclock.dashButtons.hrDash;
import com.example.advancedalarmclock.dashButtons.medDash;
import com.example.advancedalarmclock.dashButtons.settingsDash;
import com.example.advancedalarmclock.dashButtons.weightDash;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private Button gcButton;
    private Button bpButton;
    private Button weightButton;
    private Button hrButton;
    private Button medButton;
    private FloatingActionButton settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gcButton = (Button) findViewById(R.id.gc_button);
        gcButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                opengcDash();
            }
        });

        bpButton = (Button) findViewById(R.id.bp_button);
        bpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbpDash();
            }
        });

        weightButton = (Button) findViewById(R.id.weight_button);
        weightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeightDash();
            }
        });

        hrButton = (Button) findViewById(R.id.hr_button);
        hrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHrDash();
            }
        });

        medButton = (Button) findViewById(R.id.medication_button);
        medButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMedButton();
            }
        });

       settingsButton = (FloatingActionButton) findViewById(R.id.settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                openSettingsButton();
           }
        });

    }
            public void opengcDash() {
                Intent intent = new Intent(this, gcDash.class);
                startActivity(intent);
            }

            public void openbpDash(){
        Intent intent = new Intent(this, bpDash.class);
        startActivity(intent);
        }

        public void openWeightDash(){
        Intent intent = new Intent(this, weightDash.class);
        startActivity(intent);
        }

        public void openHrDash(){
        Intent intent = new Intent(this, hrDash.class);
        startActivity(intent);
        }

        public void openMedButton(){
        Intent intent = new Intent(this, medDash.class);
        startActivity(intent);
        }

        public void openSettingsButton(){
        Intent intent = new Intent(this, settingsDash.class);
        startActivity(intent);
       }
// finish creating redirect view activities for rest of the buttons
    // fix button name text's (they appear top left of phone screen)
}