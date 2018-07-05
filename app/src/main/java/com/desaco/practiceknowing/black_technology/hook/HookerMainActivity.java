package com.desaco.practiceknowing.black_technology.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.desaco.practiceknowing.R;

/**
 * https://blog.csdn.net/u012341052/article/details/71191409
 * hook技术启动未注册的Activity 或判断某个Activity是否已经实例化好了？
 *
 * https://github.com/BolexLiu/AndroidHookStartActivity
 * https://github.com/fourbrother/HookPmsSignature
 */
public class HookerMainActivity extends Activity {

    //com.desaco.practiceknowing.black_technology.hook.HookerMainActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hooker_main);

    }

    public void onClick(View view) {
        startActivity(new Intent(this, HookerMain2Activity.class));
    }
}
