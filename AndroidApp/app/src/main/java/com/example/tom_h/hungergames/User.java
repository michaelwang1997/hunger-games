package com.example.tom_h.hungergames;

/**
 * Created by spratiman on 17-Nov-17.
 */

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {
    public static FirebaseDatabase database;
    public static DatabaseReference mDatabase;

    private static FirebaseAuth mAuth;

    public String username;
    public String email;

    public User(){
        //empty for Firebase
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public static void writeNewUser() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference();
        mAuth = FirebaseAuth.getInstance();
        //mDatabase.child("users").child("Test").setValue("ASDASD");
        User user = new User(mAuth.getCurrentUser().getDisplayName(), mAuth.getCurrentUser().getEmail());

        mDatabase.child("users").child(mAuth.getCurrentUser().getUid().toString()).setValue(user);




    }
}
