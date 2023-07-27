package com.example.advancedalarmclock.dashButtons.gcInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;

public class faqGc extends AppCompatActivity {
    private ImageView faqGcReturn;
    private WebView faqGcView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_gc);
        faqGcReturn = findViewById(R.id.webViewGcfaqReturn);
        faqGcReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        faqGcView = (WebView) findViewById(R.id.faqGcWebView);
        faqGcView.setWebViewClient(new WebViewClient());
        faqGcView.loadUrl("https://www.webmd.com/diabetes/diagnosing-type-2-diabetes");

    }
}