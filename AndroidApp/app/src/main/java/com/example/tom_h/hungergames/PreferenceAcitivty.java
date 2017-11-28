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

/**
 * Created by MichaelWang on 2017-11-27.
 */

public class PreferenceAcitivty extends Fragment {
    private MyItemRecyclerViewAdapter adapter;
    private ProfileActivity.OnListFragmentInteractionListener mListener;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PreferenceAcitivty() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PreferenceAcitivty newInstance(int columnCount) {
        Log.d("PreferenceAcitivty", "PreferenceAcitivty successful");
        PreferenceAcitivty fragment = new PreferenceAcitivty();
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
        final View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//                adapter = new MyItemRecyclerViewAdapter(this.getContext(), FoodDataManager.events, mListener, isMyEvents);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction();
    }
}
