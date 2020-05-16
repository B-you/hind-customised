/*
 * Copyright @ 2019-present 8x8, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jitsi.meet.sdk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.facebook.react.modules.core.PermissionListener;

import org.jitsi.meet.sdk.log.JitsiMeetLogger;

import java.util.Map;

/**
 * A base activity for SDK users to embed. It uses {@link JitsiMeetFragment} to
 * do the heavy lifting and wires the remaining Activity lifecycle methods so it
 * works out of the box.
 */
public class JitsiMeetActivity extends FragmentActivity implements JitsiMeetActivityInterface, JitsiMeetViewListener {

    protected static final String TAG = JitsiMeetActivity.class.getSimpleName();

    private static final String ACTION_JITSI_MEET_CONFERENCE = "org.jitsi.meet.CONFERENCE";
    private static final String JITSI_MEET_CONFERENCE_OPTIONS = "JitsiMeetConferenceOptions";

    // Helpers for starting the activity
    //

    public static void launch(Context context, JitsiMeetConferenceOptions options) {
        Intent intent = new Intent(context, JitsiMeetActivity.class);
        intent.setAction(ACTION_JITSI_MEET_CONFERENCE);
        intent.putExtra(JITSI_MEET_CONFERENCE_OPTIONS, options);
        context.startActivity(intent);
    }

    public static void launch(Context context, String url) {
        JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder().setRoom(url).build();
        launch(context, options);
    }

