package com.example.gmifyp;


import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import android.content.Context;
import android.app.Activity;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.widget.Toolbar;
import android.net.Uri;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

public class library extends AppCompatActivity {
    /** Called when the activity is first created. */
    public WebView WebView;
    private final String googleDocs = "https://docs.google.com/viewer?url=";
    private static final int LOADING = 6500;
    @SuppressLint("JavascriptInterface")
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webread);

         WebView = (WebView) findViewById(R.id.webview3);

        /* An instance of this class will be registered as a JavaScript interface */
        class MyJavaScriptInterface
        {
            private TextView contentView;

            public MyJavaScriptInterface(TextView aContentView)
            {
                contentView = aContentView;
            }

            @SuppressWarnings("unused")

            public void processContent(String aContent)
            {
                final String content = aContent;
                contentView.post(new Runnable()
                {
                    public void run()
                    {
                        contentView.setText(content);
                    }
                });
            }
        }

       getSupportActionBar().show();
       getSupportActionBar().setTitle(R.string.subtitle25);;
       getSupportActionBar().setSubtitle(null);



        WebView.getSettings().setUserAgentString("Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3");
        WebView.getSettings().setJavaScriptEnabled(true);
        WebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                view.loadUrl("javascript:window.INTERFACE.processContent(document.getElementsByTagName('body')[0].innerText);");
            }
        });

        WebView.loadUrl("http://repository.gmi.edu.my/view/");
        //WebView.getSettings().setBuiltInZoomControls(true);


        WebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView41, String url) {
                if (url.endsWith(".pdf")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(url), "application/pdf");
                    /* Check if there is any application capable to process PDF file. */
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        /* If not, show PDF in Google Docs instead. */
                        message();
                        webView41.loadUrl(googleDocs + url);

                    }
                } else {
                    webView41.loadUrl(url);
                }
                return true;
            }
        });
    }
public void message(){
    Toast.makeText(this, "Operation : Loading PDF", Toast.LENGTH_LONG).show();
    Toast.makeText(this, "Operation : Loading PDF", Toast.LENGTH_LONG).show();
    Toast.makeText(this, "Operation : Loading PDF", Toast.LENGTH_LONG).show();
}
}