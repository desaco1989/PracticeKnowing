package com.desaco.practiceknowing.native_method;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
        findViewById(R.id.exception_m1).setOnClickListener(this);
        findViewById(R.id.exception_m2).setOnClickListener(this);

        showEncodeStrTv = (TextView) findViewById(R.id.show_encode_str);
        findViewById(R.id.encode_str).setOnClickListener(this);
        findViewById(R.id.decode_str).setOnClickListener(this);
        //  R.id.encode_str   R.id.decode_str
//        initData();
        cCallJava = new CcallJava(CcallJava_CplusplusCallActivity.this);

        initData2();
    }

    private void initData2() {
        byte[] byteA = new byte[2];
        byteA[0] = 12;
        byteA[1] = 23;
        int len = byteA.length;
        cCallJava.byteArray2JniM1(byteA, len);
        cCallJava.byteArray2JniM2(byteA, len);

        byte[] bytes = cCallJava.jniByte2Java();
        for (int i = 0; i < bytes.length; i++) {
            Log.e("desaco", "bytes[]=" + bytes[i]);
        }

        //Convert back to String
        try {
            Log.e("desaco", "bytes=" + new String(bytes, "UTF-8"));
        } catch (Exception e) {

        }

        //测试JNI 主线程与子线程
//        try {
//            cCallJava.setJNIEnv();
//
//            Thread.sleep(100);
//
//            cCallJava.mainThread();
//        } catch (Exception e) {
//
//        }
    }

    private CcallJava cCallJava;

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
            case R.id.exception_m1:
                exceptionExe("m1");
                break;
            case R.id.exception_m2:
                exceptionExe("m2");
                break;
            default:
                break;
        }
    }

    private void exceptionExe(String method) {
        try {
            if (method.equals("m1")) {
                cCallJava.testExceptionM1();
            } else {
                cCallJava.testExceptionM2();
            }
            System.out.println("程序没有异常，这句话会被打印\n");
        } catch (Exception e) {
            System.out.println("没有捕获到JNI抛出的异常，这句话不会被打印" + e.getMessage() + "\n");
        }
        System.out.println("程序继续执行，这句话会被打印\n");
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
