package com.desaco.practiceknowing.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.desaco.practiceknowing.R;
import com.desaco.practiceknowing.connect_eventbus.MessageEvent;
import com.desaco.practiceknowing.thread_pool_viewpager.ThreadPoolManager2;
import com.desaco.practiceknowing.utils.PictureUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by desaco on 2018/4/16.
 */

public class VideoSubFragment1 extends Fragment {

    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //fragment_layout
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        context = getContext();

        initView(view);
        initData();

        //注册事件
//        EventBus.getDefault().register(this);
        return view;
    }

    private ImageView effectIv;

    private void initView(View view) {
        TextView showTv = (TextView) view.findViewById(R.id.show_tv);
        showTv.setText("first fragment 111.");
        ImageView sourceIv = (ImageView) view.findViewById(R.id.source_img);
        sourceIv.setBackgroundResource(R.drawable.a1);
        effectIv = (ImageView) view.findViewById(R.id.effect_img);

        //excute_runnable
        Button excuteBt = (Button) view.findViewById(R.id.excute_runnable);
        excuteBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发送网络请求或者做些耗时的操作
//                BitmapCallable callable = new BitmapCallable();
//                ThreadPoolManager2.getThreadPoolInstance().futureExcute(callable);

                ThreadPoolManager2.getThreadPoolInstance().execute(subRun);//TODO
                Log.e("desaco", "VideoSubFragment1,onClick");
            }
        });

        //
        ThreadPoolManager2.getThreadPoolInstance().execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    private Handler mHandler;
    private final static int imageview_update_msg = 100;

    private void initData() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case imageview_update_msg:
                        effectIv.setImageBitmap((Bitmap) msg.obj);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent) {
        effectIv.setImageBitmap((Bitmap) messageEvent.getMessage());
    }

    Runnable subRun = new Runnable() {//创建匿名内部类
        @Override
        public void run() {
            Bitmap bitmap = PictureUtils.oldRemeber(context);
            Log.e("desaco", "VideoSubFragment1,excute Runnable,bitmap=" + bitmap);

            //子线程向主线程发送消息
//            Message message = Message.obtain();
//            message.what = imageview_update_msg;
//            message.obj = bitmap;
//            mHandler.sendMessage(message);

            //EventBus发送消息
            EventBus.getDefault().post(new MessageEvent(bitmap,""));
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        ThreadPoolManager2.getThreadPoolInstance().remove(subRun);//TODO
//        EventBus.getDefault().unregister(this);
    }
}
