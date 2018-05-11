package com.desaco.practiceknowing;

import android.app.Application;

/**
 * Created by desaco on 2018/5/9.
 */

public class PracticeApplication extends Application {

    private static PracticeApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    public static PracticeApplication getInstance() {
        return instance;
    }

}
