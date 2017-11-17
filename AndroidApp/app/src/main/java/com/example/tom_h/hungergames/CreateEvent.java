package com.example.tom_h.hungergames;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    @Bind(R.id.event_name)
    EditText _eventName;

    @Bind(R.id.event_description)
    EditText _description;

    @Bind(R.id.location)
    EditText _room;

    @Bind(R.id.category)
    Spinner _category;

    @Bind(R.id.quantity)
    Spinner _quantity;



    @Bind(R.id.category)
    Spinner _category;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_event, container, false);

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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Event Submitted!", Toast.LENGTH_LONG).show();

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            Uri imageUri = data.getData();
            image.setImageURI(imageUri);
        }
    }

    public void submit(){
        String title = _eventName.getText().toString();
        String description = _description.getText().toString();
        String room = _room.getText().toString();
        String category = _category.toString();
        String quantity = _quantity.toString();

        Location eventLocation = new Location(MapsActivity.mLastLocation);

        Date time  = Calendar.getInstance().getTime();
        Event event = new Event(eventLocation,category,quantity,title,description,room,time);
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
        if (i == R.id.submit_button) {

            submit();
            //Submit the event
        }
    }
}