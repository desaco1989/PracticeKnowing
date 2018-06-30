package com.desaco.practiceknowing.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by desaco on 2018/6/30.
 */

public class CustomViewGroup extends ViewGroup {

    public CustomViewGroup(Context context) {
        super(context);
    }

//    public CustomViewGroup(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public CustomViewGroup(Context context, AttributeSet attrs, intdefStyle) {
//        super(context, attrs, defStyle);
//    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        int mViewGroupWidth = getMeasuredWidth();  //当前ViewGroup的总宽度

        int mPainterPosX = left;  //当前绘图光标横坐标位置
        int mPainterPosY = top;  //当前绘图光标纵坐标位置

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {

            View childView = getChildAt(i);

            int width = childView.getMeasuredWidth();
            int height = childView.getMeasuredHeight();

            //如果剩余的空间不够，则移到下一行开始位置
            if (mPainterPosX + width > mViewGroupWidth) {
                mPainterPosX = left;
                mPainterPosY += height;
            }

            //执行ChildView的绘制
            childView.layout(mPainterPosX, mPainterPosY, mPainterPosX + width, mPainterPosY + height);

            //记录当前已经绘制到的横坐标位置
            mPainterPosX += width;
        }
    }

//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//
//        int mViewGroupWidth  = getMeasuredWidth();  //当前ViewGroup的总宽度
//        int mViewGroupHeight = getMeasuredHeight(); //当前ViewGroup的总高度
//
//        int mPainterPosX = left; //当前绘图光标横坐标位置
//        int mPainterPosY = top;  //当前绘图光标纵坐标位置
//
//        int childCount = getChildCount();
//        for ( int i = 0; i < childCount; i++ ) {
//
//            View childView = getChildAt(i);
//
//            int width  = childView.getMeasuredWidth();
//            int height = childView.getMeasuredHeight();
//
//            CustomViewGroup.LayoutParams margins = (CustomViewGroup.LayoutParams)(childView.getLayoutParams());
//
//            //ChildView占用的width  = width+leftMargin+rightMargin
//            //ChildView占用的height = height+topMargin+bottomMargin
//            //如果剩余的空间不够，则移到下一行开始位置
//            if( mPainterPosX + width + margins.leftMargin + margins.rightMargin > mViewGroupWidth ) {
//                mPainterPosX = left;
//                mPainterPosY += height + margins.topMargin + margins.bottomMargin;
//            }
//
//            //执行ChildView的绘制
//            childView.layout(mPainterPosX+margins.leftMargin, mPainterPosY+margins.topMargin,mPainterPosX+margins.leftMargin+width, mPainterPosY+margins.topMargin+height);
//
//            mPainterPosX += width + margins.leftMargin + margins.rightMargin;
//        }
//    }

}
