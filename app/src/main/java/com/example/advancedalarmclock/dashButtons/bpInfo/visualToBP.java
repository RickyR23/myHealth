package com.example.advancedalarmclock.dashButtons.bpInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;

public class visualToBP extends AppCompatActivity {

    private WebView visualWebView;
    private ImageView rtnButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_to_bp);

        rtnButton = findViewById(R.id.webViewReturnButtonVisual);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        visualWebView = (WebView) findViewById(R.id.visualBpWebView);
        visualWebView.setWebViewClient(new WebViewClient());
        visualWebView.loadUrl("https://www.webmd.com/hypertension-high-blood-pressure/ss/slideshow-hypertension-overview");
    }
}