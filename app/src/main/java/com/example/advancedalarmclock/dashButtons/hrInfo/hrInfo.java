package com.example.advancedalarmclock.dashButtons.hrInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;

public class hrInfo extends AppCompatActivity {
    private ImageView returnButton;
    private ImageButton faqButton, visualButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_info);
        returnButton = findViewById(R.id.hrInfoReturn);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        faqButton = findViewById(R.id.faqHrButton);
        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFaqButton();
            }
        });

        visualButton = findViewById(R.id.visualHrButton);
        visualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVisualButton();
            }
        });
    }

    void openFaqButton(){
        Intent intent = new Intent(this, faqHr.class);
        startActivity(intent);
    }

    void openVisualButton(){
        Intent intent = new Intent(this, visualHr.class);
        startActivity(intent);
    }

}