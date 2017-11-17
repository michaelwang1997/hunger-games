package com.example.tom_h.hungergames;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tom_h.hungergames.AllEventListActivity.OnListFragmentInteractionListener;
import com.example.tom_h.hungergames.dummy.DummyItem;

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        int id = cntx.getResources().getIdentifier("@drawable/fastfood", null, cntx.getPackageName());
        Drawable res = cntx.getResources().getDrawable(id);
        holder.mPictureView.setImageDrawable(res);
        holder.mEventNameView.setText(mValues.get(position).title);
        holder.mFoodAmountView.setText(mValues.get(position).quantity);
        Geocoder geocoder = new Geocoder(cntx);
        try {
            List<Address> address = geocoder.getFromLocation(mValues.get(position).latitude, mValues.get(position).longitude, 1);
            holder.mEventLocationView.setText(address.get(0).getAddressLine(0));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        holder.mEventDescriptionView.setText(mValues.get(position).description);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                    if(isMyEventsBool) { //My events page
                        Toast.makeText(v.getContext(), "Take me to a page of the event", Toast.LENGTH_SHORT).show();
                    }
                    else { //All Events page
                        Toast.makeText(v.getContext(), "Take me to the event on the map", Toast.LENGTH_SHORT).show();
                    }                }
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
