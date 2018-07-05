package com.desaco.practiceknowing.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.desaco.practiceknowing.receiver.AlarmReceiver;

import java.util.Date;

/**
 * Created by desaco on 2018/6/22.
 * Service启动定时器
 */

public class ScheduledService extends Service {
    private static final String TAG = "ScheduledService";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ScheduledService() {
        super();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

//        serviceIntent = new Intent(MainActivity.this, MyService.class);
//        startService(serviceIntent);
//
//        stopService(serviceIntent);



        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w(TAG, "in onStartCommand");
        Log.w(TAG, "MyService:" + this);
        String name = intent.getStringExtra("name");
        Log.w(TAG, "name:" + name);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("LongRunningService", "executed at " + new Date().
                        toString());
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 60 * 60 * 1000; // 这是一小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
//        int flags = 1;
//        int startId =100;
//        return super.onStartCommand(intent, flags, startId);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w(TAG, "in onDestroy");
    }

}
