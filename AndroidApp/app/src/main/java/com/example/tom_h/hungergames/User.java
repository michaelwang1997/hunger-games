package com.example.tom_h.hungergames;
 
 /**
  * Created by spratiman on 17-Nov-17.
  */
 
 import com.google.android.gms.maps.model.Marker;
import com.google.firebase.auth.FirebaseAuth;

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
 
      public User(String username, String email, String uid, List<String> preferece) {
          this.username = username;
          this.email = email;
          if(preferece != null){
              this.preference = preferece;
          }
          else{
              this.preference = new ArrayList<>();
          }
          this.uid = uid;

      }
  }