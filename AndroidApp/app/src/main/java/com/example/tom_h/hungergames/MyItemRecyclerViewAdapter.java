package com.example.tom_h.hungergames;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tom_h.hungergames.AllEventListActivity.OnListFragmentInteractionListener;
import com.example.tom_h.hungergames.dummy.DummyItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {
    private final Context cntx;
    private final List<Event> mValues;
    private final OnListFragmentInteractionListener mListener;
    private boolean isMyEventsBool;
    MapsActivity mapFragment;


    public MyItemRecyclerViewAdapter(Context context, List<Event> items, OnListFragmentInteractionListener listener, boolean isMyEvents) {
        cntx = context;
        mValues = items;
        mListener = listener;
        isMyEventsBool = isMyEvents;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        int id = cntx.getResources().getIdentifier("@drawable/fastfood", null, cntx.getPackageName());
        String imageID = mValues.get(position).imageID;
        File file = NavActivity.foodDataManager.downloadImage(imageID,holder.mPictureView);

//        Drawable res = cntx.getResources().getDrawable(id);
//        holder.mPictureView.setImageDrawable(res);
        if(file == null){
            Drawable res = cntx.getResources().getDrawable(id);
            holder.mPictureView.setImageDrawable(res);
        }
        else{
//            String filePath = file.getPath();
//            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
//            holder.mPictureView.setImageBitmap(bitmap);
        }


        holder.mEventNameView.setText(mValues.get(position).title);
        holder.mFoodAmountView.setText(mValues.get(position).quantity);
        Geocoder geocoder = new Geocoder(cntx);
        try {
            List<Address> address = geocoder.getFromLocation(mValues.get(position).latitude, mValues.get(position).longitude, 1);
            String[] addressSplit = address.get(0).getAddressLine(0).split("\\s+");
            String addressNum = addressSplit[0];
            String finalAddress;
            if(addressNum != null && addressNum.matches("[-+]?\\d*\\.?\\d+")){
                finalAddress = addressNum + " " + address.get(0).getThoroughfare() + ", " + address.get(0).getPostalCode() + ", Room: " + mValues.get(position).room;
            }
            else{
                finalAddress =address.get(0).getThoroughfare() +  ", " + address.get(0).getPostalCode() + ", Room: " + mValues.get(position).room;
            }
            holder.mEventLocationView.setText(finalAddress);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String shortenedDescriptions = "";
        for(int i = 0; i < mValues.get(position).description.length(); i++){
            if(i == 65){
                shortenedDescriptions = shortenedDescriptions + "...";
                break;
            }
            shortenedDescriptions = shortenedDescriptions + mValues.get(position).description.charAt(i);
        }
        holder.mEventDescriptionView.setText(shortenedDescriptions);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                    if(isMyEventsBool) { //My events page
                        FragmentManager fragmentManager = ((FragmentActivity)cntx).getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        mapFragment = new MapsActivity();
                        fragmentTransaction.replace(R.id.nav, mapFragment);
                        fragmentTransaction.commit();
                        Log.d("MAPS ACTIVITY", mapFragment + "");
                        Log.d("MAPS ACTIVITY", mapFragment.mGoogleMap + "");
                        mapFragment.fromEvent = false;
                        mapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(GoogleMap googleMap) {
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(mValues.get(position).latitude, mValues.get(position).longitude), 17.0f));
                                googleMap.setOnCameraChangeListener(null);
                            }
                        });
                    }
                    else { //All Events page
                        FragmentManager fragmentManager = ((FragmentActivity)cntx).getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        mapFragment = new MapsActivity();
                        fragmentTransaction.replace(R.id.nav, mapFragment);
                        fragmentTransaction.commit();
                        Log.d("MAPS ACTIVITY", mapFragment + "");
                        Log.d("MAPS ACTIVITY", mapFragment.mGoogleMap + "");
                        mapFragment.fromEvent = false;
                        mapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(GoogleMap googleMap) {
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(mValues.get(position).latitude, mValues.get(position).longitude), 17.0f));
                                googleMap.setOnCameraChangeListener(null);
                            }
                        });
                    }
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                    if(isMyEventsBool) { //My events page
                        Toast.makeText(v.getContext(), "Let me edit my event", Toast.LENGTH_SHORT).show();
                    }
                    else { //All Events page
                        Toast.makeText(v.getContext(), "Let me favourite this event to get notifications", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mPictureView;
        public final TextView mEventNameView;
        public final TextView mFoodAmountView;
        public final TextView mEventLocationView;
        public final TextView mEventDescriptionView;

        public Event mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            mPictureView = (ImageView) view.findViewById(R.id.picture);
            mEventNameView = (TextView) view.findViewById(R.id.event_name);
            mFoodAmountView = (TextView) view.findViewById(R.id.food_amount);
            mEventLocationView = (TextView) view.findViewById(R.id.event_location);
            mEventDescriptionView = (TextView) view.findViewById(R.id.event_description);

        }

    }
}
