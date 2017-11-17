package com.example.tom_h.hungergames;

import android.location.Location;

import java.sql.Time;
import java.util.Date;

public class Event {


    private Location location;

    private String title;
    private String description;
    private String room;
    private String category;
    private String quantity;

    private Date createTime;

    //private UNKNOWNTYPE owner;

    /*

                String title = _eventName.getText().toString();
            String description = _description.getText().toString();
            String room = _room.getText().toString();
            String category = _category.toString();
            String quantity = _quantity.toString();

            Location eventLocation = new Location(MapsActivity.mLastLocation);
     */

    public Event(Location location,String category,String quantity ,String title, String description,String room, Date createTime){

        this.location = location;
        this.title = title;
        this.description = description;
        this.room = room;
        this.category = category;
        this.createTime = createTime;
        this.quantity = quantity;


    }

    //push event to servert


    //subscribe to read event

    //update map.


}
