package com.example.advancedalarmclock.dashButtons.gcInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;

public class testingGc extends AppCompatActivity {
    private ImageView webViewReturn;
    private WebView gcWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_gc);
        webViewReturn = findViewById(R.id.webViewGcReturn);
        webViewReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        gcWebView = (WebView) findViewById(R.id.testingGcWebView);
        gcWebView.setWebViewClient(new WebViewClient());
        gcWebView.loadUrl("https://www.webmd.com/diabetes/how-sugar-affects-diabetes");

    }
}