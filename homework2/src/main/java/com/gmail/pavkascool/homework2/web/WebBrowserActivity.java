package com.gmail.pavkascool.homework2.web;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

import com.gmail.pavkascool.homework2.R;

public class WebBrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);
        WebView webView = findViewById(R.id.web);
        Uri address = getIntent().getData();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(address.toString());
    }
}
