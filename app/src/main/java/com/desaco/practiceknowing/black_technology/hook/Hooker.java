package com.desaco.practiceknowing.black_technology.hook;

import android.app.Instrumentation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * User  : guanhuan
 * Date  : 2017/5/3
 */

public class Hooker {
    private static final String TAG = "Hooker";

    public static void hookInstrumentation() throws Exception {
        Class<?> activityThread = Class.forName("android.app.ActivityThread");
        Method sCurrentActivityThread = activityThread.getDeclaredMethod("currentActivityThread");
        sCurrentActivityThread.setAccessible(true);

        //获取ActivityThread 对象
        Object activityThreadObject = sCurrentActivityThread.invoke(activityThread);

        //获取 Instrumentation 对象
        Field mInstrumentation = activityThread.getDeclaredField("mInstrumentation");
        mInstrumentation.setAccessible(true);
        Instrumentation instrumentation = (Instrumentation) mInstrumentation.get(activityThreadObject);
        CustomInstrumentation customInstrumentation = new CustomInstrumentation(instrumentation);
        mInstrumentation.set(activityThreadObject, customInstrumentation);
    }


}
