package com.desaco.practiceknowing.native_method;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

import com.desaco.practiceknowing.R;
import com.desaco.practiceknowing.native_method.CcallJava;
import com.desaco.practiceknowing.native_method.NativeEncryptDecode;

public class CcallJava_CplusplusCallActivity extends Activity implements View.OnClickListener {
    //

    private CcallJava jni;
    private TextView showEncodeStrTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_call_java);

        jni = new CcallJava(getApplicationContext());
        findViewById(R.id.bt_javaInt).setOnClickListener(this);
        findViewById(R.id.bt_javanull).setOnClickListener(this);
        findViewById(R.id.bt_javaString).setOnClickListener(this);
        findViewById(R.id.bt_static).setOnClickListener(this);

        showEncodeStrTv = (TextView) findViewById(R.id.show_encode_str);
        findViewById(R.id.encode_str).setOnClickListener(this);
        findViewById(R.id.decode_str).setOnClickListener(this);
        //  R.id.encode_str   R.id.decode_str
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
                break;//  R.id.encode_str   R.id.decode_str
            case R.id.encode_str:
                String str = "abcdefg";
                String encodeStr = NativeEncryptDecode.getInstance().decodeWithC(str, str.length());
                showEncodeStrTv.setText(encodeStr);
                break;
            case R.id.decode_str:
                String encode = showEncodeStrTv.getText().toString();
                String decode = NativeEncryptDecode.getInstance().decodeWithC(encode, encode.length());
                showEncodeStrTv.setText(decode);
                break;
        }
    }

    private void strEncode() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("balalabala巴啦啦小魔仙01");
            String utf8 = new String(sb.toString().getBytes("UTF-8"));
            String unicode = new String(utf8.getBytes(), "UTF-8");
            String gbk = new String(unicode.getBytes("GB2312"));
        } catch (Exception e) {
        }
    }
}
