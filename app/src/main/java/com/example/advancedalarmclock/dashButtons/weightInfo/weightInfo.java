package com.example.advancedalarmclock.dashButtons.weightInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;

public class weightInfo extends AppCompatActivity {
    private ImageView returnButton;
    private ImageButton weightBMI, weightFAQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_info);

        returnButton = findViewById(R.id.weightInfoReturnBtn);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        weightFAQ = findViewById(R.id.weightFaqButton);
        weightFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeightFAQ();
            }
        });

        weightBMI = findViewById(R.id.weightBmiButton);
        weightBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeightBMI();
            }
        });

    }

    void openWeightFAQ(){
        Intent intent = new Intent(this, faqWeight.class);
        startActivity(intent);
    }

    void openWeightBMI(){
        Intent intent = new Intent(this, bmiWeight.class);
        startActivity(intent);
    }


}