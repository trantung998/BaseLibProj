package com.bz.firebaselib;

import android.app.Activity;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class FirebaseService {
    private static FirebaseService mInstace = null;
    private FirebaseAnalytics mFirebaseAnalytics;

    public static FirebaseService getInstance() {
        if (null == mInstace) {
            mInstace = new FirebaseService();
        }
        return mInstace;
    }
    private Activity mActivity;

    public void Init(Activity activity){
        mActivity = activity;
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(mActivity.getApplicationContext());
    }

    public void LogEvent(String eventName, Bundle eventData){
        mFirebaseAnalytics.logEvent(eventName, eventData);
    }
}
