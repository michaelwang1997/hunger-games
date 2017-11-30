package com.example.tom_h.hungergames;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class AllEventListActivity extends Fragment {
    private static final String TAG = "AllEventListActivity";
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_BOOL_EVENT_PAGE = "bool-event-page";
    private static final String ARG_FOODDATAMANAGER = "food-data-manager";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    public boolean isMyEvents;
    public List<Event> myEvents;
    public boolean deleteConfirm;
    public Context cntx = this.getContext();
    private OnListFragmentInteractionListener mListener;
    private MyItemRecyclerViewAdapter adapter;
    private FoodDataManager foodDataManager;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AllEventListActivity() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AllEventListActivity newInstance(int columnCount, boolean isMyEvents, FoodDataManager foodDataManager) {
        Log.d(TAG, "AllEventListActivity successful");
        AllEventListActivity fragment = new AllEventListActivity();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putBoolean(ARG_BOOL_EVENT_PAGE, isMyEvents);
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
        myEvents = getMyEvents();
        // Set the adapter
        if (getArguments() != null) {
            isMyEvents = getArguments().getBoolean(ARG_BOOL_EVENT_PAGE);
            if (view instanceof RecyclerView) {
                final Context context = view.getContext();
                RecyclerView recyclerView = (RecyclerView) view;
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));

                // TODO: This is where you feed in data, and provide differences between my events and all events pages
                if (!isMyEvents) {
                    adapter = new MyItemRecyclerViewAdapter(this.getContext(), FoodDataManager.events, mListener, isMyEvents);
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter = new MyItemRecyclerViewAdapter(this.getContext(), myEvents, mListener, isMyEvents);
                    recyclerView.setAdapter(adapter);
                    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                        @Override
                        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                            return false;
                        }

                        @Override
                        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                            // Remove item from backing list here
                            RecyclerView.ViewHolder v = viewHolder;
                            final int position = v.getAdapterPosition();
//                            new AlertDialog.Builder(getActivity())
//                                    .setTitle("Close your event?")
//                                    .setMessage("Do you really want to close this event? You cannot undo this action.")
//                                    .setIcon(android.R.drawable.ic_dialog_alert)
//                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int whichButton) {
//
//                                        }
//                                    })
//                                    .setNegativeButton(android.R.string.no, null).create().show();
                            Event event = myEvents.remove(position);
                            NavActivity.foodDataManager.deleteEvent(Integer.toString(event.ID));
                            adapter.notifyDataSetChanged();
                            //TODO: Copy and paste code below into deleteEvent
//                            for(Event event: events){
//                                if(Integer.toString(event.ID).equals(eventID)){
//                                    events.remove(event);
//                                    break;
//                                }
//                            }
                        }

                    });
                    itemTouchHelper.attachToRecyclerView(recyclerView);
                }
            }
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Event event);
    }

    public List<Event> getMyEvents() {
        List<Event> events = new ArrayList<>();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userID = mAuth.getCurrentUser().getUid().toString();
        for (Event event : FoodDataManager.events) {
            if (event.userID.equals(userID)) {
                events.add(event);
            }
        }
        return events;
    }
}
