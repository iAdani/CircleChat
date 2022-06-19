package com.example.circlechat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseNotificationService extends FirebaseMessagingService {
    public FirebaseNotificationService() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        if (remoteMessage.getNotification() != null) {

            createNotificationChannel();


            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(1, builder.build());
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1", "New Message", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("New Message");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}