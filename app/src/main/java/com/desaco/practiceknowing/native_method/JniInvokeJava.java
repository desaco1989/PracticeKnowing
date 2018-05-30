package com.desaco.practiceknowing.native_method;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by desaco on 2018/5/30.
 *
 * https://github.com/maoqitian/CcallJava
 *  C++反射Java
 */

public class JniInvokeJava {

    private JniInvokeJava() {

    }

    static {
        System.loadLibrary("jniAttr-lib");
    }

    private static JniInvokeJava singleInstance;

    public static JniInvokeJava getInstance() {
        if (singleInstance == null) {
            synchronized (JniInvokeJava.class) {
                singleInstance = new JniInvokeJava();
                return singleInstance;
            }
        }
        return singleInstance;
    }

    public native void reflectJava(String data);

    public native void reflect();


}
