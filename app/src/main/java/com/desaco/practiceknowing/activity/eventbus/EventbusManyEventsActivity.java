package com.desaco.practiceknowing.activity.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.desaco.practiceknowing.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by desaco on 2018/10/18.
 */

public class EventbusManyEventsActivity extends Activity implements View.OnClickListener {

    private Button mBtn1, mBtn2, mBtn3, mBtn4;
    private TextView mTextview;

    private String TAG = "desaco";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_manyevents);

        EventBus.getDefault().register(this);

        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn3 = (Button) findViewById(R.id.btn3);
        mBtn4 = (Button) findViewById(R.id.btn4);
        mTextview = (TextView) findViewById(R.id.tv);

        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
        mBtn4.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void loadData(final String url) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                String json = "通过url获取网络数据赋值给json";

                //在子线程当中发送数据给主线程
                EventBus.getDefault().post(json);
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
//                EventBus.getDefault().post(new MainMessage("Hello Donkor"));
                // 2.发送方发送消息 -- 发送MainMessage这个自己定义的对象，可以丰富这个对象，用来传递消息（数据）
                EventBus.getDefault().post(new MainMessage("MainMessage, Hello EventBus"));
                break;
            case R.id.btn2:
//                EventBus.getDefault().post(new BackgroundMessage("Hello Donkor"));
                new Thread() {
                    public void run() {
                        // 2.发送方发送消息 -- 发送AsyncMessage这个自己定义的对象，可以丰富这个对象，用来传递消息（数据）
                        EventBus.getDefault().post(new BackgroundMessage("BackgroundMessage, Hello EventBus"));
                    }
                }.start();
                // 2.发送方发送消息 -- 发送BackgroundMessage这个自己定义的对象，可以丰富这个对象，用来传递消息（数据）
                // 注意，这里是在主线程中发送消息
//                EventBus.getDefault().post(new BackgroundMessage("BackgroundMessage, Hello EventBus"));
                break;
            case R.id.btn3:
//                EventBus.getDefault().post(new AsyncMessage("Hello Donkor"));
                new Thread() {
                    public void run() {
                        // 2.发送方发送消息 -- 发送AsyncMessage这个自己定义的对象，可以丰富这个对象，用来传递消息（数据）
                        EventBus.getDefault().post(new AsyncMessage("AsyncMessage, Hello EventBus"));
                    }
                }.start();
                break;
            case R.id.btn4:
//                EventBus.getDefault().post(new PostingMessage("Hello Donkor"));
                new Thread() {
                    public void run() {
                        // 2.发送方发送消息 -- 发送PostMessage这个自己定义的对象，可以丰富这个对象，用来传递消息（数据）
                        EventBus.getDefault().post(new PostMessage("PostMessage, Hello EventBus"));
                    }
                }.start();
                break;
        }
    }

    //主线程中执行
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(MainMessage msg) {
        mTextview.setText("MAIN主线程中发出：" + msg.getMessage());

        Log.d(TAG, "MAIN, onEventBus() handling message: " + Thread.currentThread().getName() + ",,,msg=" + msg.getMessage());


    }

    //后台线程
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBackgroundEventBus(BackgroundMessage msg) {
//        mTextview.setText("BACKGROUND后台线程中发出：" + msg.getMessage());

        Log.d(TAG, "BackgroundMessage, onEventBusBackground() handling message: " + Thread.currentThread().getName() + ",,,msg=" + msg.getMessage());
    }

    //异步线程
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onAsyncEventBus(AsyncMessage msg) {
//        mTextview.setText("ASYNC异步线程中发出：" + msg.getMessage());

        Log.d(TAG, "AsyncMessage,onEventBusAsync() handling message: " + Thread.currentThread().getName() + ",,,msg=" + msg.getMessage());
    }

    //默认情况，和发送事件在同一个线程
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onPostEventBus(PostMessage msg) {
//        mTextview.setText("POSTING发送事件在同一个线程中发出：" + msg.getMessage());

        Log.d(TAG, "PostMessage,onEventBusPost() handling message: " + Thread.currentThread().getName() + ",,,msg=" + msg.getMessage());
    }

}
