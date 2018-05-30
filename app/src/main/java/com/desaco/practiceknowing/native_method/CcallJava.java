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
    public void helloFromJava() {
        Toast.makeText(context, "C调用了java的空方法", Toast.LENGTH_SHORT).show();
    }

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

}
