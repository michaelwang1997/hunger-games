package com.example.tom_h.hungergames;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tom_h.hungergames.PreferenceActivity.OnListFragmentInteractionListener;

import java.util.List;
/**
 * Created by MichaelWang on 2017-11-28.
 */

public class PreferenceItemRecyclerViewAdapter extends RecyclerView.Adapter<PreferenceItemRecyclerViewAdapter.ViewHolder>{
    private final Context cntx;
    private final List<String> mValues;
    private final List<String> mUserPreferences;
    private final OnListFragmentInteractionListener mListener;

    public PreferenceItemRecyclerViewAdapter(Context context, List<String> items, List<String> user_preferences, OnListFragmentInteractionListener listener) {
        cntx = context;
        mValues = items;
        mUserPreferences = user_preferences;
        mListener = listener;
    }

    @Override
    public PreferenceItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.preference_item_list, parent, false);
        return new PreferenceItemRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PreferenceItemRecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.preference.setText(mValues.get(position));
        if(mUserPreferences.contains(mValues.get(position))){
            int id = cntx.getResources().getIdentifier("@drawable/star_filled", null, cntx.getPackageName());
            Drawable res = cntx.getResources().getDrawable(id);
            holder.toggle.setImageDrawable(res);
        }
        else{
            int id = cntx.getResources().getIdentifier("@drawable/star_unfilled", null, cntx.getPackageName());
            Drawable res = cntx.getResources().getDrawable(id);
            holder.toggle.setImageDrawable(res);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView preference;
        public final ImageView toggle;

        public Object mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            preference = (TextView) view.findViewById(R.id.preference_name);
            toggle = (ImageView) view.findViewById(R.id.toggle);

        }

    }
}
