package com.example.tom_h.hungergames;

import java.sql.Time;

/**
 * Created by Tom-H on 11/13/2017.
 */

public class Event {


    private double lat; //latitude
    private double longi; //longitude

    private String title;
    private String description;

    private Time createTime;

    //private UNKNOWNTYPE owner;

    public Event(double lat, double longi, String title, String description,Time createTime){

        lat = this.lat;
        longi = this.longi;
        title = this.title;
        description = this.description;
        createTime = this.createTime;


    }

    //push event to servert


    //subscribe to read event

    //update map.


}
