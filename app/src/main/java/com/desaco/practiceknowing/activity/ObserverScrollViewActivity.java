package com.desaco.practiceknowing.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.desaco.practiceknowing.R;
import com.desaco.practiceknowing.utils.SharedPrefsUtil;
import com.desaco.practiceknowing.widget.ScrollWebView;

/**
 * Created by desaco on 2018/6/7.
 * https://www.jianshu.com/p/d4d02cb9791c
 */

public class ObserverScrollViewActivity extends Activity {
    //

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        initPublicView();
        //test_tv
        initWebView();

        initRecyclerView();
    }

    private TextView testTv;
    private TextView topMiddleTv;

    private void initPublicView() {
        testTv = (TextView) findViewById(R.id.test_tv);
        topMiddleTv = (TextView) findViewById(R.id.top_middle_tv);
        topMiddleTv.setVisibility(View.GONE);
    }

    private void initWebView() {
        //
        ScrollWebView scrollWebView = (ScrollWebView) findViewById(R.id.scroll_webview);

        //支持App内部javascript交互
        scrollWebView.getSettings().setJavaScriptEnabled(true);
        //自适应屏幕
        scrollWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        scrollWebView.getSettings().setLoadWithOverviewMode(true);
        //设置可以支持缩放
        scrollWebView.getSettings().setSupportZoom(true);
        //扩大比例的缩放
        scrollWebView.getSettings().setUseWideViewPort(true);
        //设置是否出现缩放工具
        scrollWebView.getSettings().setBuiltInZoomControls(true);
        //打开本包内asset目录下的test.html文件
        scrollWebView.loadUrl("file:///android_asset/test_css.html");

        scrollWebView.setOnScrollChangedCallback(new ScrollWebView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int dx, int dy, int dxChange, int dyChange) {
                //TODO 记录当前WebView浏览的位置
                Log.e("desaco", "dx=" + dx + ",,dy=" + dy + ",,dxChange=" + dxChange + ",,dyChange=" + dyChange);
                SharedPrefsUtil.putValue(ObserverScrollViewActivity.this, "current_pos", dy);
                if (dy >= 30) {
                    topMiddleTv.setVisibility(View.VISIBLE);
                } else if (dy < 30) {
                    topMiddleTv.setVisibility(View.GONE);
                }
            }
        });
        scrollWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                testTv.setVisibility(View.VISIBLE);
                int pos = SharedPrefsUtil.getValue(ObserverScrollViewActivity.this, "current_pos", 0);
                view.scrollTo(0, pos);//TODO 跳转到上次页面打开的地方
            }
        });
    }

    private void initRecyclerView() {

    }

}
