package com.example.tom_h.hungergames;

import android.location.Location;

import com.google.android.gms.maps.model.Marker;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.File;
import java.sql.Time;
import java.util.Date;
import java.util.Random;

@IgnoreExtraProperties
public class Event {


    //public Location location;

    public String title;
    public String description;
    public String room;
    public String category;
    public String quantity;
    public String userID;
    public String imageID;

    public Date createTime;

    public double latitude;
    public double longitude;

    private Marker marker = null;


    public void setMarker (Marker marker){
        this.marker = marker;
    }

    public void removeMarker(){
        if(marker != null){
            marker.remove();
        }




    }

    //public FirebaseUser owner;

    public final int ID = new Random().nextInt(900000)+100000;

    //private UNKNOWNTYPE owner;

    /*

                String title = _eventName.getText().toString();
            String description = _description.getText().toString();
            String room = _room.getText().toString();
            String category = _category.toString();
            String quantity = _quantity.toString();

            Location eventLocation = new Location(MapsActivity.mLastLocation);
     */

    public Event(){
        //firebase needs an empty constructor
    }

    public Event(Location location, String category, String imageID, String quantity , String title, String description, String room, Date createTime, String userID){

       // this.location = location;
        this.title = title;
        this.description = description;
        this.room = room;
        this.category = category;
        this.createTime = createTime;
        this.quantity = quantity;
        //this.owner = LoginActivity.firebaseUser;
        this.userID = userID;
        this.imageID = imageID;
        longitude = location.getLongitude();
        latitude = location.getLatitude();
    }

    //push event to servert


    //subscribe to read event

    //update map.


}
