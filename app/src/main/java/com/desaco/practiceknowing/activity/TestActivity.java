package com.desaco.practiceknowing.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.desaco.practiceknowing.R;

/**
 * Created by desaco on 2018/10/18.
 */

public class TestActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity_test_layout  activity_layout
        setContentView(R.layout.activity_test_layout);

    }

    /**
     * dp转px
     * @param dp
     */
    public static int dip2px(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }
    /**
     * px转换dip
     */
    public static int px2dip(Context context, int px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
    /**
     * px转换sp
     */
    public static int px2sp(Context context, int pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
    /**
     * sp转换px
     */
    public static int sp2px(Context context, int spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


}
