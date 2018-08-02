package com.desaco.practiceknowing.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.jar.Attributes;

/**
 * Created by desaco on 2018/8/2.
 */

public class CustomViewGroupEvent extends LinearLayout {
    //
    public CustomViewGroupEvent(Context context) {
        super(context);
    }

    public CustomViewGroupEvent(Context context, AttributeSet attr) {
        super(context, attr);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//
//        }
//        return super.onTouchEvent(event);
//    }

    float startPos;
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                startPos = getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float movePos = getY();
//                float mStartX = ev.getRawX();
////                getScrollY();
//                if(movePos - startPos >0){
//
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//
//        }
//
//        int subViewCount = getChildCount();
//        for (int i = 0; i < subViewCount; i++) {
//            Log.e("desaco", "subView=" + getChildAt(0));
//        }
//        int top = getTop();
//        getScrollY();
//        getY();
//        return super.onInterceptTouchEvent(ev);
//    }

    //获取LinearLayout中的组件
    public void traversalView(ViewGroup viewGroup) {
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup) {
                traversalView((ViewGroup) view);
            } else {
//                doView(view);
            }
        }
    }

}
