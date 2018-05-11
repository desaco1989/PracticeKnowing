package com.desaco.practiceknowing.task_two;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.desaco.practiceknowing.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by desaco on 2018/4/16.
 * <p>
 * AudioRecord 完成音频 PCM 数据的采集.录音
 * AudioTrack API 完成音频 PCM 数据的播放
 * <p>
 * https://blog.csdn.net/i_do_can/article/details/53869875
 * <p>
 * MediaRecorder  AAC和ARM用得比较多，环信使用的就是ARM的编码方式。
 *
 * 录音文件之前直接保存的是 .pcm格式
 */

public class AudioRecordAudioTrackActivity extends Activity implements View.OnClickListener {

    private final int REQ_PERMISSION_AUDIO = 0x01;
    private Button mRecode, mPlay,mPcm2WavBt;
    private File mAudioFile = null;
    private Thread mCaptureThread = null;
    private boolean mIsRecording, mIsPlaying;
    private int mFrequence = 44100;
    private int mChannelConfig = AudioFormat.CHANNEL_IN_MONO;
    private int mPlayChannelConfig = AudioFormat.CHANNEL_IN_STEREO;
    private int mAudioEncoding = AudioFormat.ENCODING_PCM_16BIT;

    private PlayTask mPlayer;
    private RecordTask mRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_recorder_audio);

        mRecode = (Button) findViewById(R.id.record_audio);
        mPlay = (Button) findViewById(R.id.play_audio);
        mPcm2WavBt = (Button)findViewById(R.id.pcm_2_wav);
        //

        mRecode.setText("recode");
        mPlay.setText("play");

        mPlay.setEnabled(false);

        mRecode.setOnClickListener(this);
        mPlay.setOnClickListener(this);
        mPcm2WavBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.record_audio:
                if (mRecode.getTag() == null) {
                    startAudioRecode();
                } else {
                    stopAudioRecode();
                }
                break;
            case R.id.play_audio:
                if (mPlay.getTag() == null) {
                    startAudioPlay();
                } else {
                    stopAudioPlay();
                }
                break;
            case R.id.pcm_2_wav:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //TODO
                        String sourcePath = null;
                        if(mAudioFile != null){
                             sourcePath = mAudioFile.toString();
                        }else{
                            File appDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/a_rtmp_video/record_audio.pcm");
                        }

                        String desPath = createFile("wav");
                        PcmToWavUtil p2W = new PcmToWavUtil();
                        p2W.pcmToWav(sourcePath,desPath);
                    }
                }).start();
                break;
        }
    }

    public String createFile(String audioType) {
        if (!checkSDCard()) {
            return null;
        }
        //
        File appDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/a_rtmp_video/");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
//        File dir = new File(appDir, formatCurrentDate("yyyy_MM_dd_HH_mm_ss") + ".mp4");//TODO
        File dir = new File(appDir,  "record_audio."+audioType);
        if (!dir.exists()) {
            try {
                if (!dir.getParentFile().exists()) {
                    dir.getParentFile().mkdirs();
                }
                if (!dir.exists()) {
                    dir.createNewFile();
                }
                return dir.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return dir.toString();
        }

    }

    public boolean checkSDCard() {
        String flag = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(flag)) {
            return true;
        }
        return false;
    }

    public String formatCurrentDate(String pattern) {
        long time = System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();
        //"yyyy-MM-dd HH:mm:ss"
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date curDate = new Date(time);
        return format.format(curDate);
    }

    private void startAudioRecode() {
        if (checkPermission()) {
            PackageManager packageManager = this.getPackageManager();
            if (!packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
                showToast("This device doesn't have a mic!");
                return;
            }
            mRecode.setTag(this);
            mRecode.setText("stop");
            mPlay.setEnabled(false);

            String path = createFile("pcm");
            mAudioFile =  new File(path);

//            File fpath = new File(Environment.getExternalStorageDirectory()
//                    .getAbsolutePath() + "/a_rtmp_video");
//            fpath.mkdirs();// 创建文件夹
//            try {
//                // 创建临时文件,注意这里的格式为.pcm
//                mAudioFile = File.createTempFile("record_audio", ".pcm", fpath);
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }

            mRecorder = new RecordTask();
            mRecorder.execute();

            showToast("Recording started");

        } else {
            requestPermission();
        }
    }

    private void stopAudioRecode() {
        mIsRecording = false;
        mRecode.setTag(null);
        mRecode.setText("recode");
        mPlay.setEnabled(true);
        showToast("Recording Completed");
    }

    private void startAudioPlay() {
        mPlay.setTag(this);
        mPlay.setText("stop");
        mPlayer = new PlayTask();
        mPlayer.execute();
        showToast("Recording Playing");
    }

    private void stopAudioPlay() {
        mIsPlaying = false;
        mPlay.setTag(null);
        mPlay.setText("play");
    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, REQ_PERMISSION_AUDIO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQ_PERMISSION_AUDIO:
                if (grantResults.length > 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        showToast("Permission Granted");
                    } else {
                        showToast("Permission  Denied");
                    }
                }
                break;
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * AudioRecord录制音频
     */
    class RecordTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... arg0) {
            mIsRecording = true;
            try {
                // 开通输出流到指定的文件
                DataOutputStream dos = new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(mAudioFile)));
                // 根据定义好的几个配置，来获取合适的缓冲大小
                int bufferSize = AudioRecord.getMinBufferSize(mFrequence,
                        mChannelConfig, mAudioEncoding);
                // 实例化AudioRecord
                AudioRecord record = new AudioRecord(
                        MediaRecorder.AudioSource.MIC, mFrequence,
                        mChannelConfig, mAudioEncoding, bufferSize);
                // 定义缓冲
                short[] buffer = new short[bufferSize];
                // 开始录制
                record.startRecording();
                int r = 0; // 存储录制进度
                // 定义循环，根据isRecording的值来判断是否继续录制
                while (mIsRecording) {
                    // 从bufferSize中读取字节，返回读取的short个数
                    int bufferReadResult = record
                            .read(buffer, 0, buffer.length);
                    // 循环将buffer中的音频数据写入到OutputStream中
                    for (int i = 0; i < bufferReadResult; i++) {
                        dos.writeShort(buffer[i]);
                    }
                    publishProgress(new Integer(r)); // 向UI线程报告当前进度
                    r++; // 自增进度值
                }
                // 录制结束
                record.stop();
                Log.i("slack", "::" + mAudioFile.length());
                dos.close();
            } catch (Exception e) {
                // TODO: handle exception
                Log.e("slack", "::" + e.getMessage());
            }
            return null;
        }

        // 当在上面方法中调用publishProgress时，该方法触发,该方法在UI线程中被执行
        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Void result) {
        }
    }

    /**
     * AudioTrack,AudioTrack播放音频
     */
    class PlayTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... arg0) {
            mIsPlaying = true;
            int bufferSize = AudioTrack.getMinBufferSize(mFrequence,
                    mPlayChannelConfig, mAudioEncoding);
            short[] buffer = new short[bufferSize];
            try {
                // 定义输入流，将音频写入到AudioTrack类中，实现播放
                DataInputStream dis = new DataInputStream(
                        new BufferedInputStream(new FileInputStream(mAudioFile)));
                // 实例AudioTrack
                AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC,
                        mFrequence,
                        mPlayChannelConfig, mAudioEncoding, bufferSize,
                        AudioTrack.MODE_STREAM);
                // 开始播放
                track.play();
                // 由于AudioTrack播放的是流，所以，我们需要一边播放一边读取
                while (mIsPlaying && dis.available() > 0) {
                    int i = 0;
                    while (dis.available() > 0 && i < buffer.length) {
                        buffer[i] = dis.readShort();
                        i++;
                    }
                    // 然后将数据写入到AudioTrack中
                    track.write(buffer, 0, buffer.length);
                }
                // 播放结束
                track.stop();
                dis.close();
            } catch (Exception e) {
                // TODO: handle exception
                Log.e("slack", "error:" + e.getMessage());
            }
            return null;
        }

        protected void onPostExecute(Void result) {
        }

        protected void onPreExecute() {
        }

    }
}
