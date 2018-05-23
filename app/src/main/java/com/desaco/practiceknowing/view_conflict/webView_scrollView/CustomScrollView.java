package com.desaco.practiceknowing.view_conflict.webView_scrollView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by desaco on 2018/5/23.
 *
 * https://blog.csdn.net/qq_32452623/article/details/51468908
 */

/**
 *
 <ScrollView
 android:layout_width="match_parent"
 android:layout_height="wrap_content"
 android:descendantFocusability="blocksDescendants" >
 */
public class CustomScrollView extends ScrollView {

    private GestureDetector mGestureDetector;

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化手势
        mGestureDetector = new GestureDetector(context, new YScrollDetector());
    }

    /**
     * touch事件的拦截函数
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //根据手势决定是否拦截子控件的onTouch事件
        return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
    }

    /**
     * 控件的手势监听
     */
    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            //当纵向滑动的距离大于横向滑动的距离的时候，返回true
            if (Math.abs(distanceY) > Math.abs(distanceX)) {
                return true;
            }
            return false;
        }
    }
}
