package com.example.tom_h.hungergames;

import android.location.Location;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.IgnoreExtraProperties;

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

    public Date createTime;

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

    public Event(Location location,String category,String quantity ,String title, String description,String room, Date createTime){

       // this.location = location;
        this.title = title;
        this.description = description;
        this.room = room;
        this.category = category;
        this.createTime = createTime;
        this.quantity = quantity;
        //this.owner = LoginActivity.firebaseUser;

    }

    //push event to servert


    //subscribe to read event

    //update map.


}
