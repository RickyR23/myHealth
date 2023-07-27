package com.example.advancedalarmclock.dashButtons.gcInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;

public class gcInfo extends AppCompatActivity {
private ImageButton glucoseDiabetesBtn, glucoseTestFAQ;
private ImageView returnbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gc_info);
        glucoseDiabetesBtn = findViewById(R.id.faqGcButton);
        glucoseDiabetesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGcFaq();
            }
        });

        glucoseTestFAQ = findViewById(R.id.glucoseTestingButton);
        glucoseTestFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            openGcTesting();
            }
        });

        returnbtn = findViewById(R.id.infoGcReturnButton);
        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    void openGcFaq(){
        Intent intent = new Intent(this, faqGc.class);
        startActivity(intent);
    }

    void openGcTesting(){
        Intent intent = new Intent(this, testingGc.class);
        startActivity(intent);
    }
}