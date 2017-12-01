package com.example.tom_h.hungergames;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spratiman on 01-Dec-17.
 */

public class UserDataManager {

    public static FirebaseDatabase database;
    public static DatabaseReference mDatabase;
    public FirebaseStorage storage;
    public DatabaseReference usersRef;
    public static List<User> users = new ArrayList();

    public UserDataManager(){

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        mDatabase = database.getReference();

        usersRef = database.getReference("users");
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d("DATABASE", "onChildAdded:" + dataSnapshot.getKey());
                Log.d("DATABASE", "onChildAdded:" + dataSnapshot.getValue());
                User user = dataSnapshot.getValue(User.class);
                //CreateNotification(event);
                users.add(user);

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
                User user = dataSnapshot.getValue(User.class);
                for (User i: users){
                    if(i.uid == user.uid){
                        i.removeMarker();
                    }

                }
                users.remove(dataSnapshot.getValue(User.class));
                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        usersRef.addChildEventListener(childEventListener);
    }

    private static FirebaseAuth mAuth;

    public static void writeNewUser() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference();
        mAuth = FirebaseAuth.getInstance();
        User user = new User(mAuth.getCurrentUser().getDisplayName(), mAuth.getCurrentUser().getEmail(), mAuth.getUid().toString());

        mDatabase.child("users").child(mAuth.getCurrentUser().getUid().toString()).setValue(user);

        String TAG = "FireBaseIDService";
        try {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            Log.d(TAG, "Refreshed token: " + refreshedToken);
            if(refreshedToken != null) {
                //              SettingPreferences.setStringValueInPref(this, SettingPreferences.REG_ID, refreshedToken);
                //If you want to send messages to this application instance or manage this apps subscriptions on the server side, send the Instance ID token to your app server.
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mDatabase.child("users").child(mAuth.getCurrentUser().getUid().toString()).child("FirebaseInstanceIdToken").setValue(refreshedToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Call this function to update User details in Preference page
    public static void updateUser(String name, String email, List<String> preference) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference();
        mAuth = FirebaseAuth.getInstance();

        if((name != null && !name.trim().isEmpty()) && (email != null && !email.trim().isEmpty())) {
            mDatabase.child("users").child(mAuth.getCurrentUser().getUid().toString()).child("username").setValue(name);
            mDatabase.child("users").child(mAuth.getCurrentUser().getUid().toString()).child("email").setValue(email);
        } else if ((name != null && !name.trim().isEmpty()) && (email == null && email.isEmpty())) {
            mDatabase.child("users").child(mAuth.getCurrentUser().getUid().toString()).child("username").setValue(name);
        } else {
            mDatabase.child("users").child(mAuth.getCurrentUser().getUid().toString()).child("email").setValue(email);
        }

        if((preference != null)){
            mDatabase.child("users").child(mAuth.getCurrentUser().getUid().toString()).child("preference").setValue(preference);
        }
    }

}
