package com.example.test4_2.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.test4_2.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeService extends Service {
    private static Timer timer = null;
    private NotificationManager manager;
    private NotificationCompat.Builder builder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private void cleanAllNotification() {
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        long period = 60 * 1000;
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                NotificationChannel channel = new NotificationChannel("channel", "閫氱煡", NotificationManager.IMPORTANCE_DEFAULT);
                manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.createNotificationChannel(channel);
                builder = new NotificationCompat.Builder(TimeService.this);
                builder.setContentTitle("鎻愰啋绯荤粺褰撳墠鏃堕棿");
                builder.setSmallIcon(R.drawable.chengzi);
                builder.setChannelId("channel");
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                builder.setContentText("绯荤粺褰撳墠鏃堕棿涓猴細" + formatter.format(date));
                builder.setAutoCancel(true);
                builder.setDefaults(Notification.DEFAULT_ALL);
                Notification notification = builder.build();
                manager.notify(1, notification);

            }
        }, 0, period);

        return super.onStartCommand(intent, flags, startId);
    }
}