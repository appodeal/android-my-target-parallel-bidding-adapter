package com.appodealx.mailru;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.appodealx.sdk.AdError;
import com.appodealx.sdk.FullScreenAd;
import com.appodealx.sdk.FullScreenAdListener;
import com.my.target.ads.InterstitialAd;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class MailruFullScreenAdListenerTest {

    private FullScreenAd fullScreenAd;
    private FullScreenAdListener fullScreenAdListener;
    private MailruFullScreenAdListener mailruFullScreenAdListener;
    private InterstitialAd interstitialAd;

    @Before
    public void setUp() throws Exception {
        fullScreenAd = spy(new FullScreenAd() {
            @Override
            public void show(@NonNull Activity activity) {

            }
        });
        fullScreenAdListener = mock(FullScreenAdListener.class);
        mailruFullScreenAdListener = new MailruFullScreenAdListener(fullScreenAd, fullScreenAdListener);
        interstitialAd = new InterstitialAd(0, RuntimeEnvironment.application);
    }

    @Test
    public void onLoad() {
        mailruFullScreenAdListener.onLoad(interstitialAd);

        verify(fullScreenAdListener).onFullScreenAdLoaded(fullScreenAd);
    }

    @Test
    public void onNoAd() {
        mailruFullScreenAdListener.onNoAd("Error", interstitialAd);

        verify(fullScreenAdListener).onFullScreenAdFailedToLoad(AdError.NoFill);
    }

    @Test
    public void onClick() {
        mailruFullScreenAdListener.onClick(interstitialAd);

        verify(fullScreenAdListener).onFullScreenAdClicked();
    }

    @Test
    public void onDismiss_finishedFalse() {
        mailruFullScreenAdListener.onDismiss(interstitialAd);

        verify(fullScreenAdListener).onFullScreenAdClosed(false);
    }

    @Test
    public void onDismiss_finishedTrue() {
        mailruFullScreenAdListener.onVideoCompleted(interstitialAd);
        mailruFullScreenAdListener.onDismiss(interstitialAd);

        verify(fullScreenAdListener).onFullScreenAdClosed(true);
    }

    @Test
    public void onVideoCompleted() {
        mailruFullScreenAdListener.onVideoCompleted(interstitialAd);

        verify(fullScreenAdListener).onFullScreenAdCompleted();
    }

    @Test
    public void onDisplay() {
        mailruFullScreenAdListener.onDisplay(interstitialAd);

        verify(fullScreenAdListener).onFullScreenAdShown();
    }

}