package com.example.wangzh.gangwang.GankWebview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.wangzh.gangwang.R;

/**
 * Created by WangZh on 2017/3/22.
 */

public class WebViewActivity extends AppCompatActivity implements WebViewContract {
    private Toolbar mToolbar;
    private TextView mTitle;
    private WebView mWebView;
    private WebSettings mWebSettings;
    private ProgressBar mProgressBar;
    GankWebview mGankWebview;

    @Override
    public void setTitle(String title) {
        String Title = title.length() < 10 ? title : title.substring(0, 10) + "...";
        mTitle.setText(Title);
    }

    @Override
    public void setProgressBar(int progress) {
        mProgressBar.setProgress(progress);
    }

    @Override
    public void setProgressVisible(boolean visible) {
        if (visible){
            mProgressBar.setVisibility(View.VISIBLE);
        }else {
            mProgressBar.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        mToolbar=(Toolbar) findViewById(R.id.toolbar_web);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mProgressBar=(ProgressBar)findViewById(R.id.progressBar_web);
        mProgressBar.setMax(100);
        mTitle=(TextView)findViewById(R.id.text_web_title);

        mWebView=(WebView)findViewById(R.id.webview);
        mWebSettings=mWebView.getSettings();
        setWebSettings();
        mGankWebview=new GankWebview(this);
        mWebView.setWebViewClient(mGankWebview.webviewClient);
        mWebView.setWebChromeClient(mGankWebview.webChromeClient);


        Intent intent=getIntent();
        String url = intent.getStringExtra("url");

        mWebView.loadUrl(url);
    }

    private void setWebSettings(){
        mWebSettings.setJavaScriptEnabled(true);  //开启javascript
        mWebSettings.setDomStorageEnabled(true);  //开启DOM
        mWebSettings.setDefaultTextEncodingName("utf-8");//设置编码
         // web页面处理
        mWebSettings.setAllowFileAccess(true);// 支持文件流
        // mWebSettings.setSupportZoom(true);// 支持缩放
        // mWebSettings.setBuiltInZoomControls(true);// 支持缩放
        mWebSettings.setUseWideViewPort(true);// 调整到适合webview大小
        mWebSettings.setLoadWithOverviewMode(true);//调整到适合webview大小
        mWebSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);//屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常
        mWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
//提高网页加载速度，暂时阻塞图片加载，然后网页加载好了，在进行加载图片
        mWebSettings.setBlockNetworkImage(true);
//开启缓存机制
        mWebSettings.setAppCacheEnabled(true);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onDestroy() {
        if(mWebView!=null){
            mWebView.destroy();
        }
        super.onDestroy();
        //System.exit(0);
    }
}
