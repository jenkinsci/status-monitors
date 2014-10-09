package edu.hm.cs.jenkins.notifier.controller.util;

import edu.hm.cs.jenkins.notifier.BaseTest;
import edu.hm.cs.jenkins.notifier.BasicTestModule;
import edu.hm.cs.jenkins.notifier.view.settings.SettingsView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Tests the class {@link edu.hm.cs.jenkins.notifier.controller.util.ViewedCredentialsLoader}.
 *
 * @author Tobias Wochinger
 */
public class ViewedCredentialsLoaderTest extends BaseTest {

    private static final String TEST_STRING = "some Text";

    @Mock
    private SettingsView settingsView;

    @Inject
    private ViewedCredentialsLoader loader;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
    }

    /**
     * Tests the retrieving of the hostname.
     */
    @Test
    public void testGetHostname() {
        when(settingsView.getHostname()).thenReturn(TEST_STRING);

        String actual = loader.getHostname();

        assertEqualsTestString(actual);
    }

    private void assertEqualsTestString(final String actual) {
        assertEquals("Value must be corrected loaded", TEST_STRING, actual);
    }

    /**
     * Tests the retrieving of the port.
     */
    @Test
    public void testGetPort() {
        when(settingsView.getPort()).thenReturn(TEST_STRING);

        String actual = loader.getPort();

        assertEqualsTestString(actual);
    }

    /**
     * Tests the retrieving whether authentication is needed, when it actual needed.
     */
    @Test
    public void testAuthenticationNeeded() {
        when(settingsView.isAuthenticationNeeded()).thenReturn(true);

        boolean actual = loader.isAuthenticationNeeded();

        assertTrue("Authentication is needed", actual);
    }

    /**
     * Tests the retrieving whether authentication is needed, when it actual not needed.
     */
    @Test
    public void testAuthenticationNotNeeded() {
        when(settingsView.isAuthenticationNeeded()).thenReturn(false);

        boolean actual = loader.isAuthenticationNeeded();

        assertFalse("Authentication is not needed", actual);
    }

    /**
     * Tests the retrieving of the username.
     */
    @Test
    public void testGetUsername() {
        when(settingsView.getUsername()).thenReturn(TEST_STRING);

        String actual = loader.getUsername();

        assertEqualsTestString(actual);
    }

    /**
     * Tests the retrieving of the token.
     */
    @Test
    public void testGetToken() {
        when(settingsView.getToken()).thenReturn(TEST_STRING);

        String actual = loader.getToken();

        assertEqualsTestString(actual);
    }

    private class TestModule extends BasicTestModule {

        @Override
        protected void configure() {
            super.configure();
            bindSettings();
            bind(SettingsView.class).toInstance(settingsView);
        }
    }
}
