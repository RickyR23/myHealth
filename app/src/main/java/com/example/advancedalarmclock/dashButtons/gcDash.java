package com.example.advancedalarmclock.dashButtons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;
import com.example.advancedalarmclock.dashButtons.gcAdvancedGraph.gcAdvancedGraph;
import com.example.advancedalarmclock.dashButtons.gcInfo.gcInfo;
import com.example.advancedalarmclock.dashButtons.gcJournal.gcJournal;


public class gcDash extends AppCompatActivity {
    ImageButton glucoseJournal, gcAdvancedGraph, gcInfo;

    ImageView rtnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gc_dash);

        glucoseJournal = (ImageButton) findViewById(R.id.gcJournal);
        glucoseJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGcJournal();
            }
        });

        rtnButton = findViewById(R.id.rtnButtn);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        gcAdvancedGraph = findViewById(R.id.gcAdvancedGraph);
        gcAdvancedGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGcAdvancedData();
            }
        });

        gcInfo = findViewById(R.id.gcInfo);
        gcInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGcInfo();
            }
        });
    }

    public void openGcJournal(){
        Intent intent = new Intent(this, gcJournal.class);
        startActivity(intent);
    }

    public void openGcAdvancedData(){
        Intent intent = new Intent(this, com.example.advancedalarmclock.dashButtons.gcAdvancedGraph.gcAdvancedGraph.class);
        startActivity(intent);
    }

    void openGcInfo(){
        Intent intent = new Intent(this, com.example.advancedalarmclock.dashButtons.gcInfo.gcInfo.class);
        startActivity(intent);
    }
}