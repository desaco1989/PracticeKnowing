package com.desaco.practiceknowing;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

import com.desaco.practiceknowing.native_method.CcallJava;

public class CcallJavaActivity extends Activity implements View.OnClickListener {
    //

    private CcallJava jni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_call_java);

        jni = new CcallJava(getApplicationContext());
        findViewById(R.id.bt_javaInt).setOnClickListener(this);
        findViewById(R.id.bt_javanull).setOnClickListener(this);
        findViewById(R.id.bt_javaString).setOnClickListener(this);
        findViewById(R.id.bt_static).setOnClickListener(this);

//        initData();
    }

    private void initData() {
        //
        Display display = this.getWindowManager().getDefaultDisplay();
        int nHeight = display.getHeight();      //569
        int nWidth = display.getWidth();        //320

        TextView showTv = (TextView) findViewById(R.id.show_tv);
        String MARK = Build.MANUFACTURER.toLowerCase();
//        showTv.setText(MARK+",,nHeight="+nHeight+",nWidth="+nWidth);
        //获取通话权限是为了保证APP正常运行
        showTv.setText("获取通话权限是为了保证APP正常运行");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_javanull:
                jni.callbackmethod();
                break;
            case R.id.bt_javaInt:
                jni.callbackIntmethod();
                break;
            case R.id.bt_javaString:
                jni.callbackStringmethod();
                break;
            case R.id.bt_static:
                jni.callStaticmethod();
                break;
        }
    }
}
