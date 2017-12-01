package com.example.tom_h.hungergames;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MichaelWang on 2017-11-27.
 */

public class ProfileActivity extends Fragment implements View.OnClickListener {
    private ProfileItemRecyclerViewAdapter adapter;
    private OnListFragmentInteractionListener mListener;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    PreferenceActivity preferenceActivity;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProfileActivity() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ProfileActivity newInstance(int columnCount) {
        Log.d("ProfileActivity", "ProfileActivity successful");
        ProfileActivity fragment = new ProfileActivity();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

//        ImageView picture = getActivity().findViewById(R.id.profile)


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_profile, container, false);
                Context context = view.getContext();
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.user_list);
                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), mColumnCount));
                adapter = new ProfileItemRecyclerViewAdapter(recyclerView.getContext(), getUserData(), mListener);
                recyclerView.setAdapter(adapter);


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());

        TextView name = view.findViewById(R.id.profile_name);

        ImageView image = view.findViewById(R.id.profile_picture);

        if (acct != null) {
            String personName = acct.getDisplayName();

            Uri personPhoto = acct.getPhotoUrl();

            String imageUrl = personPhoto.toString();

            name.setText(personName);

            new DownLoadImageTask(image).execute(imageUrl);

        }

        Button logout = view.findViewById(R.id.btn_logout);

        logout.setOnClickListener(this);

        Button preferences = view.findViewById(R.id.btn_preferences);

        preferences.setOnClickListener(this);

        return view;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction();
    }

    //TODO: Create a method that generates an arraylist in the format of
    // {String fullName, String email, ArrayList of preferences, aka, Strings}
    // See DummyUser file for specific example

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

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_logout) {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            System.exit(0);
        }
        else if (i == R.id.btn_preferences) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            preferenceActivity = PreferenceActivity.newInstance(1);
            fragmentTransaction.replace(R.id.nav, preferenceActivity).commit();
        }
    }

    public List<Object> getUserData(){
        List<Object> userData = new ArrayList<>();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userID = mAuth.getCurrentUser().getUid().toString();
        for(User user: UserDataManager.users){
            if(user.uid.equals(userID)){
                userData.add(user.username);
                userData.add(user.email);
                Log.d("GET USER DATA", user.preference + "");
                userData.add(user.preference);
                break;
            }
        }
        return userData;
    }
}
