package com.example.tom_h.hungergames;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tom_h.hungergames.dummy.DummyUser;

/**
 * Created by MichaelWang on 2017-11-27.
 */

public class ProfileActivity extends Fragment {
    private ProfileItemRecyclerViewAdapter adapter;
    private OnListFragmentInteractionListener mListener;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_profile, container, false);
                Context context = view.getContext();
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.user_list);
                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), mColumnCount));
                adapter = new ProfileItemRecyclerViewAdapter(recyclerView.getContext(), DummyUser.user, mListener);
                recyclerView.setAdapter(adapter);

        return view;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction();
    }

    //TODO: Create a method that generates an arraylist in the format of
    // {String fullName, String email, ArrayList of preferences, aka, Strings}
    // See DummyUser file for specific example
}
