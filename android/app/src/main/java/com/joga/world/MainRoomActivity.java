package com.joga.world;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.os.Handler;
import android.util.Log;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import android.widget.Toast;
import android.content.Intent;

import android.net.Uri;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

public class MainRoomActivity extends AppCompatActivity {

    String roomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        // Initialize default options for Jitsi Meet conferences.
        URL serverURL;
        try {
            serverURL = new URL("https://video.jogaworld.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid server URL!");
        }
        JitsiMeetConferenceOptions defaultOptions = new JitsiMeetConferenceOptions.Builder().setServerURL(serverURL)
                .setWelcomePageEnabled(false).build();
        JitsiMeet.setDefaultConferenceOptions(defaultOptions);

        final int millisUntilLaunch = 1000;

        Intent intent = getIntent();
        roomName = intent.getStringExtra("room_name");

        Log.i("MainRoomActivity", "roomName: linkkkkk..." + roomName);

        JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder().setRoom(roomName).build();
        // Launch the new activity with the given options. The launch() method takes
        // care
        // of creating the required Intent and passing the options.
        JitsiMeetActivity.launch(this, options);

    }

    @Override
    protected void onStart() {
        super.onStart();
        checkforDynamicUrl();
    }

    public void onButtonClick(View v) {
        /*
         * EditText editText = findViewById(R.id.conferenceName); String text =
         * editText.getText().toString();
         *
         * if (text.length() > 0) { // Build options object for joining the conference.
         * The SDK will merge the // default // one we set earlier and this one when
         * joining.
         *
         * }
         */
    }

    public void checkforDynamicUrl() {
        // [START get_deep_link]
        FirebaseDynamicLinks.getInstance().getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Intent intent = getIntent();
                        Log.i("TAG", intent.toString()+".....getDynamicLink: linkkkkk..." + getIntent());

                        Log.i("TAG", "getDynamicLink: linkkkkk.333.." + pendingDynamicLinkData);

                        Uri deepLink = null;
                        if (intent != null) {
                            deepLink = intent.getData();
                        }
                        Log.i("TAG", "getDynamicLink: no link found...." + deepLink);

                        // [START_EXCLUDE]
                        // Display deep link in the UI
                        if (deepLink != null) {

                            String path = deepLink.getPath();
                            Log.i("TAG", "MainActivity: no link found.444..." + path);

                            String idStr = path.substring(path.lastIndexOf('/') + 1);
                            Log.i("TAG", "MainActivity: room no get from url...." + idStr);
                            Intent intentLocal = new Intent(MainRoomActivity.this, MainRoomActivity.class);
                            intentLocal.putExtra("room_name", idStr);
                            startActivity(intentLocal);

                        } else {
                            Log.d("TAG", "getDynamicLink: no link found");
                        }
                        // [END_EXCLUDE]
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "getDynamicLink:onFailure", e);
                    }
                });
        // [END get_deep_link]

    }
}
