package com.desaco.practiceknowing.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by desaco on 2018/6/4.
 */

public class SubService extends IntentService {
    public SubService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
