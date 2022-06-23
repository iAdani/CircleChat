package com.example.circlechat.api;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.circlechat.ChatActivity;
import com.example.circlechat.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseNotificationService extends FirebaseMessagingService {
    public FirebaseNotificationService() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        // if notification channel is not created, create it
        createNotificationChannel();
        // create notification
        if (remoteMessage.getNotification() != null) {
            // gets sender's username
            String sender = remoteMessage.getData().get("sender");
            // create intent to open chat activity
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("sender", sender);
            // create pending intent
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent);
            // display notification
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(R.string.incoming_msg_channel_id, builder.build());
        }
    }

    /**
     * Creates notification channel for Android Oreo and above (if not already created)
     */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (getSystemService(NotificationManager.class)
                    .getNotificationChannel(getString(R.string.channel_id)) == null) {
                NotificationChannel channel = new NotificationChannel(getString(R.string.incoming_msg_channel_id),
                        "New Message",
                        NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription("New Message");
                NotificationManager manager = getSystemService(NotificationManager.class);
                manager.createNotificationChannel(channel);
            }
        }
    }
}