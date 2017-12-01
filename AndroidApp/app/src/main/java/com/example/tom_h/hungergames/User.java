package com.example.tom_h.hungergames;
 
 /**
  * Created by spratiman on 17-Nov-17.
  */
 
 import android.util.Log;

 import com.google.android.gms.maps.model.Marker;
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
 
  public class User {

      private Marker marker = null;

      public void removeMarker(){
          marker.remove();
      }
 
     private static FirebaseAuth mAuth;
  
      public String username;
      public String email;
     public List<String> preference; // food preference
      public String uid;
  
      public User(){
          //empty for Firebase
     }
 
      public User(String username, String email, String uid) {
          this.username = username;
          this.email = email;
          this.preference = new ArrayList<>();
          this.uid = uid;

      }
  }