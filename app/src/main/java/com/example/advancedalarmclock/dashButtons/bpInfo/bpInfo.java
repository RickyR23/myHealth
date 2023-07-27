package com.example.advancedalarmclock.dashButtons.bpInfo;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;

public class bpInfo extends AppCompatActivity {

    ImageButton faqInfo, visualInfo;
    ImageView rtnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_info);
            faqInfo = findViewById(R.id.faqBPButton);
            faqInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openFaqInfoButton();
                }
            });
            visualInfo = findViewById(R.id.visualGuideButton);
            visualInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openVisualInfoButton();
                }
            });

            rtnButton = findViewById(R.id.infoReturnButton);
            rtnButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
    }

    void openFaqInfoButton(){
        Intent intent = new Intent(this, faqBP.class);
        startActivity(intent);
    }

    void openVisualInfoButton(){
        Intent intent = new Intent(this, visualToBP.class);
        startActivity(intent);
    }
}