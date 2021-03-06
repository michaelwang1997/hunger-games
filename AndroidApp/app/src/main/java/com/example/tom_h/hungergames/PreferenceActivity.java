package com.example.tom_h.hungergames;

import android.content.Context;
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

import com.example.tom_h.hungergames.dummy.Preferences;

import java.util.ArrayList;
import java.util.List;

public class PreferenceActivity extends Fragment implements View.OnClickListener{
    private PreferenceItemRecyclerViewAdapter adapter;
    private PreferenceActivity.OnListFragmentInteractionListener mListener;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    ProfileActivity profileActivity;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PreferenceActivity() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PreferenceActivity newInstance(int columnCount) {
        Log.d("PreferenceActivity", "PreferenceActivity successful");
        PreferenceActivity fragment = new PreferenceActivity();
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
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        profileActivity = ProfileActivity.newInstance(1);
        fragmentTransaction.replace(R.id.nav, profileActivity).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.preference_list, container, false);
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.preference_list_view);
            recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), mColumnCount));
            List<String> preferences = new ArrayList<>();
            preferences.add("Pizza");
            preferences.add("Burger");
            preferences.add("Noodles");
            adapter = new PreferenceItemRecyclerViewAdapter(recyclerView.getContext(), Preferences.preferences, preferences ,mListener);
            recyclerView.setAdapter(adapter);
        Button done = view.findViewById(R.id.btn_done);
        done.setOnClickListener(this);
        return view;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction();
    }

    //TODO: Make a method that can generate the user's preferences into a arraylist that is taken from database
}
