package com.desaco.practiceknowing.black_technology.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;

/**
 * User  : guanhuan
 * Date  : 2017/5/3
 */

public class CustomInstrumentation extends Instrumentation{

    private Instrumentation base;

    public CustomInstrumentation(Instrumentation base) {
        this.base = base;
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Log.e("TAG", "invoked  CustomInstrumentation#newActivity, " + "class name =" + className + ", intent = " + intent);
        return super.newActivity(cl, HookerMain2Activity.class.getName(), intent);
    }
}
