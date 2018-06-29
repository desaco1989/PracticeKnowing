package com.desaco.practiceknowing.animation;

import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by desaco on 2018/6/29.
 * 深入理解 Android 中的 Matrix- https://blog.csdn.net/d_clock/article/details/80124369
 */

public class SimpleCustomAnimation extends Animation {

    private int mWidth, mHeight;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        this.mWidth = width;
        this.mHeight = height;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        Matrix matrix = t.getMatrix();
        matrix.preScale(interpolatedTime, interpolatedTime);//缩放
        matrix.preRotate(interpolatedTime * 360);//旋转
        //下面的Translate组合是为了将缩放和旋转的基点移动到整个View的中心，不然系统默认是以View的左上角作为基点
        matrix.preTranslate(-mWidth / 2, -mHeight / 2);
        matrix.postTranslate(mWidth / 2, mHeight / 2);
    }
}
