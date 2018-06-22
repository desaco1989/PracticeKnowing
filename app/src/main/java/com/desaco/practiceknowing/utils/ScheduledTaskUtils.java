package com.desaco.practiceknowing.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;

import com.desaco.practiceknowing.receiver.AlarmReceiver;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by desaco on 2018/6/22.
 * <p>
 * 启动一个Service，在service启动定时器？？
 * <p>
 * 定时器，定时器中断; 定时器任务
 * <p>
 * AlarmManger方式可唤醒cpu甚至实现精确定时，适用于配合service在后台执行一些长期的定时行为。
 * <p>
 * Timer和TimerTask；CountDownTimer；AlarmManager；Handler delayed
 * <p>
 * Handler+Runnable
 * handler.postDelayed(runnable,1000);         // 开始Timer
 * handler.removeCallbacks(runnable);           //停止Timer
 */

public class ScheduledTaskUtils {
    AlarmManager alarmManager;

    public void task(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
//        if (Build.VERSION >= 19) {
//            alarmManager.setWindow(AlarmManager.RTC_WAKEUP, Calendar.getTimeInMillis(),intervalMillis, PendingIntent);
//        } else {
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getTimeInMillis(), intervalMillis, PendingIntent);
//        }
    }

//    CountDownTimer timer = new CountDownTimer(10000, 1000)中，第一个参数表示总时间，第二个参数表示间隔时间。意思就是每隔一秒会回调一次方法onTick，然后10秒之后会回调onFinish方法。


    //Timer和TimerTask  定时器Timer和TimerTask的启动，停止，暂停，继续等操作实例
    private Timer mTimer;
    private TimerTask mTimerTask;

    private void startTimer() {
        if (mTimer == null) {
            mTimer = new Timer();
        }

        if (mTimerTask == null) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    Log.i("desaco", "count: ");

                }
            };
        }

        long delay = 1000l;
        long period = 50;
        if (mTimer != null && mTimerTask != null) {
            mTimer.schedule(mTimerTask, delay, period);
        }
    }

    private void stopTimer() {

        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }

        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

    //AlarmManager
    public void alarm(Context context) {
        AlarmManager manager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        int anHour = 60 * 60 * 1000; // 这是一小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(context, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
    }

    //CountDownTimer定时器
    private CountDownTimer countDownTimer;

    private void startTimerTask() {
        if (countDownTimer == null) {
            countDownTimer = new CountDownTimer(10000000, 5000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    //todo timer Task
                }

                @Override
                public void onFinish() {

                }
            };
        }
        countDownTimer.start();
    }

    private void stopTimerTask() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }


}
