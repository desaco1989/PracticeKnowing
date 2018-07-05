package com.desaco.practiceknowing;

import android.app.Application;

import com.desaco.practiceknowing.black_technology.hook.Hooker;

/**
 * Created by desaco on 2018/5/9.
 */

public class PracticeApplication extends Application {

    private static PracticeApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        try {
            Hooker.hookInstrumentation();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static PracticeApplication getInstance() {
        return instance;
    }

}
