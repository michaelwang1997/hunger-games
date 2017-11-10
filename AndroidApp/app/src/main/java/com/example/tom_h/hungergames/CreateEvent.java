package com.example.tom_h.hungergames;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * Created by Usama on 11/9/2017.
 */

public class CreateEvent extends Activity {

    /*@Bind(R.id.picture)
    ImageView _createPicture;*/

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);
        ButterKnife.bind(this);

    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }
}
