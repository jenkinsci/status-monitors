package edu.hm.cs.jenkins.monitor.controller.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.app.FragmentManager;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import edu.hm.cs.jenkins.monitor.BaseTest;
import edu.hm.cs.jenkins.monitor.R;
import edu.hm.cs.jenkins.monitor.view.dialog.Dialog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowConnectivityManager;
import org.robolectric.shadows.ShadowNetworkInfo;

import javax.inject.Inject;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.robolectric.Robolectric.shadowOf;

/**
 * Tests the class
 * {@link edu.hm.cs.jenkins.monitor.controller.util.NetworkUtil}.
 */
@RunWith(RobolectricTestRunner.class)
public class NetworkUtilTest extends BaseTest {

    @Inject
    private NetworkUtil networkUtil;

    @Mock
    private Dialog errorDialog;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
    }

    /**
     * Test util if networkInfo is null.
     */
    @Test
    public void testNetworkInfoNull() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        ShadowConnectivityManager shadowConnectivityManager = shadowOf(manager);
        shadowConnectivityManager.setActiveNetworkInfo(null);

        assertFalse("Network is not available", networkUtil.isNetworkAvailable());
    }

    /**
     * Test util if networkInfo is says it is not connected.
     */
    @Test
    public void testNotConnected() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        ShadowConnectivityManager shadowConnectivityManager = shadowOf(manager);
        ShadowNetworkInfo shadowOfActiveNetworkInfo = shadowOf(shadowConnectivityManager.getActiveNetworkInfo());
        shadowOfActiveNetworkInfo.setConnectionStatus(false);

        assertFalse("Network is not available", networkUtil.isNetworkAvailable());
    }

    /**
     * Test util if network is connected.
     */
    @Test
    public void testConnected() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        ShadowConnectivityManager shadowConnectivityManager = shadowOf(manager);
        ShadowNetworkInfo shadowOfActiveNetworkInfo = shadowOf(shadowConnectivityManager.getActiveNetworkInfo());
        shadowOfActiveNetworkInfo.setConnectionStatus(true);

        assertTrue("Network is available", networkUtil.isNetworkAvailable());
    }

    /**
     * Test if the correct error dialog is shown.
     */
    @Test
    public void testShowNetworkErrorAlert() {
        networkUtil.showAlert();

        verify(errorDialog).show(R.string.network_error);
    }

    private class TestModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(FragmentManager.class).toInstance(getFragmentManager());
            bind(Dialog.class).annotatedWith(Names.named("silent")).toInstance(errorDialog);
            bind(Dialog.class).annotatedWith(Names.named("striking")).toInstance(errorDialog);
        }
    }
}