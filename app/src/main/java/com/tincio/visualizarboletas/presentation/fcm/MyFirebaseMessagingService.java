package com.tincio.visualizarboletas.presentation.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    private String codigoNoticia;
    private String tituloNoticia;
    private String cuerpoNoticia;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        //if (remoteMessage.getData().size() > 0 && remoteMessage.getNotification() != null) {
      /*  if (remoteMessage.getData().size() > 0 ) {
            codigoNoticia = remoteMessage.getData().get("codigoNoticia");
            tituloNoticia = remoteMessage.getData().get("title");
            cuerpoNoticia = remoteMessage.getData().get("body");

           // sendNotification(codigoNoticia,tituloNoticia,cuerpoNoticia);
            sendUpdateNotification();
        }*/

    }
    @Override
    protected Intent zzae(Intent intent) {
        return null;
    }
}
