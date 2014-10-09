package edu.hm.cs.jenkins.notifier.controller.util;

import edu.hm.cs.jenkins.notifier.BaseTest;
import edu.hm.cs.jenkins.notifier.BasicTestModule;
import edu.hm.cs.jenkins.notifier.view.settings.SettingsView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;
import java.util.prefs.Preferences;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests the class {@link edu.hm.cs.jenkins.notifier.controller.util.CredentialsSaver}.
 *
 * @author Tobias Wochinger
 */
public class CredentialsSaverTest extends BaseTest {

    private static final String TEST_STRING = "any String";

    @Mock
    private Preferences preferences;

    @Mock
    private SettingsView view;

    @Inject
    private CredentialsSaver saver;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
    }

    /**
     * Tests the correct saving of the values.
     */
    @Test
    public void testSaveSettings() {
        when(view.getHostname()).thenReturn(TEST_STRING);
        when(view.getPort()).thenReturn(TEST_STRING);
        when(view.isAuthenticationNeeded()).thenReturn(true);
        when(view.getUsername()).thenReturn(TEST_STRING);
        when(view.getToken()).thenReturn(TEST_STRING);

        saver.saveSettings();

        assertCorrectSaving(CredentialsSaver.HOSTNAME_KEY, TEST_STRING);
        assertCorrectSaving(CredentialsSaver.PORT_KEY, TEST_STRING);
        assertCorrectSaving(CredentialsSaver.USERNAME_KEY, TEST_STRING);
        assertCorrectSaving(CredentialsSaver.TOKEN_KEY, TEST_STRING);
        verify(preferences).putBoolean(CredentialsSaver.AUTHENTICATION_NEEDED_KEY, true);
    }

    private void assertCorrectSaving(final String key, final String expected) {
        verify(preferences).put(key, expected);
    }

    private class TestModule extends BasicTestModule {

        @Override
        protected void configure() {
            super.configure();
            bindSettings();
            bind(SettingsView.class).toInstance(view);
            bind(Preferences.class).toInstance(preferences);

        }
    }
}
