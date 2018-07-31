package com.appodealx.mailru;

import android.content.Context;
import android.view.View;

import com.appodealx.sdk.NativeAd;
import com.appodealx.sdk.NativeListener;
import com.my.target.nativeads.factories.NativeViewsFactory;
import com.my.target.nativeads.views.ContentStreamAdView;
import com.my.target.nativeads.views.MediaAdView;

import java.util.List;

public class MailruNative extends NativeAd {

    private NativeListener nativeListener;
    private com.my.target.nativeads.NativeAd nativeAd;

    MailruNative(NativeListener nativeListener) {
        this.nativeListener = nativeListener;
    }

    public void load(Context context, int slotId, String bidId) {
        com.my.target.nativeads.NativeAd nativeAd = new com.my.target.nativeads.NativeAd(slotId, context);
        nativeAd.setListener(new MailruNativeListener(this, nativeListener));
        nativeAd.getCustomParams().setCustomParam("bid_id", bidId);
        nativeAd.load();
    }

    public NativeAd createNativeAd(com.my.target.nativeads.NativeAd nativeAd) {
        this.nativeAd = nativeAd;

        if (nativeAd.getBanner() != null) {
            if (nativeAd.getBanner().getIcon() != null) {
                setIcon(nativeAd.getBanner().getIcon().getUrl());
            }

            if (nativeAd.getBanner().getImage() != null) {
                setImage(nativeAd.getBanner().getImage().getUrl());
            }

            if (nativeAd.getBanner() != null && nativeAd.getBanner().getRating() != 0) {
                setRating(nativeAd.getBanner().getRating());
            }

            setTitle(nativeAd.getBanner().getTitle());
            setDescription(nativeAd.getBanner().getDescription());
            setCta(nativeAd.getBanner().getCtaText());
        }

        return this;
    }

    @Override
    public View getMediaView(Context context) {
        if (nativeAd != null) {
            ContentStreamAdView contentStreamView = NativeViewsFactory.getContentStreamView(nativeAd, context);
            MediaAdView mediaAdView = contentStreamView.getMediaAdView();
            contentStreamView.removeView(mediaAdView);

            return mediaAdView;
        }

        return super.getMediaView(context);
    }

    @Override
    public String getAgeRestrictions() {
        if (nativeAd != null && nativeAd.getBanner() != null) {
            return nativeAd.getBanner().getAgeRestrictions();
        } else {
            return super.getAgeRestrictions();
        }
    }

    @Override
    public void registerViewForInteraction(View view, List<View> clickableViews) {
        if (nativeAd != null) {
            nativeAd.registerView(view);
        }
    }

    @Override
    public void unregisterViewForInteraction() {
        if (nativeAd != null) {
            nativeAd.unregisterView();
        }
    }

    @Override
    public boolean hasVideo() {
        return nativeAd != null && nativeAd.getBanner() != null && nativeAd.getBanner().hasVideo();
    }

}