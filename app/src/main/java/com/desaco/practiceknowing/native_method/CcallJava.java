package com.desaco.practiceknowing.native_method;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by desaco on 2018/5/30.
 * <p>
 * https://github.com/maoqitian/CcallJava
 * c 反射Java
 * <p>
 * cd app/src/main/java
 * javah -jni com.desaco.practiceknowing.native_method.JniInvokeJava
 */

public class CcallJava {
    //

    private Context context;

    public CcallJava(Context context) {
        this.context = context;
    }

    static {
        System.loadLibrary("cCallJava-lib");
        System.loadLibrary("native_exception-lib");
    }

    //定义本地方法
    //C调用java空方法
    public native void callbackmethod();

    //C调用java中的带两个int参数的方法
    public native void callbackIntmethod();

    //C调用java中参数为string的方法
    public native void callbackStringmethod();

    //C调用java中静态方法
    public native void callStaticmethod();

    //C调用java空方法
    public void helloFromJava2() {
        Toast.makeText(context, "C调用了java的空方法", Toast.LENGTH_SHORT).show();
    }


    public String key = "desaco";

    //C调用java中的带两个int参数的方法
    public int add(int x, int y) {
        return x + y;
    }


    //C调用java中参数为string的方法
    public void printString(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    //C调用java中静态方法
    public static void staticmethod(String s) {
        Log.w("desaco", s + ",我是被C调用的静态方法");
    }

    //测试JNI异常
    public native void testExceptionM1();

    public native void testExceptionM2();

    //测试java byte[]传给JNI
    public native void byteArray2JniM1(byte[] buffer, int length);

    public native void byteArray2JniM2(byte[] buffer, int length);

    //测试JNI byte[]传给java
    public native byte[] jniByte2Java();

    // JNI 主线程与子线程
//    public native void mainThread();
//    public native void setJNIEnv();

    //private  public
//    public static void helloFromJava(int i) {//TODO
//        Log.e("desaco", "helloFromJava,i=" + i);
//    }

//    public void helloFromJava(){
//        System.out.println("helloFromJava");
//    }

//    private static void fromJNI(int i) {
//        Log.v("Java---------->", "" + i);
//    }

}
