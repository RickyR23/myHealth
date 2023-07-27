package com.example.advancedalarmclock.dashButtons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;
import com.example.advancedalarmclock.dashButtons.hrAdvancedGraph.hrAdvancedGraph;
import com.example.advancedalarmclock.dashButtons.hrInfo.hrInfo;
import com.example.advancedalarmclock.dashButtons.hrJournal.hrJournal;

public class hrDash extends AppCompatActivity {

    ImageButton hrJournal, hrAdvancedGraph, hrInfo;
    ImageView returnBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_dash);

        hrJournal = findViewById(R.id.hrJournal);
        hrJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHrJournal();
            }
        });
        hrAdvancedGraph = findViewById(R.id.hrAdvancedGraph);
        hrAdvancedGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHrAdvancedGraph();
            }
        });
        hrInfo = findViewById(R.id.hrInfo);
        hrInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHrInfo();
            }
        });

        returnBtn = findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    void openHrJournal(){
        Intent intent = new Intent(this, com.example.advancedalarmclock.dashButtons.hrJournal.hrJournal.class);
        startActivity(intent);
    }

    void openHrAdvancedGraph(){
        Intent intent = new Intent(this, com.example.advancedalarmclock.dashButtons.hrAdvancedGraph.hrAdvancedGraph.class);
        startActivity(intent);
    }

    void openHrInfo(){
        Intent intent = new Intent(this, com.example.advancedalarmclock.dashButtons.hrInfo.hrInfo.class);
        startActivity(intent);
    }


}