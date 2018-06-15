package com.desaco.practiceknowing.broadcastReceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.desaco.practiceknowing.MainActivity;
import com.desaco.practiceknowing.R;

/**
 * Created by desaco on 2018/6/15.
 * android 广播接收器之通知栏页面跳转
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static int NOTIFY_ID = 1000;
    private static final String tag = "NotificationReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Boot Complete", Toast.LENGTH_SHORT).show();
        //推送消息
        sendNotification(context, "12345");
    }

    private void sendNotification(Context context, String mas) {
        //【1】获取Notification 管理器的参考
        NotificationManager notifyMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //【2】设置通知。PendingIntent表示延后触发，是在用户下来状态栏并点击通知时触发，触发时PendingIntent发送intent，打开到指定页面。
        Intent intent = new Intent(context, MainActivity.class);
        // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // context.startActivity(intent );
        // intent.setClassName(this,MainActivity.class);
        //intent.setComponent(new ComponentName(this, MainActivity.class));  //方法4
        // 主要设置点击通知的时显示内容的类
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Hello")
                .setContentTitle("Title")
                .setContentText("Content text")
                .setContentIntent(pi)
                .build();
        //点击后删除，如果是FLAG_NO_CLEAR则不删除，FLAG_ONGOING_EVENT用于某事正在进行，例如电话，具体查看参考。
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        //【3】发送通知到通知管理器。第一个参数是这个通知的唯一标识，通过这个id可以在以后cancel通知，
        // 更新通知（发送一个具有相同id的新通知）。这个id在应用中应该是唯一的。
        notifyMgr.notify(NOTIFY_ID, notification);
    }
}
