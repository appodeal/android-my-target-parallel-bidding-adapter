package com.appodealx.mailru;

import com.appodealx.sdk.AdError;
import com.appodealx.sdk.NativeListener;
import com.my.target.nativeads.NativeAd;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class MailruNativeListenerTest {

    private NativeListener nativeListener;
    private MailruNative mailruNative;
    private MailruNativeListener mailruNativeListener;
    private NativeAd nativeAd;

    @Before
    public void setUp() throws Exception {
        nativeListener = mock(NativeListener.class);
        mailruNative = spy(new MailruNative(nativeListener));
        mailruNativeListener = spy(new MailruNativeListener(mailruNative, nativeListener));

        nativeAd = new NativeAd(0, RuntimeEnvironment.application);
    }

    @Test
    public void onLoad() {
        mailruNativeListener.onLoad(nativeAd);

        verify(nativeListener).onNativeLoaded(mailruNative);
    }

    @Test
    public void onNoAd() {
        mailruNativeListener.onNoAd("Error", nativeAd);

        verify(nativeListener).onNativeFailedToLoad(AdError.NoFill);
    }

}