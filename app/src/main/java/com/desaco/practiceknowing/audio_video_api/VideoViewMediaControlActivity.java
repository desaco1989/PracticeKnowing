package com.desaco.practiceknowing.audio_video_api;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.desaco.practiceknowing.R;

/**
 * Created by desaco on 2018/5/24.
 */

public class VideoViewMediaControlActivity extends Activity {
    //

    private String url3 = "http://qiubai-video.qiushibaike.com/VGU6K0T3CDU6N7JJ_3g.mp4";
    private String url2 = "http://qiubai-video.qiushibaike.com/YXSKWQA6N838MJC4_3g.mp4";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //第一种
        init();

        //第二种
//        setContentView(R.layout.activity_videoview_mediacontrol);
//        initViewAndData();
    }
    private void init(){
        VideoView videoView = new VideoView(this);
        videoView.setVideoURI(Uri.parse(url3));
        videoView.requestFocus();


        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoView);
        mc.setKeepScreenOn(true);


        videoView.setMediaController(mc);

        LinearLayout llMain = new LinearLayout(this);
        llMain.setGravity(Gravity.CENTER_VERTICAL);
        llMain.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        llMain.setLayoutParams(params);

        llMain.addView(videoView, params);
        setContentView(llMain);

    }

    private void initViewAndData() {
        VideoView videoVi = (VideoView) findViewById(R.id.vv_video); ////获取VideoView的id
        String url = "http://ofqeepdd.vod2.hongshiyun.net/target/mp4/2017/10/11/649_ff96ab30af2a438e8d55897e2e9808f4_20_854x480.mp4";
        //设置播放的来源
        videoVi.setVideoPath(url);
        videoVi.requestFocus();
        //实例化媒体控制器
        MediaController mediaController = new MediaController(this);
        mediaController.setMediaPlayer(videoVi);
        videoVi.setMediaController(mediaController);
    }
}
