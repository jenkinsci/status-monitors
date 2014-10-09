package edu.hm.cs.jenkins.monitor;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import org.junit.After;
import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;

/**
 * Allows mock creation via Annotations and creates the application context.
 *
 * @author Tobias Wochinger
 */
public class BaseTest {

    private FragmentActivity activity;

    /**
     * Sets up the test environment for every test.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        activity = Robolectric.buildActivity(JenkinsMonitor.class).
                create().get();
    }

    /**
     * Inject members with a test binding module.
     *
     * @param testModule module with bindings
     */
    public final void injectMembers(final Module testModule) {
        RoboGuice.setBaseApplicationInjector(Robolectric.application, RoboGuice.DEFAULT_STAGE,
                Modules.override(RoboGuice.newDefaultRoboModule(Robolectric.application)).with(testModule));

        injectMembers();
    }

    /**
     * Inject members and use no special module.
     */
    public final void injectMembers() {
        RoboInjector injector = RoboGuice.getInjector(activity);
        injector.injectMembersWithoutViews(this);
    }

    /**
     * Resets the test environment after each test.
     */
    @After
    public void tearDown() {
        RoboGuice.util.reset();
    }

    /**
     * Gets a resource and parse it to a string.
     *
     * @param resourceId id of the resource
     * @return resource as String
     */
    public final String getString(final int resourceId) {
        return activity.getString(resourceId);
    }

    /**
     * Returns the activity in whose context was tested.
     * @return activity
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * Provides the current fragmentManager.
     * @return fragmentManager
     */
    protected FragmentManager getFragmentManager() {
        return activity.getSupportFragmentManager();
    }

    /**
     * Returns a requested system service.
     * @param nameOfService name of the service as string
     * @return SystemService
     */
    protected Object getSystemService(final String nameOfService) {
        return activity.getSystemService(nameOfService);
    }
}
