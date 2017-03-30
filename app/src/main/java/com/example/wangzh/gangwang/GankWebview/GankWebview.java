package com.example.wangzh.gangwang.GankWebview;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by WangZh on 2017/3/22.
 */

public class GankWebview {
    private WebViewContract mWebViewContract;
    public GankWebviewClient webviewClient;
    public GankWebChromeClient webChromeClient;

    GankWebview(WebViewContract contract){
        this.mWebViewContract=contract;
        webChromeClient=new GankWebChromeClient();
        webviewClient=new GankWebviewClient();
    }

    public class GankWebviewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            mWebViewContract.setProgressVisible(true);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mWebViewContract.setProgressVisible(false);
        }
    }
    public class GankWebChromeClient extends android.webkit.WebChromeClient{
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            mWebViewContract.setTitle(title);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress<100){
                mWebViewContract.setProgressBar(newProgress);
            }
        }
    }
}
