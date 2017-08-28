package com.example.hp.navigationwithtab.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.hp.navigationwithtab.R;
import com.example.hp.navigationwithtab.fragments.TopNewsFrgment;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Mahesh on 7/17/2017.
 */

public class MyHandleFirebaseMessage extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String str=remoteMessage.getNotification().getBody();
        Intent intent=new Intent(this, TopNewsFrgment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingInten=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notibuilder=new NotificationCompat.Builder(this);
        notibuilder.setContentTitle("Fcm Notifiction");
        notibuilder.setSmallIcon(R.drawable.download);
        notibuilder.setContentText(remoteMessage.getNotification().getBody());
        notibuilder.setContentIntent(pendingInten);
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notibuilder.build());
    }
}
