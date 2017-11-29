package com.example.tom_h.hungergames;

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
        User user = new User(mAuth.getCurrentUser().getDisplayName(), mAuth.getCurrentUser().getEmail());

        mDatabase.child("users").child(mAuth.getCurrentUser().getUid().toString()).setValue(user);
    }

    // Call this function to update User details in Preference page
    public static void updateUser(String name, String email) {

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
    }
}
