package com.joga.world;

import android.os.Bundle;
import android.content.Intent;
//import android.support.v4.app.FragmentActivity;
import androidx.fragment.app.FragmentActivity;
import org.jitsi.meet.sdk.JitsiMeetView;
import org.jitsi.meet.sdk.JitsiMeetActivityInterface;
import org.jitsi.meet.sdk.JitsiMeetActivityDelegate;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import com.facebook.react.modules.core.PermissionListener;

// Example
//
public class ChatMainActivity extends FragmentActivity implements JitsiMeetActivityInterface {
    private JitsiMeetView view;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        JitsiMeetActivityDelegate.onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        JitsiMeetActivityDelegate.onBackPressed();
        // JitsiMeetActivityDelegate.requestPermissions();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new JitsiMeetView(this);
        JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                .setRoom("https://video.jogaworld.com/d1234").build();
        view.join(options);

        setContentView(view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        view.dispose();
        view = null;

        JitsiMeetActivityDelegate.onHostDestroy(this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        JitsiMeetActivityDelegate.onNewIntent(intent);
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, final String[] permissions,
            final int[] grantResults) {
        JitsiMeetActivityDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onResume() {
        super.onResume();

        JitsiMeetActivityDelegate.onHostResume(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        JitsiMeetActivityDelegate.onHostPause(this);
    }

    // JitsiMeetActivityInterface
    //

    @Override
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {
        JitsiMeetActivityDelegate.requestPermissions(this, permissions, requestCode, listener);
    }

}