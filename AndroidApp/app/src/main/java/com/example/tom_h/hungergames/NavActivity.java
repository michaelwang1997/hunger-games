package com.example.tom_h.hungergames;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;


public class NavActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        AllEventListActivity.OnListFragmentInteractionListener {

    MapsActivity mapFragment;
    CreateEvent createEvent;
    ProfileActivity profileActivity;
    Fragment allEventListActivity;
    public static FoodDataManager foodDataManager;
    public static Context navContext;
    public static String NOTIFICATION_SERVICE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        navContext = this;
        NOTIFICATION_SERVICE = navContext.NOTIFICATION_SERVICE;
        setSupportActionBar(toolbar);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mapFragment = new MapsActivity();
        fragmentTransaction.replace(R.id.nav, mapFragment);
        fragmentTransaction.commit();
        foodDataManager = new FoodDataManager();


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        ImageView userIcon = (ImageView) headerView.findViewById(R.id.user_pic);

        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                profileActivity = new ProfileActivity();
                fragmentTransaction.replace(R.id.nav, profileActivity);
                if(!mapFragment.isVisible()) {
                    fragmentTransaction.remove(mapFragment).commit();
                } else {
                    fragmentTransaction.commit();
                }
            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(NavActivity.this);

        if (acct != null) {
            String personName = acct.getDisplayName();

            Uri personPhoto = acct.getPhotoUrl();

            String imageUrl = personPhoto.toString();

            new DownLoadImageTask(userIcon).execute(imageUrl);

            /*Log.d("Uri", personPhoto.toString());
            userIcon.setImageURI(personPhoto);*/

            TextView name = headerView.findViewById(R.id.user_name);
            name.setText(personName);
        }


        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == MapsActivity.MY_PERMISSIONS_REQUEST_LOCATION) {
            mapFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.create_event) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            createEvent = new CreateEvent();
            fragmentTransaction.replace(R.id.nav, createEvent);
            if(!mapFragment.isVisible()) {
                fragmentTransaction.remove(mapFragment).commit();
            } else {
                fragmentTransaction.commit();
            }
        } else if (id == R.id.Map) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mapFragment = new MapsActivity();
            fragmentTransaction.replace(R.id.nav, mapFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.my_events) {
            // Insert code
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            allEventListActivity = AllEventListActivity.newInstance(1, true,
                    foodDataManager);
            fragmentTransaction.replace(R.id.nav, allEventListActivity);
            if(!mapFragment.isVisible()) {
                fragmentTransaction.remove(mapFragment).commit();
            } else {
                fragmentTransaction.commit();
            }
        } else if (id == R.id.all_events) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            allEventListActivity = AllEventListActivity.newInstance(1, false,
                    foodDataManager);
            fragmentTransaction.replace(R.id.nav, allEventListActivity);
            if(!mapFragment.isVisible()) {
                fragmentTransaction.remove(mapFragment).commit();
            } else {
                fragmentTransaction.commit();
            }
        } /*else if (id == R.id.settings) {
            // Insert code
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            profileActivity = new ProfileActivity();
            fragmentTransaction.replace(R.id.nav, profileActivity);
            if(!mapFragment.isVisible()) {
                fragmentTransaction.remove(mapFragment).commit();
            } else {
                fragmentTransaction.commit();
            }

        } else if (id == R.id.logout) {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            System.exit(0);
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(Event item) {

    }

    public void userPage() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        profileActivity = new ProfileActivity();
        fragmentTransaction.replace(R.id.nav, profileActivity);
        if(!mapFragment.isVisible()) {
            fragmentTransaction.remove(mapFragment).commit();
        } else {
            fragmentTransaction.commit();
        }
    }


    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
}