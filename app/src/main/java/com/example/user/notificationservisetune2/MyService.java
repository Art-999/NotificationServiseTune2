package com.example.user.notificationservisetune2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.IntDef;

import java.util.concurrent.TimeUnit;

import static android.app.Notification.DEFAULT_VIBRATE;

public class MyService extends Service {
    NotificationManager nm;
    public MyService() {
    }

    @Override
    public void onCreate() {
        nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendNotif();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public void sendNotif(){
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("filename","Your message ... ");
        PendingIntent pIntent=PendingIntent.getActivities(this,0, new Intent[]{intent},0);

        Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                + "://" + getPackageName() + "/raw/goodmorning");

        Notification notification=new Notification.Builder(this)
                .setSmallIcon(R.drawable.unnamed)
                .setContentTitle("New message")
                .setContentText("Useful information for you")
                .setContentIntent(pIntent)
                .setDefaults(DEFAULT_VIBRATE)
                .setSound(alarmSound)
                .build();
        nm.notify(1,notification);
        stopSelf();

    }
}