    // Overrides
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_jitsi_meet);

        if (!extraInitialize()) {
            initialize();
        }
        //checkforDynamicUrl();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //checkforDynamicUrl();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent in = getIntent();
        Uri data = in.getData();
        if(data != null) {
            String roomName = data.toString();
            System.out.println(roomName + ".....deeplinkingcallback..77777........." + in + "...   :- " + data);

            String roomID = roomName.substring(roomName.lastIndexOf('/') + 1);
            Log.i("getConferenceOptions", roomID + ".... no link found2...000..." + roomName.indexOf("https://yourmbc.page.link/"));

            if (roomName.indexOf("https://yourmbc.page.link/") > -1) {
                JitsiMeetConferenceOptions optionsLocal = new JitsiMeetConferenceOptions.Builder().setRoom(roomID).build();
                Log.i("getConferenceOptions", roomName + ".... no link found2..joined..1.." + optionsLocal.getRoom());
                join(optionsLocal);
            }
        }

    }

    public void checkforDynamicUrl() {
        // [START get_deep_link]
        FirebaseDynamicLinks.getInstance().getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Intent intent = getIntent();
                        Log.i("TAG", "getDynamicLink: linkkkkk.2222.." + intent.getData());

                        Log.i("TAG", "getDynamicLink: linkkkkk.2323.." + pendingDynamicLinkData);


                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                        }
                        Log.i("TAG", "getDynamicLink: no link found..45444.." + deepLink);

                        Toast.makeText(getApplicationContext(), "deep lick click...." + deepLink, Toast.LENGTH_SHORT)
                                .show();

                        // [START_EXCLUDE]
                        // Display deep link in the UI
                        if (deepLink != null) {

                            Toast.makeText(getApplicationContext(), "deep lick click.45444.33.." + deepLink,
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            Log.d("TAG", "getDynamicLink: no link found");
                        }
                        // [END_EXCLUDE]
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("TAG", "getDynamicLink:onFailure....454444", e);
            }
        });
        // [END get_deep_link]

    }

    @Override
    public void onDestroy() {
        // Here we are trying to handle the following corner case: an application using
        // the SDK
        // is using this Activity for displaying meetings, but there is another "main"
        // Activity
        // with other content. If this Activity is "swiped out" from the recent list we
        // will get
        // Activity#onDestroy() called without warning. At this point we can try to
        // leave the
        // current meeting, but when our view is detached from React the JS <-> Native
        // bridge won't
        // be operational so the external API won't be able to notify the native side
        // that the
        // conference terminated. Thus, try our best to clean up.
        leave();
        if (AudioModeModule.useConnectionService()) {
            ConnectionService.abortConnections();
        }
        JitsiMeetOngoingConferenceService.abort(this);

        super.onDestroy();
    }

    @Override
    public void finish() {
        leave();

        super.finish();
    }

    // Helper methods
    //

    protected JitsiMeetView getJitsiView() {
        JitsiMeetFragment fragment = (JitsiMeetFragment) getSupportFragmentManager()
                .findFragmentById(R.id.jitsiFragment);
        return fragment.getJitsiView();
    }

    public void join(@Nullable String url) {
        JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder().setRoom(url).build();
        join(options);
    }

    public void join(JitsiMeetConferenceOptions options) {
        getJitsiView().join(options);
    }

    public void leave() {
        getJitsiView().leave();
    }

    private @Nullable JitsiMeetConferenceOptions getConferenceOptions(Intent intent) {
        String action = intent.getAction();

        if (Intent.ACTION_VIEW.equals(action)) {
            Uri uri = intent.getData();
            if (uri != null) {
                return new JitsiMeetConferenceOptions.Builder().setRoom(uri.toString()).build();
            }
        } else if (ACTION_JITSI_MEET_CONFERENCE.equals(action)) {
            return intent.getParcelableExtra(JITSI_MEET_CONFERENCE_OPTIONS);
        }

        return null;
    }

    /**
     * Helper function called during activity initialization. If {@code true} is
     * returned, the initialization is delayed and the
     * {@link JitsiMeetActivity#initialize()} method is not called. In this case,
     * it's up to the subclass to call the initialize method when ready.
     *
     * This is mainly required so we do some extra initialization in the Jitsi Meet
     * app.
     *
     * @return {@code true} if the initialization will be delayed, {@code false}
     *         otherwise.
     */
    protected boolean extraInitialize() {
        return false;
    }

    protected void initialize() {
        // Listen for conference events.
        getJitsiView().setListener(this);

        // Join the room specified by the URL the app was launched with.
        // Joining without the room option displays the welcome page.
        join(getConferenceOptions(getIntent()));
    }

    // Activity lifecycle methods
    //

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        JitsiMeetActivityDelegate.onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        JitsiMeetActivityDelegate.onBackPressed();
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("TAG", "onNewIntent: no link found2..99999.." + intent.toString());

        JitsiMeetConferenceOptions options;

        Log.i("TAG", "onNewIntent: no link found2..second.." + getConferenceOptions(intent));


        if ((options = getConferenceOptions(intent)) != null) {
            String roomName = options.getRoom();
            String roomID = roomName.substring(roomName.lastIndexOf('/') + 1);
            JitsiMeetConferenceOptions optionsLocal = new JitsiMeetConferenceOptions.Builder().setRoom(roomID).build();
            Log.i("getConferenceOptions", roomName+".... no link found2...000..."+roomName.indexOf("https://yourmbc.page.link/"));

            if(roomName.indexOf("https://yourmbc.page.link/") > -1){
                Log.i("getConferenceOptions", options+".... no link found2..joined..1.."+optionsLocal.getRoom());
                join(optionsLocal);
            }else{
                Log.i("getConferenceOptions", options+".... no link found2..joined..2.."+options.getRoom());
                join(options);
            }

            Log.i("getConferenceOptions", options+".... no link found2......");
            return;
        }

        JitsiMeetActivityDelegate.onNewIntent(intent);
    }

    @Override
    protected void onUserLeaveHint() {
        getJitsiView().enterPictureInPicture();
    }

    // JitsiMeetActivityInterface
    //

    @Override
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {
        JitsiMeetActivityDelegate.requestPermissions(this, permissions, requestCode, listener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        JitsiMeetActivityDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    // JitsiMeetViewListener
    //

    @Override
    public void onConferenceJoined(Map<String, Object> data) {
        JitsiMeetLogger.i("Conference joined: " + data);
        // Launch the service for the ongoing notification.
        JitsiMeetOngoingConferenceService.launch(this);
    }

    @Override
    public void onConferenceTerminated(Map<String, Object> data) {
        JitsiMeetLogger.i("Conference terminated: " + data);
        finish();
    }

    @Override
    public void onConferenceWillJoin(Map<String, Object> data) {
        JitsiMeetLogger.i("Conference will join: " + data);
    }
}
