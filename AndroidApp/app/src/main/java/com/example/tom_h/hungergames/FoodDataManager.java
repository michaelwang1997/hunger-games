package com.example.tom_h.hungergames;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class FoodDataManager extends AppCompatActivity {

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


    public FoodDataManager(){
        //super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
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
                events.add(dataSnapshot.getValue(Event.class));

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
//        super.onCreate(savedInstanceState);
//        database = FirebaseDatabase.getInstance();
//        mDatabase = database.getReference();
//        //write to database
////        mDatabase.child("events").child("EventID").setValue(CHILDEVENT);
////        //YOu update event in the following mannor:
////        //mDatabase.child("users").child(userId).child("username").setValue(name);
////        mDatabase.setValue("Hello, world"); //basic setValue
////        // Read from the database
//        eventsRef = database.getReference();
//        ChildEventListener childEventListener = new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
//                Log.d("DATABASE", "onChildAdded:" + dataSnapshot.getKey());
//                Log.d("DATABASE", "onChildAdded:" + dataSnapshot.getValue());
//                events.add(dataSnapshot.getValue(Event.class));
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
//
////        mDatabase.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                // This method is called once with the initial value and again
////                // whenever data at this location is updated.
////                String value = dataSnapshot.getValue(String.class);
////                Log.d("TAG", "Value is: " + value);
////            }
////
////            @Override
////            public void onCancelled(DatabaseError error) {
////                // Failed to read value
////                Log.w("TAG", "Failed to read value.", error.toException());
////            }
////        });
//    }

    public void deleteEvent(String eventID) {
        DatabaseReference db_node = FirebaseDatabase.getInstance().getReference().getRoot().child("events");
        db_node.child(eventID).setValue(null);
    }

}
