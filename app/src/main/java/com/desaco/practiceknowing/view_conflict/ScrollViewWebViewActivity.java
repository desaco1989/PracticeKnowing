package com.desaco.practiceknowing.view_conflict;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.desaco.practiceknowing.R;

import java.io.InputStream;

/**
 * Created by desaco on 2018/5/23.
 * com.desaco.practiceknowing.view_conflict.ScrollViewWebViewActivity
 */
public class ScrollViewWebViewActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview_webview);
        loadHtml();
    }
    private void loadHtml(){
        WebView webview = (WebView)findViewById(R.id.webview_h5);
        //支持App内部javascript交互
        webview.getSettings().setJavaScriptEnabled(true);
        //自适应屏幕
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setLoadWithOverviewMode(true);
        //设置可以支持缩放
        webview.getSettings().setSupportZoom(true);
        //扩大比例的缩放
        webview.getSettings().setUseWideViewPort(true);
        //设置是否出现缩放工具
        webview.getSettings().setBuiltInZoomControls(true);

        //打开本包内asset目录下的test.html文件
        webview.loadUrl("file:///android_asset/test_css.html");
//        webview.loadUrl("file:///android_asset/License/privacy.html")

        //打开本地sd卡内的kris.html文件
//        webview.loadUrl("file:///mnt/sdcard/test.html");
//        webview.loadUrl("file://"+"/mnt/sdcard/"+"index.html");
//        webview.loadUrl("content://com.android.htmlfileprovider/sdcard/kris.html");
    }

}
