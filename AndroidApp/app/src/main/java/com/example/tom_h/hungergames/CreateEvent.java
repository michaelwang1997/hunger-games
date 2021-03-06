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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

     File imageFile = null;
 
 
 
     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.create_event, container, false);
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

             imageFile = new File(getRealPathFromURI(tempUri));

             Log.d("Pic path", imageFile.getAbsolutePath());
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
         String imageID = null;
         if(imageFile != null){
             imageID = NavActivity.foodDataManager.uploadImage(imageFile);
         }
         Log.d("imageID", "What is imageID::" + imageID);

         Event event = new Event(eventLocation,category,imageID,quantity,title,description,room,time,userID);
 
         NavActivity.foodDataManager.createEvent(event);
         for(User user: UserDataManager.users){

             FirebaseDatabase database = FirebaseDatabase.getInstance();
             DatabaseReference mDatabase = database.getReference();
             //String tokenID = mDatabase.child("users").child(userID).child("FirebaseInstanceIdToken").toString();
             //String tokenID = "eTX_nsXGT24:APA91bHc4CR_67CfkudJ5vKqM0Q1yPENTOcrVN49szqdf1Hv71Iwv5xzNwKkOBBDQouiP0aM0MBAZ-ucNGeVRK0XQOjljnXcJwmlAhVpxm9heOEXgx6Y0d4B-Thr_m0YBpBSvJQzNAJO";
             String tokenID = "esRiv8iNxNs:APA91bHujlX4ikquYZ5xFsQ_u7dPTHhILRE5g7K3Yh5UYvgkNwTexOb__CziIEcnm0e5nly5IfQjesdHrNu-o47O4rJNUwtH0iIgiN8J0wf9gZRHY5BGcKQMGYqd0YL-9m_lR5u0rc1V";
             if (user.preference != null) {
                 for (String pref : user.preference) {
                     Log.d("Pref: ", pref);
                     if (pref.equals(category)) {
                         Log.d("category: ", category);
                         Runnable myRunnable = createRunnable(category, tokenID);
                         Thread thread = new Thread(myRunnable);
                         thread.start();
                     }
                 }
             }
         }
     }

     private Runnable createRunnable( final String categori, final String tokenIDe){

         Log.d("createRunnable:tokenIDe", tokenIDe);

         Runnable aRunnable = new Runnable() {
             @Override
             public void run() {
                 FirebaseCloudMessagingService.createNotificationRequest("New food event!", categori, tokenIDe);
             }
         };

         return aRunnable;
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