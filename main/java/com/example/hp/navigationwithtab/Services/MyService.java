package com.example.hp.navigationwithtab.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.hp.navigationwithtab.R;

import java.text.SimpleDateFormat;

/**
 * Created by Mahesh on 6/5/2017.
 */

public class MyService extends Service {
    NotificationManager mnotifictionmanager;
    MediaPlayer mp;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        long date=System.currentTimeMillis();
        SimpleDateFormat sdf=new SimpleDateFormat("hh-mm");
        String datetime=sdf.format(date);
        Toast.makeText(this, "Current Time"+ datetime, Toast.LENGTH_LONG).show();
           /*while(datetime.matches("08-48"))
           {mnotifictionmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(this);
            builder.setContentTitle("News Alert");
            builder.setSmallIcon(R.mipmap.ic_launcher);
            Notification notifiction=builder.build();
            mnotifictionmanager.notify(100,notifiction);}*/
           mp=MediaPlayer.create(this,Settings.System.DEFAULT_RINGTONE_URI);
           mp.setLooping(true);
           mp.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
