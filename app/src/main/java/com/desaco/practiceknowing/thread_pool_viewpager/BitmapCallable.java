package com.desaco.practiceknowing.thread_pool_viewpager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.desaco.practiceknowing.utils.PictureUtils;

import java.util.concurrent.Callable;

/**
 * Created by desaco on 2018/5/9.
 */

public class BitmapCallable implements Callable {
    @Override
    public Object call() throws Exception {
//        return PictureUtils.filmFilter();
        return  null;
    }
}
