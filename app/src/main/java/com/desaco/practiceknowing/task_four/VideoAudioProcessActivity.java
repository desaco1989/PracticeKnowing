package com.desaco.practiceknowing.task_four;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.desaco.practiceknowing.R;

/**
 * Created by desaco on 2018/4/22.
 *
 *
 */

public class VideoAudioProcessActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_audio_process);

        initView();
        initData();
    }
    private void initView(){
        //
        Button fenliBt = (Button)findViewById(R.id.fenli_audio_video);
        fenliBt.setOnClickListener(this);
        //
        Button hebingBt = (Button)findViewById(R.id.hebing_audio_video);
        hebingBt.setOnClickListener(this);
    }
    private void initData(){

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fenli_audio_video:
                new Thread(new Runnable() {//Thread thread =
                    @Override
                    public void run() {
                        VideoSynthesisSeparation.exactorMedia();
                        Log.e("desaco","create video finish ");
                    }
                }).start();
                break;
            case R.id.hebing_audio_video:
                Thread thread2 = new Thread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
                break;
            default:
                break;
        }
    }
}
