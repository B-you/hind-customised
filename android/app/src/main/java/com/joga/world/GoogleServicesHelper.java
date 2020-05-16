package com.joga.world;

import android.net.Uri;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import io.fabric.sdk.android.Fabric;

import org.jitsi.meet.sdk.JitsiMeetActivity;

/**
 * Helper class to initialize Google related services and functionality. This
 * functionality is compiled conditionally and called via reflection, that's why
 * it was extracted here.
 *
 * "Libre builds" (builds with the LIBRE_BUILD flag set) will not include this
 * file.
 */
final class GoogleServicesHelper {
    public static void initialize(JitsiMeetActivity activity) {
        if (BuildConfig.GOOGLE_SERVICES_ENABLED) {
            Log.d(activity.getClass().getSimpleName(), "Initializing Google Services......");

            Fabric.with(activity, new Crashlytics());

            /*
             * FirebaseDynamicLinks.getInstance().getDynamicLink(activity.getIntent()).
             * addOnSuccessListener(activity, pendingDynamicLinkData -> { Uri dynamicLink =
             * null;
             * 
             * Log.d(activity.getClass().getSimpleName(),
             * "firebase deep linking call back..........");
             * 
             * 
             * if (pendingDynamicLinkData != null) { dynamicLink =
             * pendingDynamicLinkData.getLink(); }
             * Log.d(activity.getClass().getSimpleName(),
             * "firebase deep linking call back.........."+dynamicLink);
             * 
             * if (dynamicLink != null) {
             * 
             * //activity.join(dynamicLink.toString()); } });
             */
        }
    }
}
