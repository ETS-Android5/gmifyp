package com.example.gmifyp;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.app.Application;
import android.os.Environment;
import java.io.File;


public class VLU extends Activity {
    /** Called when the activity is first created. */
    protected File extStorageAppBasePath;
    protected File extStorageAppCachePath;
    @SuppressLint("JavascriptInterface")
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webread);
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File externalStorageDir = Environment.getExternalStorageDirectory();
            if (externalStorageDir != null) {
                extStorageAppBasePath = new File(externalStorageDir.getAbsolutePath() +
                        File.separator + "Android" + File.separator + "data" +
                        File.separator + getPackageName());
            }

            if (extStorageAppBasePath != null) {
                extStorageAppCachePath = new File(extStorageAppBasePath.getAbsolutePath() +
                        File.separator + "cache");

                boolean isCachePathAvailable = true;

                if (!extStorageAppCachePath.exists()) {
                    isCachePathAvailable = extStorageAppCachePath.mkdirs();
                }

                if (!isCachePathAvailable) {
                    extStorageAppCachePath = null;
                }
            }
        }

        WebView webView = (WebView) findViewById(R.id.webview4);

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
        webView.getSettings().setUserAgentString("Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3");
        webView.getSettings().setJavaScriptEnabled(true);
       webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                view.loadUrl("javascript:window.INTERFACE.processContent(document.getElementsByTagName('body')[0].innerText);");
            }
        });
        //document.getElementsByClassName("d-none d-md-inline-block mx-1")[0].innerText

        webView.loadUrl("http://vlu.gmi.edu.my/message/output/popup/notifications.php");
    }
   @Override
    public File getCacheDir() {
        if (extStorageAppCachePath != null) {
            return extStorageAppCachePath;
        }
        else {
            return super.getCacheDir();
        }
    }


}