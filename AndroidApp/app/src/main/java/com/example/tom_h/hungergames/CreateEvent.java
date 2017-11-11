package com.example.tom_h.hungergames;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Usama on 11/9/2017.
 */

public class CreateEvent extends Fragment {

    /*@Bind(R.id.picture)
    ImageView _createPicture;*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_event, container, false);
        return view;
    }
}
