package com.desaco.practiceknowing.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by desaco on 2018/6/7.
 * 监听滚动的WebView事件
 */

public class ScrollWebView extends WebView {
    //
    private OnScrollChangedCallback mOnScrollChangedCallback;

    public ScrollWebView(final Context context) {
        super(context);
    }

    public ScrollWebView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollWebView(final Context context, final AttributeSet attrs,
                         final int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(final int dx, final int dy, final int dxChange,
                                   final int dyChange) {

        super.onScrollChanged(dx, dy, dxChange, dyChange);
        //final int l, final int t, final int oldl,final int oldt

        if (mOnScrollChangedCallback != null) {
            //l - oldl, t - oldt
            mOnScrollChangedCallback.onScroll(dx, dy, dxChange, dyChange);
        }
    }

    public OnScrollChangedCallback getOnScrollChangedCallback() {
        return mOnScrollChangedCallback;
    }

    //set监听
    public void setOnScrollChangedCallback(final OnScrollChangedCallback onScrollChangedCallback) {
        this.mOnScrollChangedCallback = onScrollChangedCallback;
    }

    /**
     * Impliment in the activity/fragment/view that you want to listen to the webview
     */
    public static interface OnScrollChangedCallback {
        public void onScroll(int dx, int dy, int dxChange, int dyChange);
    }
}
