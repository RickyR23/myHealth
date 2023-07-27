package com.example.advancedalarmclock.dashButtons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;

public class bpDash extends AppCompatActivity {

    ImageButton bpJournal, bpAdvancedGraph, bpInfo;
    ImageView rtnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_dash);

        bpJournal = findViewById(R.id.bpJournal);
        bpJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBpJournal();
            }
        });

        bpAdvancedGraph = findViewById(R.id.bpAdvancedGraph);
        bpAdvancedGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBpAdvancedGraph();
            }
        });

        bpInfo = findViewById(R.id.bpInfo);
        bpInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBpInfo();
            }
        });

        rtnButton = findViewById(R.id.return_buttonBp);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    void openBpJournal(){
        Intent intent = new Intent(this, com.example.advancedalarmclock.dashButtons.bpJournal.bpJournal.class);
        startActivity(intent);
    }

    void openBpAdvancedGraph(){
        Intent intent = new Intent(this, com.example.advancedalarmclock.dashButtons.bpAdvancedGraph.bpAdvancedGraph.class);
        startActivity(intent);
    }

    void openBpInfo(){
        Intent intent = new Intent(this, com.example.advancedalarmclock.dashButtons.bpInfo.bpInfo.class);
        startActivity(intent);
    }
}