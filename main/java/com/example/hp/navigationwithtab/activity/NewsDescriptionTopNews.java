package com.example.hp.navigationwithtab.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.hp.navigationwithtab.R;
import com.example.hp.navigationwithtab.modelclass.News;

/**
 * Created by Mahesh on 6/2/2017.
 */

public class NewsDescriptionTopNews extends Activity {
    WebView mwebview;
    String url;
    News mnews;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {

        super.onCreate(savedInstanceState, persistentState);
        Toast.makeText(this, "im in newsdescription", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.newsdescriptiontopnews);
        mwebview= (WebView) findViewById(R.id.webview);
        Intent intent=new Intent();
        mnews= (News) intent.getSerializableExtra("News");
        url=mnews.getNewsDescription();
        System.out.println("url:"+mnews.getNewsDescription());
        mwebview.getSettings().setJavaScriptEnabled(true);
        mwebview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mwebview.loadUrl(mnews.getNewsDescription());
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }

    }
