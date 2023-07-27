package com.example.advancedalarmclock.dashButtons.hrInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;

public class faqHr extends AppCompatActivity {
private ImageView returnBtn;
private WebView faqWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_hr);

        returnBtn = findViewById(R.id.faqwebViewHrReturn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        faqWebView = (WebView) findViewById(R.id.faqHrWebView);
        faqWebView.setWebViewClient(new WebViewClient());
        faqWebView.loadUrl("https://www.webmd.com/heart-disease/heart-failure/watching-rate-monitor");
    }
}