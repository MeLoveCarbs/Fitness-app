package com.example.firstdraft.Activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.firstdraft.R;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DisplayPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_page);

        WebView wbb = (WebView) findViewById(R.id.webPageView);
        WebSettings wbset=wbb.getSettings();
        wbset.setJavaScriptEnabled(true);
        wbset.setBuiltInZoomControls(true);
        wbb.setWebViewClient(new WebViewClient());
        String url="file:///android_asset/workoutguide.html";
        wbb.loadUrl(url);



    }
}
