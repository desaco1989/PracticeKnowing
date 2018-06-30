package com.desaco.practiceknowing.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by desaco on 2018/6/30.
 * 编写onMeasure函数
 *
 * 重载了onMeasure函数之后，你会发现，当CustomView使用match_parent的时候，它会占满整个父控件，
 * 而当CustomView使用wrap_content的时候，它的大小则是代码中定义的默认大小100x100像素。
 * 当然，你也可以根据自己的需求改写measureDimension()的实现。
 */

public class CustomView extends View {

    private static final int DEFAULT_VIEW_WIDTH = 100;
    private static final int DEFAULT_VIEW_HEIGHT = 100;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = measureDimension(DEFAULT_VIEW_WIDTH, widthMeasureSpec);
        int height = measureDimension(DEFAULT_VIEW_HEIGHT, heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    protected int measureDimension(int defaultSize, int measureSpec) {

        int result = defaultSize;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        //1. layout给出了确定的值，比如：100dp
        //2. layout使用的是match_parent，但父控件的size已经可以确定了，比如设置的是具体的值或者match_parent
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize; //建议：result直接使用确定值
        }
        //1. layout使用的是wrap_content
        //2. layout使用的是match_parent,但父控件使用的是确定的值或者wrap_content
        else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize); //建议：result不能大于specSize
        }
        //UNSPECIFIED,没有任何限制，所以可以设置任何大小
        //多半出现在自定义的父控件的情况下，期望由自控件自行决定大小
        else {
            result = defaultSize;
        }

        return result;
    }
}
