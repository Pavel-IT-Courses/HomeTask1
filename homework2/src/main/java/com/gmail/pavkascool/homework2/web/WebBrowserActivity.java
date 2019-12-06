package com.gmail.pavkascool.homework2.web;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebViewClient;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

import com.gmail.pavkascool.homework2.R;

public class WebBrowserActivity extends AppCompatActivity implements View.OnClickListener {

    private Button previous, browser;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);
        webView = findViewById(R.id.web);
        webView.setWebViewClient(new WebClient());
        Uri address = getIntent().getData();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(address.toString());
        previous = findViewById(R.id.back);
        previous.setOnClickListener(this);
        browser = findViewById(R.id.browser);
        browser.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.back:
                webView.goBack();
                break;
            case R.id.browser:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(webView.getUrl()));
                startActivity(intent);
                break;
        }

    }

    private class WebClient extends WebViewClient {
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
