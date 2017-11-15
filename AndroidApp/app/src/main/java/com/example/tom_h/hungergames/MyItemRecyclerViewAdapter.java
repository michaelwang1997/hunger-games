package com.example.tom_h.hungergames;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tom_h.hungergames.AllEventListActivity.OnListFragmentInteractionListener;
import com.example.tom_h.hungergames.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {
    private final Context cntx;
    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(Context context, List<DummyItem> items, OnListFragmentInteractionListener listener) {
        cntx = context;
        mValues = items;
        mListener = listener;
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
        int id = cntx.getResources().getIdentifier(mValues.get(position).picture, null, cntx.getPackageName());
        Drawable res = cntx.getResources().getDrawable(id);
        holder.mPictureView.setImageDrawable(res);
        holder.mEventNameView.setText(mValues.get(position).name);
        holder.mFoodAmountView.setText(mValues.get(position).amount);
        holder.mEventLocationView.setText(mValues.get(position).location);
        holder.mEventDescriptionView.setText(mValues.get(position).description);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                    Toast.makeText(v.getContext(), "This is an item", Toast.LENGTH_SHORT).show();
                }
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

        public DummyItem mItem;

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