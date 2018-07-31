package com.appodealx.mailru;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.appodealx.sdk.AdError;
import com.appodealx.sdk.BannerListener;
import com.appodealx.sdk.BannerView;
import com.appodealx.sdk.FullScreenAdListener;
import com.appodealx.sdk.InternalAdapterInterface;
import com.appodealx.sdk.NativeListener;
import com.appodealx.sdk.utils.RequestInfoKeys;
import com.my.target.common.MyTargetPrivacy;
import com.my.target.common.MyTargetVersion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class Mailru extends InternalAdapterInterface {

    @NonNull
    @Override
    public JSONArray getBannerRequestInfo(@NonNull JSONObject adapterSettings) {
        return getRequestInfoFromSettings(adapterSettings);
    }

    @NonNull
    @Override
    public JSONArray getInterstitialRequestInfo(@NonNull JSONObject adapterSettings) {
        return getRequestInfoFromSettings(adapterSettings);
    }

    @NonNull
    @Override
    public JSONArray getRewardedVideoRequestInfo(@NonNull JSONObject adapterSettings) {
        return getRequestInfoFromSettings(adapterSettings);
    }

    @NonNull
    @Override
    public JSONArray getNativeRequestInfo(@NonNull JSONObject adapterSettings) {
        return getRequestInfoFromSettings(adapterSettings);
    }

    @Override
    public void loadBanner(@NonNull Activity activity, @NonNull BannerView bannerView, JSONObject adInfo, BannerListener bannerListener) {
        try {
            int slotId = adInfo.getInt("slot_id");
            String bidId = adInfo.getString("bid_id");

            if (!TextUtils.isEmpty(bidId)) {
                MailruBanner mailruBanner = new MailruBanner(bannerView, bannerListener);
                mailruBanner.load(activity, slotId, bidId);
            } else {
                bannerListener.onBannerFailedToLoad(AdError.InternalError);
            }
        } catch (Exception e) {
            bannerListener.onBannerFailedToLoad(AdError.InternalError);
        }
    }

    @Override
    public void loadInterstitial(@NonNull Activity activity, JSONObject adInfo, FullScreenAdListener fullScreenAdListener) {
        loadFullscreenAd(activity, adInfo, fullScreenAdListener);
    }

    @Override
    public void loadRewardedVideo(@NonNull Activity activity, JSONObject adInfo, FullScreenAdListener fullScreenAdListener) {
        loadFullscreenAd(activity, adInfo, fullScreenAdListener);
    }

    @Override
    public void loadNative(@NonNull Activity activity, JSONObject adInfo, NativeListener nativeListener) {
        try {
            int slotId = adInfo.getInt("slot_id");
            String bidId = adInfo.getString("bid_id");

            if (!TextUtils.isEmpty(bidId)) {
                MailruNative mailruNative = new MailruNative(nativeListener);
                mailruNative.load(activity, slotId, bidId);
            } else {
                nativeListener.onNativeFailedToLoad(AdError.InternalError);
            }
        } catch (Exception e) {
            nativeListener.onNativeFailedToLoad(AdError.InternalError);
        }
    }

    @Override
    public void updateConsent(Activity activity, boolean hasConsent, boolean isGDPRScope) {
        MyTargetPrivacy.setUserConsent(hasConsent);
    }


    @NonNull
    private JSONArray getRequestInfoFromSettings(JSONObject settings) {
        JSONArray adTypeInfo = new JSONArray();
        JSONObject info = new JSONObject();

        try {
            JSONObject appodealInfo = new JSONObject();
            appodealInfo.put(RequestInfoKeys.APPODEAL_ID, settings.getString("id"));
            appodealInfo.put(RequestInfoKeys.APPODEAL_ECPM, settings.getDouble("ecpm"));

            JSONObject ext = new JSONObject();
            ext.put("slot_id", settings.getInt("mailru_slot_id"));

            JSONObject adUnitExt = settings.optJSONObject(RequestInfoKeys.EXTRA_PARALLEL_BIDDING_INFO);
            if (adUnitExt != null) {
                Iterator<?> keys = adUnitExt.keys();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    ext.put(key, adUnitExt.get(key));
                }
            }

            info.put(RequestInfoKeys.DISPLAY_MANAGER, "mailru");
            info.put(RequestInfoKeys.DISPLAY_MANAGER_VERSION, MyTargetVersion.VERSION);
            info.put(RequestInfoKeys.APPODEAL_INFO, appodealInfo);
            info.put(RequestInfoKeys.EXT, ext);
        } catch (JSONException e) {
            Log.e("Appodealx-Mailru", e.getMessage());
        }

        adTypeInfo.put(info);

        return adTypeInfo;
    }

    private void loadFullscreenAd(@NonNull Activity activity, JSONObject adInfo, FullScreenAdListener fullScreenAdListener) {
        try {
            int slotId = adInfo.getInt("slot_id");
            String bidId = adInfo.getString("bid_id");

            if (!TextUtils.isEmpty(bidId)) {
                MailruFullScreenAd mailruFullScreenAd = new MailruFullScreenAd(fullScreenAdListener);
                mailruFullScreenAd.load(activity, slotId, bidId);
            } else {
                fullScreenAdListener.onFullScreenAdFailedToLoad(AdError.InternalError);
            }
        } catch (Exception e) {
            fullScreenAdListener.onFullScreenAdFailedToLoad(AdError.InternalError);
        }
    }

}