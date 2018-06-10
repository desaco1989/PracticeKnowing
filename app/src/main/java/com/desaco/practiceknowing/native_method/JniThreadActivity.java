package com.desaco.practiceknowing.native_method;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.desaco.practiceknowing.R;

/**
 * Created by desaco on 2018/6/10.
 * <p>
 * https://blog.csdn.net/panda1234lee/article/details/13503291
 */

public class JniThreadActivity extends Activity {
    //com.desaco.practiceknowing.native_method.JniThreadActivity
    private Button mButton = null;

    static {
        System.loadLibrary("native_exception-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni_thread);

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 调用JNI中的函数来启动JNI中的线程
                mainThread();
            }
        });

        // 初始化JNI环境
        setJNIEnv();

    }

    //由JNI中的线程回调类方法
    private static void fromJNI(int i) {
        Log.v("Java---------->", "" + i);
    }

    //自己定义的线程回调成员方法
    public void From_JNI_Again(int i) {
        Log.v("Java_object", "i=" + i);
    }

    // 本地方法
    private native void mainThread();
    private native void setJNIEnv();
}
