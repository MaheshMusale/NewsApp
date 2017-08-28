package com.example.hp.navigationwithtab.activity;

/**
 * Created by HP on 30/04/2017.
 */

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hp.navigationwithtab.R;
import com.example.hp.navigationwithtab.modelclass.NewsDetail;

//import static com.example.hp.navigationwithtab.R.id.progressBar;
import static com.example.hp.navigationwithtab.R.id.toolbar;

public class NewsDescription extends AppCompatActivity{

    Toolbar mNewsToolbar;
    TextView mNewsDescription;
    //WebView mNewsWebView;
    ImageView mNewsImage;
    //ProgressBar mProgressBar;
    CollapsingToolbarLayout mNewsCollapsingToolbarLayout;
    AppBarLayout mAppBarLayout;
    private String postUrl ="";
    NewsDetail mNewsDetail;
    String img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsdetail_layout);

        mNewsToolbar= (Toolbar) findViewById(toolbar);
       // mNewsWebView= (WebView) findViewById(R.id.webView);
        mNewsImage= (ImageView) findViewById(R.id.backdrop);
        //mProgressBar= (ProgressBar) findViewById(progressBar);
        mNewsDescription= (TextView) findViewById(R.id.description);


        Intent intent=getIntent();
        mNewsDetail= (NewsDetail) intent.getSerializableExtra("NewsDetail");
        String url=mNewsDetail.getmNewsDescription();
        String title=mNewsDetail.getmNewstitle();

        setSupportActionBar(mNewsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initCollapsingToolbar();

        mNewsDescription.setText(title +"\n"+" "+"\n"+ url);


        /*mNewsWebView.getSettings().setJavaScriptEnabled(true);
        *//*mNewsWebView.setWebViewClient(new WebViewClient());*//*
        mNewsWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressBar.setVisibility(view.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mNewsWebView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(view.GONE);
            }
        });
        mNewsWebView.loadUrl(url);
        mNewsWebView.setHorizontalScrollBarEnabled(false);
*/
        img=mNewsDetail.getmNewsImage();
        Glide.with(this).load(img).placeholder(R.drawable.placeholder).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into( mNewsImage);
    }


    private void initCollapsingToolbar() {
        mNewsCollapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mNewsCollapsingToolbarLayout.setTitle(" ");

        mAppBarLayout= (AppBarLayout) findViewById(R.id.appbar);
        mAppBarLayout.setExpanded(true);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mNewsCollapsingToolbarLayout.setTitle("News Description");
                    isShow = true;
                } else if (isShow) {
                    mNewsCollapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
