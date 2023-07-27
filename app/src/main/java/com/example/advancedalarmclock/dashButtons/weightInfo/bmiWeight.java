package com.example.advancedalarmclock.dashButtons.weightInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;

public class bmiWeight extends AppCompatActivity {
private ImageView returnBtn;

private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_weight);
        returnBtn = findViewById(R.id.bmiWebViewReturn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        webView = (WebView) findViewById(R.id.bmiWeightWebView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.webmd.com/diet/body-bmi-calculator");
    }
}