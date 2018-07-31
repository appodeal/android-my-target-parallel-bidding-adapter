package com.appodealx.mailru;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.appodealx.sdk.BannerListener;
import com.appodealx.sdk.BannerView;
import com.my.target.ads.MyTargetView;

public class MailruBanner {

    private BannerView bannerView;
    private BannerListener bannerListener;
    private MyTargetView myTargetView;

    MailruBanner(@NonNull BannerView bannerView, BannerListener bannerListener) {
        this.bannerView = bannerView;
        this.bannerListener = bannerListener;
    }

    public void load(Context context, int slotId, String bidId) {
        myTargetView = new MyTargetView(context);
        myTargetView.init(slotId, false);
        myTargetView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        myTargetView.setListener(new MailruBannerListener(this, bannerListener));
        if (myTargetView.getCustomParams() != null) {
            myTargetView.getCustomParams().setCustomParam("bid_id", bidId);
        }
        myTargetView.load();
    }

    void fillContainer() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                bannerView.setBannerHeight(50);
                bannerView.addView(myTargetView);

                bannerListener.onBannerLoaded(bannerView);
            }
        });
    }

}