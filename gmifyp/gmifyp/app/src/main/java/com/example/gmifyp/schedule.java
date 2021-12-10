package com.example.gmifyp;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.Nullable;


public class schedule extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_schedule);
        WebView webview2 = findViewById(R.id.webview2);
        webview2.getSettings().setJavaScriptEnabled(true);
        webview2.setWebViewClient(new WebViewClient());
        webview2.setInitialScale(170);
        webview2.getSettings().setLoadWithOverviewMode(false);
        webview2.getSettings().setUseWideViewPort(false);
        webview2.getSettings().setBuiltInZoomControls(true);
        webview2.getSettings().setDisplayZoomControls(false);
        webview2.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webview2.setScrollbarFadingEnabled(false);
        webview2.loadUrl("http://class.gmi.edu.my/");
        setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle(R.string.subtitle);
        //getSupportActionBar().hide();
        getSupportActionBar().show();
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setSubtitle(R.string.subtitle13);
        Handler hend = new Handler();

        hend.postDelayed(new Runnable() {

            @Override
            public void run() {
                // DO DELAYED STUFF
                getSupportActionBar().hide();
            }
        }, 5000);
    }
}
