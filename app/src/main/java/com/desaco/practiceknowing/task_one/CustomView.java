package com.desaco.practiceknowing.task_one;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by desaco on 2018/4/16.
 *
 * https://www.cnblogs.com/androidNot/p/5636024.html
 */

public class CustomView extends View {

    private Paint p;
    private float degrees=0;

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        p = new Paint();
        p.setColor(Color.BLUE);
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.save();
        canvas.translate(200,200);
        canvas.rotate(degrees,50,50);
        canvas.drawRect(0 ,0 ,100 ,100 ,p);
        degrees ++;
        canvas.restore();
        invalidate();//清除并重绘
    }

}
