package com.desaco.practiceknowing.task_one;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.desaco.practiceknowing.R;

/**
 * Created by desaco on 2018/4/16.
 * <p>
 * 在 Android 平台绘制一张图片，使用至少 3 种不同的 API，ImageView，SurfaceView，自定义 View
 * https://www.cnblogs.com/zhangyingai/p/7087371.html
 */
public class TaskOneActivity extends Activity {
//
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_one);

        initView();
        initData();
    }

    private void initView() {

    }

    private void initData() {

    }
}
