package com.desaco.practiceknowing.task_four;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.util.Log;

import com.desaco.practiceknowing.utils.CommonUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by desaco on 2018/4/18.
 * Android 视频分离和合成(MediaMuxer和MediaExtractor)
 * https://blog.csdn.net/zhi184816/article/details/52514138
 * <p>
 * https://github.com/RrtoyewxXu/AndroidLiveRecord/tree/master/mediaexactorandmediamuxerdemo
 */

public class VideoSynthesisSeparation {
    private VideoSynthesisSeparation(){}

    private String SDCARD_PATH = "";
    private MediaExtractor mediaExtractor;

    /**
     * 将视频多信道的分离出来，比如声音和图像分隔出来，可以做到二次合成。
     */
    public static void exactorMedia() {// /mnt/sdcard/
        MediaExtractor mediaExtractor = new MediaExtractor();
        FileOutputStream videoOutputStream = null;
        FileOutputStream audioOutputStream = null;
        try {
            //分离的视频文件  separate  a_rtmp_video  vr1.mp4
            String outputVideo = CommonUtils.createFolderAndFilePath("a_rtmp_video/","output_video.mp4");
            File videoFile = new File(outputVideo);
//            File videoFile = new File(SDCARD_PATH, "output_video.mp4");
            //分离的音频文件 combine
            String outputaudio = CommonUtils.createFolderAndFilePath("a_rtmp_video/","output_audio");
            File audioFile = new File(outputaudio);
//            File audioFile = new File(SDCARD_PATH, "output_audio");
            videoOutputStream = new FileOutputStream(videoFile);
            audioOutputStream = new FileOutputStream(audioFile);
            //源文件
//            mediaExtractor =
            //TODO
            //Environment.getExternalStorageState().toString() +
            mediaExtractor.setDataSource("/mnt/sdcard/a_rtmp_video/vr1.mp4");
            //信道总数
            int trackCount = mediaExtractor.getTrackCount();
            int audioTrackIndex = -1;
            int videoTrackIndex = -1;
            for (int i = 0; i < trackCount; i++) {
                MediaFormat trackFormat = mediaExtractor.getTrackFormat(i);
                String mineType = trackFormat.getString(MediaFormat.KEY_MIME);
                //视频信道
                if (mineType.startsWith("video/")) {
                    videoTrackIndex = i;
                }
                //音频信道
                if (mineType.startsWith("audio/")) {
                    audioTrackIndex = i;
                }
            }

            ByteBuffer byteBuffer = ByteBuffer.allocate(500 * 1024);
            //切换到视频信道
            mediaExtractor.selectTrack(videoTrackIndex);
            Log.e("desaco","create video sperate start");
            while (true) {
                int readSampleCount = mediaExtractor.readSampleData(byteBuffer, 0);
                if (readSampleCount < 0) {
                    break;
                }
                //保存视频信道信息
                byte[] buffer = new byte[readSampleCount];
                byteBuffer.get(buffer);
                videoOutputStream.write(buffer);
                byteBuffer.clear();
                mediaExtractor.advance();
            }
            //切换到音频信道
            mediaExtractor.selectTrack(audioTrackIndex);
            while (true) {
                int readSampleCount = mediaExtractor.readSampleData(byteBuffer, 0);
                if (readSampleCount < 0) {
                    break;
                }
                //保存音频信息
                byte[] buffer = new byte[readSampleCount];
                byteBuffer.get(buffer);
                audioOutputStream.write(buffer);
                byteBuffer.clear();
                mediaExtractor.advance();
            }
            Log.e("desaco","create video sperate success ");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("desaco","create video sperate fail");
        } finally {
            mediaExtractor.release();
            try {
                videoOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * https://www.jianshu.com/p/66acab100e4b
     * 分离容器中的视频track和音频track
     * @throws IOException
     */
    private void muxingAudioAndVideo() throws IOException {
        String mOutputVideoPath = null;//TODO
        MediaMuxer mMediaMuxer = new MediaMuxer(mOutputVideoPath,
                MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);

        // 视频的MediaExtractor
        MediaExtractor mVideoExtractor = new MediaExtractor();
        String mVideoPath = null;//TODO
        mVideoExtractor.setDataSource(mVideoPath);
        int videoTrackIndex = -1;
        for (int i = 0; i < mVideoExtractor.getTrackCount(); i++) {
            MediaFormat format = mVideoExtractor.getTrackFormat(i);
            if (format.getString(MediaFormat.KEY_MIME).startsWith("video/")) {
                mVideoExtractor.selectTrack(i);
                videoTrackIndex = mMediaMuxer.addTrack(format);
                break;
            }
        }

        // 音频的MediaExtractor
        MediaExtractor mAudioExtractor = new MediaExtractor();
        String mAudioPath = null;//TODO
        mAudioExtractor.setDataSource(mAudioPath);
        int audioTrackIndex = -1;
        for (int i = 0; i < mAudioExtractor.getTrackCount(); i++) {
            MediaFormat format = mAudioExtractor.getTrackFormat(i);
            if (format.getString(MediaFormat.KEY_MIME).startsWith("audio/")) {
                mAudioExtractor.selectTrack(i);
                audioTrackIndex = mMediaMuxer.addTrack(format);
            }
        }

        // 添加完所有轨道后start
        mMediaMuxer.start();

        // 封装视频track
        if (-1 != videoTrackIndex) {
            MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
            info.presentationTimeUs = 0;
            ByteBuffer buffer = ByteBuffer.allocate(100 * 1024);
            while (true) {
                int sampleSize = mVideoExtractor.readSampleData(buffer, 0);
                if (sampleSize < 0) {
                    break;
                }

                info.offset = 0;
                info.size = sampleSize;
                info.flags = MediaCodec.BUFFER_FLAG_SYNC_FRAME;
                info.presentationTimeUs = mVideoExtractor.getSampleTime();
                mMediaMuxer.writeSampleData(videoTrackIndex, buffer, info);

                mVideoExtractor.advance();
            }
        }

        // 封装音频track
        if (-1 != audioTrackIndex) {
            MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
            info.presentationTimeUs = 0;
            ByteBuffer buffer = ByteBuffer.allocate(100 * 1024);
            while (true) {
                int sampleSize = mAudioExtractor.readSampleData(buffer, 0);
                if (sampleSize < 0) {
                    break;
                }

                info.offset = 0;
                info.size = sampleSize;
                info.flags = MediaCodec.BUFFER_FLAG_SYNC_FRAME;
                info.presentationTimeUs = mAudioExtractor.getSampleTime();
                mMediaMuxer.writeSampleData(audioTrackIndex, buffer, info);

                mAudioExtractor.advance();
            }
        }

        // 释放MediaExtractor
        mVideoExtractor.release();
        mAudioExtractor.release();

        // 释放MediaMuxer
        mMediaMuxer.stop();
        mMediaMuxer.release();
    }
}
