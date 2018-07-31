package com.appodealx.mailru;

import com.appodealx.sdk.AdError;
import com.appodealx.sdk.BannerListener;
import com.appodealx.sdk.BannerView;
import com.my.target.ads.MyTargetView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class MailruBannerListenerTest {

    private BannerListener bannerListener;
    private MailruBanner mailruBanner;
    private MailruBannerListener mailruBannerListener;
    private MyTargetView myTargetView;

    @Before
    public void setUp() throws Exception {
        bannerListener = mock(BannerListener.class);
        mailruBanner = spy(new MailruBanner(mock(BannerView.class), bannerListener));
        mailruBannerListener = new MailruBannerListener(mailruBanner, bannerListener);
        myTargetView = new MyTargetView(RuntimeEnvironment.application);
    }

    @Test
    public void onLoad() throws Exception {
        mailruBannerListener.onLoad(myTargetView);

        verify(mailruBanner).fillContainer();
    }

    @Test
    public void onNoAd() throws Exception {
        mailruBannerListener.onNoAd("Error", myTargetView);

        verify(bannerListener).onBannerFailedToLoad(AdError.NoFill);
    }

    @Test
    public void onClick() throws Exception {
        mailruBannerListener.onClick(myTargetView);

        verify(bannerListener).onBannerClicked();
    }

}