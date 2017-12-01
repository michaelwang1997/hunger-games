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
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class PreferenceItemRecyclerViewAdapter extends RecyclerView.Adapter<PreferenceItemRecyclerViewAdapter.ViewHolder>{
    private final Context cntx;
    private final List<String> mValues;
    private List<String> mUserPreferences;
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
        if(checkPreferences(mValues.get(position))){
            int id = cntx.getResources().getIdentifier("@drawable/star_filled", null, cntx.getPackageName());
            Drawable res = cntx.getResources().getDrawable(id);
            holder.toggle.setImageDrawable(res);
        }
        else{
            int id = cntx.getResources().getIdentifier("@drawable/star_unfilled", null, cntx.getPackageName());
            Drawable res = cntx.getResources().getDrawable(id);
            holder.toggle.setImageDrawable(res);
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id_filled = cntx.getResources().getIdentifier("@drawable/star_filled", null, cntx.getPackageName());
                Drawable filled = cntx.getResources().getDrawable(id_filled);
                int id_unfilled = cntx.getResources().getIdentifier("@drawable/star_unfilled", null, cntx.getPackageName());
                Drawable unfilled = cntx.getResources().getDrawable(id_unfilled);
                if(checkPreferences((String) holder.preference.getText())) {
                    holder.toggle.setImageDrawable(unfilled);
                    updateUserData((String) holder.preference.getText(), 0);
                    //TODO: REMOVE PREFERENCE FROM FIREBASE AND UPDATE PROFILE PAGE
                }
                else{
                    holder.toggle.setImageDrawable(filled);
                    updateUserData((String)holder.preference.getText(), 1);

                    //TODO: ADD PREFERENCE FROM FIREBASE AND UPDATE PROFILE PAGE

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
    public boolean checkPreferences(String preference){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userID = mAuth.getCurrentUser().getUid().toString();
        for(User user: UserDataManager.users){
            if(user.uid.equals(userID)) {
                if (user.preference != null) {
                    return user.preference.contains(preference);
                }
                break;
            }
        }
        return false;
    }
    public void updateUserData(String preference, int add_remove){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userID = mAuth.getCurrentUser().getUid().toString();
        List<String> copy = new ArrayList<>();
        String email = null;
        String name = null;
        for(User user: UserDataManager.users){
            if(user.uid.equals(userID)){
                if(user.preference != null){
                    copy = user.preference;
                }
                if (add_remove == 0) {
                    copy.remove(preference);
                }
                else if (add_remove == 1) {
                    copy.add(preference);
                }
                email = user.email;
                name = user.username;
                break;
            }
        }
        NavActivity.userDataManager.updateUser(name, email, copy);
    }
}
