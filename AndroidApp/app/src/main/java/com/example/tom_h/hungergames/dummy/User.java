package com.example.tom_h.hungergames.dummy;

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

    private static String username;
    private static String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public static void writeNewUser() {
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();

        User user = new User(mAuth.getCurrentUser().getDisplayName(), mAuth.getCurrentUser().getEmail());

        mDatabase.child("users").child(mAuth.getCurrentUser().getUid()).setValue(user);
    }
}
