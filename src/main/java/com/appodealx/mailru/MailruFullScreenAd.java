package com.appodealx.mailru;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.appodealx.sdk.AdError;
import com.appodealx.sdk.FullScreenAd;
import com.appodealx.sdk.FullScreenAdListener;
import com.my.target.ads.InterstitialAd;

public class MailruFullScreenAd extends FullScreenAd {

    private FullScreenAdListener fullScreenAdListener;
    private InterstitialAd interstitialAd;

    MailruFullScreenAd(FullScreenAdListener fullScreenAdListener) {
        this.fullScreenAdListener = fullScreenAdListener;
    }

    public void load(Context context, int slotId, String bidId) {
        interstitialAd = new InterstitialAd(slotId, context);
        interstitialAd.setListener(new MailruFullScreenAdListener(this, fullScreenAdListener));
        interstitialAd.getCustomParams().setCustomParam("bid_id", bidId);
        interstitialAd.load();
    }

    @Override
    public void show(@NonNull Activity activity) {
        if (interstitialAd != null) {
            interstitialAd.show();
        } else {
            fullScreenAdListener.onFullScreenAdFailedToShow(AdError.InternalError);
        }
    }
}