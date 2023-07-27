package com.example.advancedalarmclock.dashButtons.hrInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;

public class visualHr extends AppCompatActivity {
    private ImageView returnButton;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_hr);

        returnButton = findViewById(R.id.webViewHrReturn);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        webView = (WebView) findViewById(R.id.faqlHrWebView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.webmd.com/heart/ss/slideshow-heart-rate#:~:text=A%20Healthy%20Resting%20Heart%20Rate&text=Most%20healthy%20adults%20should%20have,your%20heart%20rate%20will%20be.");
    }
}