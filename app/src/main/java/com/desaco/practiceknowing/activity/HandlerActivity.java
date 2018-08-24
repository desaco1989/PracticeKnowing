package com.desaco.practiceknowing.activity;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

/**
 * Created by desaco on 2018/8/25.
 */

public class HandlerActivity  extends Activity {

    private void initData(){
        Handler handler = new Handler();
        Message msg = handler.obtainMessage();
        msg.arg1 = 100;
        msg.sendToTarget();
    }
}
