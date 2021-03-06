package com.desaco.practiceknowing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.desaco.practiceknowing.native_method.Caculate;
import com.desaco.practiceknowing.native_method.JniInvokeJava;
import com.desaco.practiceknowing.native_method.NativeEncryptDecode;
import com.desaco.practiceknowing.task_five.DrawingGraphicsActivity;
import com.desaco.practiceknowing.task_four.VideoAudioProcessActivity2;
import com.desaco.practiceknowing.task_one.TaskOneActivity;
import com.desaco.practiceknowing.task_three.TextureViewActivity;
import com.desaco.practiceknowing.task_two.AudioRecordAudioTrackActivity;
import com.desaco.practiceknowing.fragment.TestTreadPoolViewPagerActivity;

/**
 * Android 音视频开发入门指南- https://blog.csdn.net/shareus/article/details/79957614
 * cd app/src/main/java
 javah -jni com.desaco.practiceknowing.native_method.JniInvokeJava
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

//        ThreadPoolManager.getThreadPoolInstance().executeRunnable(runnable);
        //show_task_one
        initView();
        initData();
    }

    private void initView() {
        Button taskOneBt = (Button) findViewById(R.id.show_task_one);
        taskOneBt.setOnClickListener(this);

        Button taskTwoBt = (Button) findViewById(R.id.show_task_two);
        taskTwoBt.setOnClickListener(this);

        Button taskThreeBt = (Button) findViewById(R.id.show_task_three);
        taskThreeBt.setOnClickListener(this);

        Button taskFiveBt = (Button) findViewById(R.id.show_task_four);
        taskFiveBt.setOnClickListener(this);
        //show_task_four
        Button fourBt = (Button) findViewById(R.id.show_task_five);
        taskFiveBt.setOnClickListener(this);
        //threadpool_viewpager
        Button threadpool_viewpager = (Button) findViewById(R.id.threadpool_viewpager);
        threadpool_viewpager.setOnClickListener(this);
        //
        Button jniBt = (Button) findViewById(R.id.jni_test);
        jniBt.setOnClickListener(this);
    }

    private void initData() {
        TextView showMsgTv = (TextView) findViewById(R.id.show_msg);
        int add = Caculate.getInstance().add(3, 4);
        int subtract = Caculate.getInstance().subtract(3, 4);
        int multiply = Caculate.getInstance().multiply(3, 4);
        int divide = Caculate.getInstance().divide(3, 8);

        String encrypt = NativeEncryptDecode.getInstance().encrypt("encrypt data");
        String decode = NativeEncryptDecode.getInstance().decode("decode data");

        showMsgTv.setText("四则运算结果：add=" + add + ",subtract=" + subtract + ",multiply=" + multiply + ",divide=" + divide
                + ",encrypt=" + encrypt + ",decode=" + decode);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.show_task_one:
                Intent taskOneIntent = new Intent();
                taskOneIntent.setClass(mContext, TaskOneActivity.class);
                startActivity(taskOneIntent);
                break;
            case R.id.show_task_two:
                Intent taskTwoIntent = new Intent();
                taskTwoIntent.setClass(mContext, AudioRecordAudioTrackActivity.class);
                startActivity(taskTwoIntent);
                break;
            case R.id.show_task_three:
                Intent taskThreeIntent = new Intent();
                // ActivitySurfaceViewCamera  TextureViewActivity
                taskThreeIntent.setClass(mContext, TextureViewActivity.class);
                startActivity(taskThreeIntent);
                break;
            //R.id.show_task_four
            case R.id.show_task_four:
                Intent fourIntent = new Intent();
                // ActivitySurfaceViewCamera  TextureViewActivity  VideoAudioProcessActivity2   VideoAudioProcessActivity
                fourIntent.setClass(mContext, VideoAudioProcessActivity2.class);
                startActivity(fourIntent);
                break;
            case R.id.show_task_five:
                Intent taskFiveIntent = new Intent();
                taskFiveIntent.setClass(mContext, DrawingGraphicsActivity.class);
                startActivity(taskFiveIntent);
                break;
            case R.id.threadpool_viewpager:
                jumpActivty(TestTreadPoolViewPagerActivity.class);
                break;
            case R.id.jni_test:
                JniInvokeJava.getInstance().reflect();
                break;
            default:
                break;
        }
    }

    public void showToastMsg(String msg) {
//        Toast. makeText(this, msg, Toast.LENGTH_LONG).show();//使用这个，可能会报各种空指针异常
        Log.e("desaco", msg);
    }

    private void jumpActivty(Class<?> clazz) {
        Intent taskFiveIntent = new Intent();
        taskFiveIntent.setClass(mContext, clazz);
        startActivity(taskFiveIntent);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i <= 100; i++) {
                i = i + 1;
            }
        }
    };
}
