package edu.hm.cs.jenkins.monitor.controller.settings.listener;

import android.preference.Preference;
import edu.hm.cs.jenkins.monitor.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

/**
 * Tests the class {@link UsernameChangeListener}.
 *
 * @author Tobias Wochinger
 */
@RunWith(RobolectricTestRunner.class)
public class UsernameChangeListenerTest extends BasePreferenceChangeListenerTest {

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
        testValidValue(R.string.username_key);
    }

    /**
     * Tests the listener with a new invalid value.
     */
    @Test
    public void testInvalidValue() {
        testInvalidValue(R.string.username_key, R.string.invalid_username);
    }

    private class TestModule extends PreferenceTestModule {
        @Override
        protected void configure() {
            super.configure();
            bind(Preference.OnPreferenceChangeListener.class).
                    to(UsernameChangeListener.class);
        }
    }
}
