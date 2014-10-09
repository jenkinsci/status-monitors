package edu.hm.cs.jenkins.notifier.controller.settings;

import com.google.inject.name.Names;
import edu.hm.cs.jenkins.notifier.BasicTestModule;
import edu.hm.cs.jenkins.notifier.BaseTest;
import edu.hm.cs.jenkins.notifier.view.settings.SettingsView;
import edu.hm.cs.jenkins.web.util.CredentialsLoader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests the class {@link edu.hm.cs.jenkins.notifier.controller.settings
 * .SettingsInitializeController}.
 *
 * @author Tobias Wochinger
 */
public class SettingsInitializeControllerTest extends BaseTest {

    @Mock
    private SettingsView view;

    @Mock
    private CredentialsLoader credentialsLoader;

    @Inject
    private SettingsInitializeController controller;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
    }

    /**
     * Tests the setting of the initial values of input fields.
     */
    @Test
    public void testSetInitialValues() {
        String hostname = "host";
        String port = "1234";
        boolean authenticationNeeded = true;
        String username = "name";
        String token = "av2323";
        setUpCredentials(hostname, port, authenticationNeeded, username, token);

        controller.initializeView();

        verifyInitialValues(hostname, port, authenticationNeeded, username, token);

    }

    private void setUpCredentials(final String hostname, final String port, final boolean authenticationNeeded,
                                  final String username, final String token) {
        when(credentialsLoader.getHostname()).thenReturn(hostname);
        when(credentialsLoader.getPort()).thenReturn(port);
        when(credentialsLoader.isAuthenticationNeeded()).thenReturn(authenticationNeeded);
        when(credentialsLoader.getUsername()).thenReturn(username);
        when(credentialsLoader.getToken()).thenReturn(token);
    }

    private void verifyInitialValues(final String hostname, final String port, final boolean authenticationNeeded,
                                     final String username, final String token) {
        verify(view).setHostname(hostname);
        verify(view).setPort(port);
        verify(view).setAuthenticationNeeded(authenticationNeeded);
        verify(view).setUsername(username);
        verify(view).setToken(token);
    }

    private class TestModule extends BasicTestModule {

        @Override
        protected void configure() {
            super.configure();
            bindSettings();
            bind(SettingsView.class).toInstance(view);
            bind(CredentialsLoader.class).annotatedWith(Names.named("stored")).toInstance(credentialsLoader);
        }
    }
}
