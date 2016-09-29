package com.inlight.twoweeks.receiver;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.inlight.twoweeks.MainActivity;
import com.inlight.twoweeks.R;

import java.util.IllegalFormatException;

/**
 * Created by anderspedersen on 28/09/16.
 */

public class AlarmNotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int alarmMinutes = MainActivity.TIME_TRESHOLD_IN_MIN;
        Log.i("test", "Alarm notification received!");

        // Start MainActivity when notificatoin is touced
        PendingIntent alarmintent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        int smallIconId = R.drawable.ic_layers_white_24dp;

        // Get app icon as bitmap
        Bitmap largeIcon;
        try {
            largeIcon = ((BitmapDrawable) context.getPackageManager().getApplicationIcon(context.getPackageName())).getBitmap();
        } catch (PackageManager.NameNotFoundException e) {
            largeIcon = null;
            Log.d("test", "Unable to getInstance app icon because of " + e.getMessage());
            e.printStackTrace();
        }

        Resources r = context.getResources();
        String title = r.getString(R.string.app_name);
        String message = r.getString(R.string.unlock_notification);
        try {
            message = String.format(message);
        } catch (IllegalFormatException e) {
            Log.i("test", "Unable to show number of minutes in alarm notification tooltip");
        }

        // Create lock screen widget
        Notification notification = new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(smallIconId)
                .setLargeIcon(largeIcon)
                .setColor(ContextCompat.getColor(context, R.color.accent))
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(alarmintent)
                .setTicker(message)
                .setAutoCancel(true)
                .setCategory(Notification.CATEGORY_ALARM)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .build();

        NotificationManagerCompat.from(context).notify(0, notification);
    }
}
