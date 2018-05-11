package com.desaco.practiceknowing.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.desaco.practiceknowing.PracticeApplication;
import com.desaco.practiceknowing.R;

/**
 * Created by desaco on 2018/5/9.
 */

public class PictureUtils {

    private PictureUtils() {
    }

    /**
     * 怀旧效果(相对之前做了优化快一倍) ,算法如下：
     * R = 0.393r+0.769g+0.189b
     * G = 0.349r+0.686g+0.168b
     * B = 0.272r+0.534g+0.131b
     *
     * @return
     */
    public static Bitmap oldRemeber(Context context) {
        // 速度测试
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.a1);
        long start = System.currentTimeMillis();
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        int pixColor = 0;
        int pixR = 0;
        int pixG = 0;
        int pixB = 0;
        int newR = 0;
        int newG = 0;
        int newB = 0;
        int[] pixels = new int[width * height];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                pixColor = pixels[width * i + k];
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                newR = (int) (0.393 * pixR + 0.769 * pixG + 0.189 * pixB);
                newG = (int) (0.349 * pixR + 0.686 * pixG + 0.168 * pixB);
                newB = (int) (0.272 * pixR + 0.534 * pixG + 0.131 * pixB);
                int newColor = Color.argb(255, newR > 255 ? 255 : newR, newG > 255 ? 255 : newG, newB > 255 ? 255 : newB);
                pixels[width * i + k] = newColor;
            }
        }

        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        long end = System.currentTimeMillis();
        return bitmap;
    }

    /**
     * 胶片滤镜特效
     * <p>
     * <p>
     * filmFilter(Bitmap bitmap)
     */
    public static Bitmap filmFilter(Context context) {

//        Bitmap bitmap = BitmapFactory.decodeResource(PracticeApplication.getInstance().getResources(), R.drawable.a1);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.a1, options);

        int pixelsR, pixelsG, pixelsB, pixelsA;
        int color;
//
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] oldPixels = new int[width * height];
        int[] newPixels = new int[width * height];

        // 获取像素
        bitmap.getPixels(oldPixels, 0, width, 0, 0, width, height);

        for (int i = 1; i < height * width; i++) {
            color = oldPixels[i];
            // 获取RGB分量
            pixelsA = Color.alpha(color);
            pixelsR = Color.red(color);
            pixelsG = Color.green(color);
            pixelsB = Color.blue(color);

            // 转换
            pixelsR = (255 - pixelsR);
            pixelsG = (255 - pixelsG);
            pixelsB = (255 - pixelsB);
            // 均小于等于255大于等于0
            if (pixelsR > 255) {
                pixelsR = 255;
            } else if (pixelsR < 0) {
                pixelsR = 0;
            }
            if (pixelsG > 255) {
                pixelsG = 255;
            } else if (pixelsG < 0) {
                pixelsG = 0;
            }
            if (pixelsB > 255) {
                pixelsB = 255;
            } else if (pixelsB < 0) {
                pixelsB = 0;
            }
            // 根据新的RGB生成新像素
            newPixels[i] = Color.argb(pixelsA, pixelsR, pixelsG, pixelsB);
        }
        // 根据新像素生成新图片
        bitmap.setPixels(newPixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
