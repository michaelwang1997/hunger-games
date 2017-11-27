package com.example.tom_h.hungergames;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by spratiman on 17-Nov-17.
 */

public class FirebaseIDService extends FirebaseInstanceIdService {

    private static final String TAG = "FireBaseIDService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        try {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            Log.d(TAG, "Refreshed token: " + refreshedToken);
            if(refreshedToken != null) {
//              SettingPreferences.setStringValueInPref(this, SettingPreferences.REG_ID, refreshedToken);
                //If you want to send messages to this application instance or manage this apps subscriptions on the server side, send the Instance ID token to your app server.
                sendRegistrationToServer(refreshedToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mDatabase.child("users").child(mAuth.getCurrentUser().getUid().toString()).child("FirebaseInstanceIdToken").setValue(token);
    }
}
