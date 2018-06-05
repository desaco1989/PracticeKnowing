package com.desaco.practiceknowing.view_event_dispatch;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by desaco on 2018/6/5.
 * <p>
 * View本身是不存在分发，所以也没有拦截方法（onInterceptTouchEvent）
 */

public class CustomView extends View {
    public CustomView(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("desaco", "View onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("desaco", "View dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

}
