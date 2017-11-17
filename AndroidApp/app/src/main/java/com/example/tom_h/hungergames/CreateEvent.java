package com.example.tom_h.hungergames;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;

/**
 * Created by Usama on 11/9/2017.
 *
 */

public class CreateEvent extends Fragment {

    private int PICK_IMAGE = 100;

    MapsActivity mapFragment;
    CreateEvent createEvent;

    ImageView image;

    Button submit;

    @Bind(R.id.picture)
    ImageView _createPicture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_event, container, false);

        image = view.findViewById(R.id.picture);
        submit = view.findViewById(R.id.submit_button);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery =
                        new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Event Submitted!", Toast.LENGTH_LONG).show();

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            Uri imageUri = data.getData();
            image.setImageURI(imageUri);
        }
    }
}