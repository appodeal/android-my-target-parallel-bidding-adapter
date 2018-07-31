package com.appodealx.mailru;

import android.support.annotation.NonNull;

import com.appodealx.sdk.AdError;
import com.appodealx.sdk.NativeListener;
import com.my.target.nativeads.NativeAd;

public class MailruNativeListener implements NativeAd.NativeAdListener {

    private MailruNative mailruNative;
    private NativeListener nativeListener;

    MailruNativeListener(MailruNative mailruNative, NativeListener nativeListener) {
        this.mailruNative = mailruNative;
        this.nativeListener = nativeListener;
    }

    @Override
    public void onLoad(@NonNull NativeAd nativeAd) {
        nativeListener.onNativeLoaded(mailruNative.createNativeAd(nativeAd));
    }

    @Override
    public void onNoAd(@NonNull String s, @NonNull NativeAd nativeAd) {
        nativeListener.onNativeFailedToLoad(AdError.NoFill);
    }

    @Override
    public void onClick(@NonNull NativeAd nativeAd) {
        nativeListener.onNativeClicked();
    }

    @Override
    public void onShow(@NonNull NativeAd nativeAd) {

    }

    @Override
    public void onVideoPlay(@NonNull NativeAd nativeAd) {

    }

    @Override
    public void onVideoPause(@NonNull NativeAd nativeAd) {

    }

    @Override
    public void onVideoComplete(@NonNull NativeAd nativeAd) {

    }

}