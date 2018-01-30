package com.example.panagiotisspentzouris.batterystats;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.View.OnClickListener;

import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton myFab = findViewById(R.id.fab);
        myFab.setOnClickListener(this);

        WebView mWebview = findViewById(R.id.webview);
        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript
        mWebview.getSettings().setSupportZoom(true);
        mWebview.getSettings().setBuiltInZoomControls(true);

        final Activity activity = this;

        mWebview.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        mWebview.loadUrl("http://www.google.com");

        //setContentView(mWebview);

    }

    @Override
    public void onClick(View view) {
        Intent inent = new Intent(this, YoutubeActivity.class);
        startActivity(inent);
    }
}
