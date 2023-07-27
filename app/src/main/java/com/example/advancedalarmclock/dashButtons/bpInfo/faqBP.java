package com.example.advancedalarmclock.dashButtons.bpInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;

public class faqBP extends AppCompatActivity {

    private WebView faqWebview;
    private ImageView rtnbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_bp);

        faqWebview = (WebView) findViewById(R.id.faqBpWebView);
        faqWebview.setWebViewClient(new WebViewClient());
        faqWebview.loadUrl("https://www.webmd.com/hypertension-high-blood-pressure/hypertension-frequently-asked-questions");

        rtnbutton = findViewById(R.id.webViewReturnButton);
        rtnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}