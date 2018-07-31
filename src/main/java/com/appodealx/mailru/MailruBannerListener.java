package com.appodealx.mailru;

import android.support.annotation.NonNull;

import com.appodealx.sdk.AdError;
import com.appodealx.sdk.BannerListener;
import com.my.target.ads.MyTargetView;

public class MailruBannerListener implements MyTargetView.MyTargetViewListener {

    private MailruBanner mailruBanner;
    private BannerListener bannerListener;

    MailruBannerListener(@NonNull MailruBanner mailruBanner, BannerListener bannerListener) {
        this.mailruBanner = mailruBanner;
        this.bannerListener = bannerListener;
    }

    @Override
    public void onLoad(@NonNull MyTargetView myTargetView) {
        mailruBanner.fillContainer();
    }

    @Override
    public void onNoAd(@NonNull String s, @NonNull MyTargetView myTargetView) {
        bannerListener.onBannerFailedToLoad(AdError.NoFill);
    }

    @Override
    public void onClick(@NonNull MyTargetView myTargetView) {
        bannerListener.onBannerClicked();
    }

}