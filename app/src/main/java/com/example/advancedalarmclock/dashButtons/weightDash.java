package com.example.advancedalarmclock.dashButtons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;
import com.example.advancedalarmclock.dashButtons.weightAdvancedGraph.weightAdvancedGraph;
import com.example.advancedalarmclock.dashButtons.weightInfo.weightInfo;
import com.example.advancedalarmclock.dashButtons.weightJournal.weightJournal;

public class weightDash extends AppCompatActivity {

    ImageButton weightJournal, weightAdvancedGraph, weightInfo;
    ImageView rtnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_dash);

        weightJournal = findViewById(R.id.weightJournal);
        weightJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeightJournal();
            }
        });

        weightAdvancedGraph = findViewById(R.id.weightAdvancedGraph);
        weightAdvancedGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeightAdvancedGraph();
            }
        });

        weightInfo = findViewById(R.id.weightInfo);
        weightInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeightInfo();
            }
        });

        rtnButton = findViewById(R.id.rtnBtnWeight);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    void openWeightJournal(){
        Intent intent = new Intent(this, weightJournal.class);
        startActivity(intent);
    }

    void openWeightAdvancedGraph(){
        Intent intent = new Intent(this, weightAdvancedGraph.class);
        startActivity(intent);
    }

    void openWeightInfo(){
        Intent intent = new Intent(this, com.example.advancedalarmclock.dashButtons.weightInfo.weightInfo.class);
        startActivity(intent);
    }
}