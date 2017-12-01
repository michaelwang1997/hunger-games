package com.example.tom_h.hungergames;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import com.example.tom_h.hungergames.MapsActivity;
import com.example.tom_h.hungergames.NavActivity;
import com.example.tom_h.hungergames.R;
import com.google.android.gms.maps.MapFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;

 /**
  * Created by Usama on 11/9/2017.
  *
  */
 
 public class CreateEvent extends Fragment implements View.OnClickListener{
 
     private int CAMERA_REQUEST = 100;
 
     ImageView image;
 
     Button submit;
 
     EditText _eventName;
 
     EditText _description;
 
     EditText _room;
 
     Spinner _category;
 
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
 
         submit.setOnClickListener(this);
         image.setOnClickListener(this);
         return view;
     }
 
     @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
         if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST) {
             Bitmap photo = (Bitmap) data.getExtras().get("data");
             image.setImageBitmap(photo);

             Uri tempUri = getImageUri(getActivity().getApplicationContext(), photo);

             File finalFile = new File(getRealPathFromURI(tempUri));

             Log.d("Pic path", finalFile.getAbsolutePath());
         }
     }

     public Uri getImageUri(Context inContext, Bitmap inImage) {
         ByteArrayOutputStream bytes = new ByteArrayOutputStream();
         inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
         String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
         return Uri.parse(path);
     }

     public String getRealPathFromURI(Uri uri) {
         Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
         cursor.moveToFirst();
         int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
         return cursor.getString(idx);
     }

     public void submit(){
 
         if(_eventName.getText() == null || _description.getText() == null
                     || _room.getText() == null || _category.getSelectedItem() == null
                  || _quantity.getSelectedItem() == null
                  )
          {
 
             Toast.makeText(getContext(), "You missed something!", Toast.LENGTH_SHORT).show();
              return;
          }
          String title = _eventName.getText().toString();
         String description = _description.getText().toString();
         String room = _room.getText().toString();
         String category = _category.getSelectedItem().toString();
         String quantity = _quantity.getSelectedItem().toString();
         Location eventLocation = null;
 
         if (MapsActivity.mLastLocation != null) {
             eventLocation = new Location(MapsActivity.mLastLocation);
         }
 
 
         Date time  = Calendar.getInstance().getTime();
         FirebaseAuth mAuth = FirebaseAuth.getInstance();
         String userID = mAuth.getCurrentUser().getUid().toString();
         Event event = new Event(eventLocation,category,quantity,title,description,room,time,userID);
 
         NavActivity.foodDataManager.createEvent(event);
     }
 
     @Override
     public void onClick(View v) {
         int i = v.getId();
         Log.d("submit button", "WTF");
         if (i == R.id.submit_button) {
              Log.d("submit button", "submit button pressed");
              submit();
              //Submit the event
 
             FragmentManager fragmentManager = getFragmentManager();
             FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
             /*mapFragment = new MapsActivity();*/
             fragmentTransaction.replace(R.id.nav, new MapsActivity());
             fragmentTransaction.commit();
 
          } else if (i == R.id.picture) {
              Intent cameraIntent =
                      new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
             startActivityForResult(cameraIntent, CAMERA_REQUEST);
         }
     }
 }