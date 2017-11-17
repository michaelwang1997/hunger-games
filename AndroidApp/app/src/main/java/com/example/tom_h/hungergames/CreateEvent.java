package com.example.tom_h.hungergames;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;

/**
 * Created by Usama on 11/9/2017.
 *
 */

public class CreateEvent extends Fragment implements View.OnClickListener{

    private int PICK_IMAGE = 100;

    MapsActivity mapFragment;
    CreateEvent createEvent;

    ImageView image;

    Button submit;

//    @Bind(R.id.picture)
//    ImageView _createPicture;

//    @Bind(R.id.event_location)
//    Button _loginButton;

    //@Bind(R.id.event_name_input)
    EditText _eventName;

    //@Bind(R.id.event_description)
    EditText _description;

    //@Bind(R.id.location)
    EditText _room;

    //@Bind(R.id.category)
    Spinner _category;

    //@Bind(R.id.quantity)
    Spinner _quantity;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_event, container, false);
        Log.d("submit button", "WATWAT");
        _eventName = view.findViewById(R.id.event_name_input);
        _description = view.findViewById(R.id.event_description);
        _room = view.findViewById(R.id.room);
        _category = view.findViewById(R.id.category);
        _quantity = view.findViewById(R.id.quantity);


        image = view.findViewById(R.id.picture);
        submit = view.findViewById(R.id.submit_button);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery =
                        new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
            }
        });

        submit.setOnClickListener(this);
       // _submit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            //Uri imageUri = data.getData();
            //image.setImageURI(imageUri);
        }
    }

    public void submit(){

        if(_eventName.getText() == null || _description.getText() == null
                    || _room.getText() == null || _category.getSelectedItem() == null
                || _quantity.getSelectedItem() == null
                )
        {
            //tell the user they forgot to fill in info.
            //for now we just return nothing


            return;
        }
        String title = _eventName.getText().toString();
        String description = _description.getText().toString();
        String room = _room.getText().toString();
        String category = _category.getSelectedItem().toString();
        String quantity = _quantity.getSelectedItem().toString();
//        Intent i = new Intent(this, NavActivity.class);
//        startActivity(i);
        Location eventLocation = null;
        if(MapsActivity.mLastLocation != null){
            eventLocation = new Location(MapsActivity.mLastLocation);
        }


        Date time  = Calendar.getInstance().getTime();
        Event event = new Event(eventLocation,category,quantity,title,description,room,time);

        NavActivity.foodDataManager.createEvent(event);
    }
    /*

        @Bind(R.id.event_name)
    EditText _eventName;

    @Bind(R.id.event_description)
    EditText _description;

    @Bind(R.id.location)
    EditText _room;

    @Bind(R.id.category)
    Spinner _passwordText;

    @Bind(R.id.quantity)
    Spinner _quantity;

import java.util.Calendar

Date currentTime = Calendar.getInstance().getTime();

    @Bind(R.id.category)
    Spinner _category;
     */

    @Override
    public void onClick(View v) {
        int i = v.getId();
        Log.d("submit button", "WTF");
        if (i == R.id.submit_button) {
            Log.d("submit button", "submit button pressed");
            submit();
            //Submit the event
        }
    }
}