package com.dentalapp.dentalapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;

/**
 * Created by cristian on 8/4/15.
 */
public class AlarmService extends Service {

    public static String TAG = AlarmService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        /*
        Intent alarmIntent = new Intent(getBaseContext(), AlarmScreen.class);
        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        alarmIntent.putExtras(intent);
        getApplication().startActivity(alarmIntent);
        AlarmManagerHelper.setAlarms(this);
        */

        //String tone = intent.getStringExtra(AlarmManagerHelper.TONE);
        //Uri toneUri = Uri.parse(tone);

        Intent notifIntent = new Intent(getBaseContext(), MainActivity.class);
        notifIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        notifIntent.putExtras(intent);
        //***************************** NOTIFICATIONS **********************************************
        // Now set the notifications:
        // Define an Intent and an action to perform with it by another application
        PendingIntent pIntent = PendingIntent.getActivity(getApplication(), 0, notifIntent, 0);
        // Builds a notification
        Notification.Builder mBuilder = new Notification.Builder(getApplication())
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Alarm")
                .setTicker("Time to brush your teeth!")
                .setContentText("Time to brush your teeth!");
        // Defines the Intent to fire when the notification is clicked
        mBuilder.setContentIntent(pIntent);
        // Set the default notification option
        // DEFAULT_SOUND : Make sound
        // DEFAULT_VIBRATE : Vibrate
        // DEFAULT_LIGHTS : Use the default light notification
        //mBuilder.setSound(toneUri);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        // Auto cancels the notification when clicked on in the task bar
        mBuilder.setAutoCancel(true);

        // Gets a NotificationManager which is used to notify the user of the background event
        NotificationManager mNotificationManager =
                (NotificationManager) getApplication().getSystemService(Context.NOTIFICATION_SERVICE);

        // Post the notification
        mNotificationManager.notify(1, mBuilder.build());


        AlarmManagerHelper.setAlarms(this);
        return super.onStartCommand(intent, flags, startId);
    }





}