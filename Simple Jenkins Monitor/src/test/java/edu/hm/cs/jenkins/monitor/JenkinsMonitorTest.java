package edu.hm.cs.jenkins.monitor;

import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import javax.inject.Inject;
import edu.hm.cs.jenkins.monitor.controller.util.FragmentUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.*;

/**
 * Tests the class {@link edu.hm.cs.jenkins.monitor.JenkinsMonitor}.
 * @author Tobias Wochinger
 */
@RunWith(RobolectricTestRunner.class)
public class JenkinsMonitorTest extends BaseTest {

    @Mock
    private FragmentUtil fragmentUtil;

    @Mock
    private Thread.UncaughtExceptionHandler handler;

    @Inject
    private JenkinsMonitor activity;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
    }

    /**
     * Tests the selecting of the options item for the settings.
     */
    @Test
    public void testSettingsSelected() {
        MenuItem item = mock(MenuItem.class);
        when(item.getItemId()).thenReturn(R.id.settings);

        activity.onOptionsItemSelected(item);

        verify(fragmentUtil).showSettingsFragment();
    }

    /**
     * Tests the selection of an options item which is not the settings item.
     */
    @Test
    public void testOtherItemSelected() {
        MenuItem item = mock(MenuItem.class);
        when(item.getItemId()).thenReturn(-1);

        activity.onOptionsItemSelected(item);

        verifyZeroInteractions(fragmentUtil);
    }

    /**
     * Tests that the jobs fragment is shown, when the back key is pressed.
     */
    @Test
    public void testOnBackPressed() {
        activity.onBackPressed();

        verify(fragmentUtil).showJobsFragment();
    }

    private class TestModule extends BasicTestModule {

        @Override
        protected void configure() {
            bind(FragmentUtil.class).toInstance(fragmentUtil);
            bind(FragmentManager.class).toInstance(getFragmentManager());
            bind(Thread.UncaughtExceptionHandler.class).toInstance(handler);
        }
    }
}
