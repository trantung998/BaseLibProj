package com.bz.adslibrary;

import android.app.Activity;
import android.util.Log;

import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.InitializationListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;

public class AdsService {
    private static AdsService mInstace = null;
    private Activity mActivity;
    private String TAG = "AdsService";

    public static AdsService getInstance() {
        if (null == mInstace) {
            mInstace = new AdsService();
        }
        return mInstace;
    }

    public void Init(Activity activity, String ironsource_app_key, RewardedVideoListener callback) {
        mActivity = activity;
        mRewardedVideoListener = callback;
        //Rewarded Video
        IronSource.init(mActivity, ironsource_app_key, new InitializationListener() {
            @Override
            public void onInitializationComplete() {
                Log.d(TAG, "onInitializationComplete");
            }
        }, IronSource.AD_UNIT.REWARDED_VIDEO);

        SetVideoRewardListeners();

        //Once you’ve successfully verified your integration, please remember to remove the integration helper from your code.
        IntegrationHelper.validateIntegration(activity);
    }

    public void ShowAds(String placementName) {
        IronSource.showRewardedVideo(placementName);
    }

    public void ShowAds() {
        Log.d(TAG, "ShowAds");
        IronSource.showRewardedVideo();
    }

    public boolean IsRewardedVideoAvailable() {
        Log.d(TAG, "IsRewardedVideoAvailable");
//        return IsVideoRewardReady_AppLovin();
        return IronSource.isRewardedVideoAvailable();
    }

    public void OnResume() {
        IronSource.onResume(mActivity);
    }

    public void OnPause() {
        IronSource.onPause(mActivity);
    }

    RewardedVideoListener mRewardedVideoListener;

    private void SetVideoRewardListeners() {
        IronSource.setRewardedVideoListener(mRewardedVideoListener);
    }
}
