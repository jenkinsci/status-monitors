package edu.hm.cs.jenkins.monitor.controller.settings.listener;

import android.preference.Preference;
import edu.hm.cs.jenkins.monitor.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

/**
 * Tests the class {@link HostnameChangeListener}.
 *
 * @author Tobias Wochinger
 */
@RunWith(RobolectricTestRunner.class)
public class HostnameChangeListenerTest extends BasePreferenceChangeListenerTest {

    @Before
    @Override
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
    }

    /**
     * Tests the listener with a new valid value.
     */
    @Test
    public void testValidValue() {
        testValidValue(R.string.hostname_key);
    }

    /**
     * Tests the listener with a new invalid value.
     */
    @Test
    public void testInvalidValue() {
        testInvalidValue(R.string.hostname_key, R.string.invalid_hostname);
    }

    private class TestModule extends PreferenceTestModule {
        @Override
        protected void configure() {
            super.configure();
            bind(Preference.OnPreferenceChangeListener.class).
                    to(HostnameChangeListener.class);
        }
    }
}
