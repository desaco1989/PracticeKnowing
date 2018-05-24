package com.desaco.practiceknowing.audio_video_api;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.LinearLayout;
import android.widget.MediaController;

import com.desaco.practiceknowing.R;

import java.io.IOException;

/**
 * Created by desaco on 2018/5/24.
 */

public class MediaPlayerMediaControlActivity extends Activity implements MediaPlayer.OnPreparedListener,
        MediaController.MediaPlayerControl {
    private static final String TAG = "AudioPlayer";
    private MediaPlayer mediaPlayer;
    private MediaController mediaController;
    private Handler handler = new Handler();
    private LinearLayout mControllLayout;
    private String url2 = "http://qiubai-video.qiushibaike.com/YXSKWQA6N838MJC4_3g.mp4";

    private Context mContext;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mControllLayout = new LinearLayout(this);
//        setContentView(mControllLayout);
        setContentView(R.layout.surfaceview_layout);
        mContext = this;

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        try {
            //TODO 这里写你的音频路径
            mediaPlayer.setDataSource(url2);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Log.e(TAG, "Could not open file xxx for playback.", e);
        }

        ///-----------------------------------------
        final SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceview_layout_sub);
        surfaceView.setKeepScreenOn(true);
        surfaceView.setFocusable(true);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mediaPlayer.release();
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mediaPlayer.setDisplay(holder);


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
                // TODO Auto-generated method stub

            }
        });

        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.setFixedSize(400, 300);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // the MediaController will hide after 3 seconds - tap the screen to
        // make it appear again
        mediaController.show();
        return false;
    }

    // --MediaPlayerControl
    // methods----------------------------------------------------
    public void start() {
        mediaPlayer.start();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    public void seekTo(int i) {
        mediaPlayer.seekTo(i);
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public int getBufferPercentage() {
        return 0;
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onPrepared");
        mediaController = new MediaController(this);
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(mControllLayout);
        mediaController.setEnabled(true);
        handler.post(new Runnable() {
            public void run() {
                mediaController.show();
            }
        });
    }

    @Override
    public int getAudioSessionId() {
        // TODO Auto-generated method stub
        return 0;
    }
}
