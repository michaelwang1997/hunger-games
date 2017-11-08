package com.example.tom_h.hungergames;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;



public class MainActivity extends AppCompatActivity {
    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sendNotification(View view) {

//Get an instance of NotificationManager//

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.notification_icon)
                        .setContentTitle("HUNGERGAMES HAVE BEGUN")
                        .setContentText("FOUND FOOD NEAR YOU");


        // Gets an instance of the NotificationManager service//

        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//When you issue multiple notifications about the same type of event, it’s best practice for your app to try to update an existing notification with this new information, rather than immediately creating a new notification. If you want to update this notification at a later date, you need to assign it an ID. You can then use this ID whenever you issue a subsequent notification. If the previous notification is still visible, the system will update this existing notification, rather than create a new one. In this example, the notification’s ID is 001//

        //NotificationManager.notify().

                mNotificationManager.notify(001, mBuilder.build());
    }



}
