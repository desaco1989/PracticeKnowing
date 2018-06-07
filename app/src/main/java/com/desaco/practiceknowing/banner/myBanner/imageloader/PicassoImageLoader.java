package com.desaco.practiceknowing.banner.myBanner.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.desaco.practiceknowing.banner.myBanner.BannerLayout;
import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class PicassoImageLoader implements BannerLayout.ImageLoader {
    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
        Picasso.with(context).load(path).into(imageView);
    }
}
