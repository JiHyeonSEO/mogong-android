package org.androidtown.alarm_notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by samsung on 2017-09-17.
 */

public class AlarmReceiver extends BroadcastReceiver
{
    private static final int MY_NOTIFICATION_ID = 0;
    NotificationManager notificationManager;
    Notification myNotification;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "Time is set", Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        myNotification = new Notification.Builder(context)
                .setContentTitle("모두의 공강")
                .setContentText("10분전입니다.")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .build();

        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
    }
}
