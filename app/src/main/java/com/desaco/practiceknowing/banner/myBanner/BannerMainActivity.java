package com.desaco.practiceknowing.banner.myBanner;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.desaco.practiceknowing.R;
import com.desaco.practiceknowing.banner.myBanner.imageloader.GlideImageLoader;
import com.desaco.practiceknowing.banner.myBanner.imageloader.PicassoImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BannerMainActivity extends Activity {
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_main);

        BannerLayout bannerLayout = (BannerLayout) findViewById(R.id.banner);
        BannerLayout bannerLayout2 = (BannerLayout) findViewById(R.id.banner2);

        //TODO Banner 1，可在RecyclerView中添加此Banner
        final List<String> urls = new ArrayList<>();
        urls.add("http://img3.imgtn.bdimg.com/it/u=2674591031,2960331950&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=3639664762,1380171059&fm=23&gp=0.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=1095909580,3513610062&fm=23&gp=0.jpg");
        urls.add("http://img4.imgtn.bdimg.com/it/u=1030604573,1579640549&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=2583054979,2860372508&fm=23&gp=0.jpg");
        bannerLayout.setImageLoader(new GlideImageLoader());
        bannerLayout.setViewUrls(urls);
        //添加监听事件
        bannerLayout.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(BannerMainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });

        //TODO Banner 2，可在RecyclerView中添加此Banner
        //低于三张
        final List<String> urls2 = new ArrayList<>();
        urls2.add("http://img3.imgtn.bdimg.com/it/u=2674591031,2960331950&fm=23&gp=0.jpg");
        urls2.add("http://img5.imgtn.bdimg.com/it/u=3639664762,1380171059&fm=23&gp=0.jpg");
        bannerLayout2.setImageLoader(new PicassoImageLoader());
        bannerLayout2.setViewUrls(urls2);
    }
}
