package com.example.tom_h.hungergames;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tom_h.hungergames.ProfileActivity.OnListFragmentInteractionListener;

import java.util.List;

public class ProfileItemRecyclerViewAdapter extends RecyclerView.Adapter<ProfileItemRecyclerViewAdapter.ViewHolder> {
    private final Context cntx;
    private final List<Object> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ProfileItemRecyclerViewAdapter(Context context, List<Object> items, OnListFragmentInteractionListener listener) {
        cntx = context;
        mValues = items;
        mListener = listener;
    }

    @Override
    public ProfileItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_item, parent, false);
        return new ProfileItemRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProfileItemRecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        String key = "";
        if(position == 0){
            key = "Name";
        }
        if(position == 1){
            key = "Email";
        }
        if(position == 2){
            key = "Preferences";
        }
        holder.profileKey.setText(key);
        if(mValues.get(position) instanceof List){
            List<String> lst = (List<String>) mValues.get(position);
            String string = "";
            for(int i = 0; i < lst.size(); i++){
                if(i == 2 && i != lst.size() - 1){
                    string = string + lst.get(i) + "...";
                    break;
                }
                else if(i == 2 && i == lst.size() - 1 || i == lst.size() - 1){
                    string = string + lst.get(i);
                }
                else if(i != lst.size() - 1) {
                    String str = lst.get(i) + ", ";
                    string = string + str;
                }
            }
            holder.profileValue.setText(string);

        }
        else{
            holder.profileValue.setText((String) mValues.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView profileKey;
        public final TextView profileValue;

        public Object mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            profileKey = (TextView) view.findViewById(R.id.profile_key);
            profileValue = (TextView) view.findViewById(R.id.profile_value);

        }

    }
}