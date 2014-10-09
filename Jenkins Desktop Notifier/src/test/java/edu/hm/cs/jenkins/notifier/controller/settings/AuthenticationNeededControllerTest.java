package edu.hm.cs.jenkins.notifier.controller.settings;

import edu.hm.cs.jenkins.notifier.BasicTestModule;
import edu.hm.cs.jenkins.notifier.BaseTest;
import edu.hm.cs.jenkins.notifier.view.settings.Settings;
import edu.hm.cs.jenkins.notifier.view.settings.SettingsView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;
import java.awt.event.ActionEvent;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests the class {@link edu.hm.cs.jenkins.notifier.controller.settings
 * .AuthenticationNeededController}.
 *
 * @author Tobias Wochinger
 */
public class AuthenticationNeededControllerTest extends BaseTest {

    @Mock
    private Settings settings;

    @Mock
    private SettingsView settingsView;

    @Mock
    private ActionEvent event;

    @Inject
    private AuthenticationNeededController controller;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
    }

    /**
     * Tests when the checkbox is selected (= authentication is needed).
     */
    @Test
    public void authenticationNeededSelected() {
        when(settingsView.isAuthenticationNeeded()).thenReturn(true);

        controller.actionPerformed(event);

        verify(settingsView).setCredentialFieldsEnabled(true);
    }

    /**
     * Tests when the checkbox is deselected (= authentication is not needed).
     */
    @Test
    public void authenticationNeededDeselected() {
        when(settingsView.isAuthenticationNeeded()).thenReturn(false);

        controller.actionPerformed(event);

        verify(settingsView).setCredentialFieldsEnabled(false);
    }

    private class TestModule extends BasicTestModule {

        @Override
        protected void configure() {
            super.configure();
            bind(Settings.class).toInstance(settings);
            bind(SettingsView.class).toInstance(settingsView);
        }
    }
}
