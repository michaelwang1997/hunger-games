package com.example.tom_h.hungergames;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


//this shouldn't extend from AppCOmpatACtivity TODO: this comment
public class FoodDataManager {

    public FirebaseStorage storage;
    public StorageReference storageRef;
    public FirebaseDatabase database;
    public DatabaseReference mDatabase;
    public DatabaseReference eventsRef;
    public static List<Event> events = new ArrayList();

    public void createEvent(Event event) {
        Log.d("create Event", "Creating an Event");
        Log.d("create Event", "what is " + event.title);
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();
        mDatabase.child("events").child(Integer.toString(event.ID)).setValue(event);

    }


    public void CreateNotification(Event event) {

//Get an instance of NotificationManager//
        try{

            Notification.Builder mBuilder =
                    new Notification.Builder(NavActivity.navContext)
                            .setSmallIcon(R.mipmap.notification_icon)
                            .setContentTitle(event.title.toString())
                            .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                            .setContentText(event.description.toString());


            // Gets an instance of the NotificationManager service//

            NotificationManager mNotificationManager =

                    (NotificationManager) NavActivity.navContext.getSystemService(NavActivity.navContext.NOTIFICATION_SERVICE);

//When you issue multiple notifications about the same type of event, it’s best practice for your app to try to update an existing notification with this new information, rather than immediately creating a new notification. If you want to update this notification at a later date, you need to assign it an ID. You can then use this ID whenever you issue a subsequent notification. If the previous notification is still visible, the system will update this existing notification, rather than create a new one. In this example, the notification’s ID is 001//

            //NotificationManager.notify().

            mNotificationManager.notify(001, mBuilder.build());
        }
        catch(Exception e){
            Log.d("EXCEPTION e", "Exceptop e : "+ e.toString());
        }


    }

    public void uploadImage(){
        Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
        StorageReference riversRef = storageRef.child("images/"+file.getLastPathSegment());
        UploadTask uploadTask = riversRef.putFile(file);

// Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
            }
        });
    }

    public void downloadImage (){
        StorageReference islandRef = storageRef.child("images/island.jpg");

        File localFile;
        try{
            localFile = File.createTempFile("images", "jpg");

        }catch (Exception e){
            //TODO ERROR CATCHING
        }

        islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                // Local temp file has been created
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }

    public FoodDataManager(){
        //super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        mDatabase = database.getReference();
        //write to database
//        mDatabase.child("events").child("EventID").setValue(CHILDEVENT);
//        //YOu update event in the following mannor:
//        //mDatabase.child("users").child(userId).child("username").setValue(name);
//        mDatabase.setValue("Hello, world"); //basic setValue
//        // Read from the database
        eventsRef = database.getReference("events");
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d("DATABASE", "onChildAdded:" + dataSnapshot.getKey());
                Log.d("DATABASE", "onChildAdded:" + dataSnapshot.getValue());
                Event event = dataSnapshot.getValue(Event.class);
                CreateNotification(event);
                events.add(event);

                // A new comment has been added, add it to the displayed list
                //Comment comment = dataSnapshot.getValue(Comment.class);

                // ...
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
//                Log.d("DATABASE", "onChildChanged:" + dataSnapshot.getKey());
//
//                // A comment has changed, use the key to determine if we are displaying this
//                // comment and if so displayed the changed comment.
//                //Comment newComment = dataSnapshot.getValue(Comment.class);
//               // String commentKey = dataSnapshot.getKey();
//
//                // ...
//            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("DATABASE", "onChildRemoved:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so remove it.
                //String commentKey = dataSnapshot.getKey();
                Event event = dataSnapshot.getValue(Event.class);
                for (Event i: events){
                    if(i.ID == event.ID){
                        i.removeMarker();
                    }

                }
                events.remove(dataSnapshot.getValue(Event.class));
                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        eventsRef.addChildEventListener(childEventListener);
    }
//    @Override
//    public void onCreate(Bundle savedInstanceState){
//        //super.onCreate(savedInstanceState);
//        database = FirebaseDatabase.getInstance();
//        mDatabase = database.getReference();
//        //write to database
////        mDatabase.child("events").child("EventID").setValue(CHILDEVENT);
////        //YOu update event in the following mannor:
////        //mDatabase.child("users").child(userId).child("username").setValue(name);
////        mDatabase.setValue("Hello, world"); //basic setValue
////        // Read from the database
//        eventsRef = database.getReference("events");
//        ChildEventListener childEventListener = new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
//                Log.d("DATABASE", "onChildAdded:" + dataSnapshot.getKey());
//                Log.d("DATABASE", "onChildAdded:" + dataSnapshot.getValue());
//                Event event = dataSnapshot.getValue(Event.class);
//                CreateNotification(event);
//                events.add(event);
//
//                // A new comment has been added, add it to the displayed list
//                //Comment comment = dataSnapshot.getValue(Comment.class);
//
//                // ...
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
////            @Override
////            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
////                Log.d("DATABASE", "onChildChanged:" + dataSnapshot.getKey());
////
////                // A comment has changed, use the key to determine if we are displaying this
////                // comment and if so displayed the changed comment.
////                //Comment newComment = dataSnapshot.getValue(Comment.class);
////               // String commentKey = dataSnapshot.getKey();
////
////                // ...
////            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                Log.d("DATABASE", "onChildRemoved:" + dataSnapshot.getKey());
//
//                // A comment has changed, use the key to determine if we are displaying this
//                // comment and if so remove it.
//                //String commentKey = dataSnapshot.getKey();
//                Event event = dataSnapshot.getValue(Event.class);
//                for (Event i: events){
//                    if(i.ID == event.ID){
//                        i.removeMarker();
//                    }
//
//                }
//                events.remove(dataSnapshot.getValue(Event.class));
//                // ...
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        };
//        eventsRef.addChildEventListener(childEventListener);
//    }

    public void deleteEvent(String eventID) {
        DatabaseReference db_node = FirebaseDatabase.getInstance().getReference().getRoot().child("events");
        db_node.child(eventID).setValue(null);
    }

}
