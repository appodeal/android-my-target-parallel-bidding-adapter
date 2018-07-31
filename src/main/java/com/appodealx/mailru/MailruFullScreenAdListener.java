package com.appodealx.mailru;

import android.support.annotation.NonNull;

import com.appodealx.sdk.AdError;
import com.appodealx.sdk.FullScreenAd;
import com.appodealx.sdk.FullScreenAdListener;
import com.my.target.ads.InterstitialAd;

public class MailruFullScreenAdListener implements InterstitialAd.InterstitialAdListener {

    private FullScreenAd fullScreenAd;
    private FullScreenAdListener fullScreenAdListener;
    private boolean finished = false;

    MailruFullScreenAdListener(@NonNull FullScreenAd fullScreenAd, FullScreenAdListener fullScreenAdListener) {
        this.fullScreenAd = fullScreenAd;
        this.fullScreenAdListener = fullScreenAdListener;
    }

    @Override
    public void onLoad(@NonNull InterstitialAd interstitialAd) {
        fullScreenAdListener.onFullScreenAdLoaded(fullScreenAd);
    }

    @Override
    public void onNoAd(@NonNull String s, @NonNull InterstitialAd interstitialAd) {
        fullScreenAdListener.onFullScreenAdFailedToLoad(AdError.NoFill);
    }

    @Override
    public void onClick(@NonNull InterstitialAd interstitialAd) {
        fullScreenAdListener.onFullScreenAdClicked();
    }

    @Override
    public void onDismiss(@NonNull InterstitialAd interstitialAd) {
        fullScreenAdListener.onFullScreenAdClosed(finished);
    }

    @Override
    public void onVideoCompleted(@NonNull InterstitialAd interstitialAd) {
        finished = true;

        fullScreenAdListener.onFullScreenAdCompleted();
    }

    @Override
    public void onDisplay(@NonNull InterstitialAd interstitialAd) {
        fullScreenAdListener.onFullScreenAdShown();
    }

}