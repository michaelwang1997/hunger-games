package com.example.tom_h.hungergames;
 
 /**
  * Created by spratiman on 17-Nov-17.
  */
 
 import android.util.Log;
 
 import com.google.firebase.auth.FirebaseAuth;
 import com.google.firebase.database.DatabaseReference;
  import com.google.firebase.database.FirebaseDatabase;
  import com.google.firebase.iid.FirebaseInstanceId;
  
 import java.util.ArrayList;
 import java.util.List;
 
  public class User {
      public static FirebaseDatabase database;
      public static DatabaseReference mDatabase;
 @@ -19,6 +22,7 @@
  
      public String username;
      public String email;
     public List<String> preference; // food preference
  
      public User(){
          //empty for Firebase
 @@ -27,6 +31,7 @@ public User(){
      public User(String username, String email) {
          this.username = username;
          this.email = email;
 +        this.preference = new ArrayList<String>();
      }
  
      public static void writeNewUser() {
 @@ -54,7 +59,7 @@ public static void writeNewUser() {
      }
  
      // Call this function to update User details in Preference page
 
     public static void updateUser(String name, String email, List<String> preference) {
  
          FirebaseDatabase database = FirebaseDatabase.getInstance();
          DatabaseReference mDatabase = database.getReference();
 @@ -68,5 +73,9 @@ public static void updateUser(String name, String email) {
          } else {
              mDatabase.child("users").child(mAuth.getCurrentUser().getUid().toString()).child("email").setValue(email);
          }
 
         if((preference != null)){
             mDatabase.child("users").child(mAuth.getCurrentUser().getUid().toString()).child("preference").setValue(preference);
         }
      }
  }