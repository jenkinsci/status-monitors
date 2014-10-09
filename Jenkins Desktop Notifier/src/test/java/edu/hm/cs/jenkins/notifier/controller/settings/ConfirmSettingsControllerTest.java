package edu.hm.cs.jenkins.notifier.controller.settings;

import edu.hm.cs.jenkins.notifier.BasicTestModule;
import edu.hm.cs.jenkins.notifier.BaseTest;
import edu.hm.cs.jenkins.notifier.controller.communication.
        BuildCompletionChecker;
import edu.hm.cs.jenkins.notifier.controller.util.CredentialsSaver;
import edu.hm.cs.jenkins.notifier.view.settings.Settings;
import edu.hm.cs.jenkins.web.util.ValidationUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;
import java.awt.event.ActionEvent;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests the class {@link edu.hm.cs.jenkins.notifier.controller.settings
 * .ConfirmSettingsController}.
 *
 * @author Tobias Wochinger
 */
public class ConfirmSettingsControllerTest extends BaseTest {

    @Mock
    private Settings settings;

    @Mock
    private CredentialsSaver credentialsSaver;

    @Mock
    private BuildCompletionChecker job;

    @Mock
    private ValidationUtil validator;

    @Mock
    private ActionEvent event;

    @Inject
    private ConfirmSettingsController controller;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
    }

    /**
     * Tests the confirming of the user when all entered fields are valid and
     * the job isn't running.
     */
    @Test
    public void testWithValidSettingsAndJobStopped() {
        when(validator.areCredentialsValid()).thenReturn(true);
        when(job.isRunning()).thenReturn(false);
        controller.actionPerformed(event);

        verify(credentialsSaver).saveSettings();
        verify(settings).dispose();
        verify(job).start();
    }

    /**
     * Tests the confirming of the user when all entered fields are valid.
     */
    @Test
    public void testWithValidSettingsAndJobRunning() {
        when(validator.areCredentialsValid()).thenReturn(true);
        when(job.isRunning()).thenReturn(true);
        controller.actionPerformed(event);

        verify(credentialsSaver).saveSettings();
        verify(settings).dispose();
        verify(job).refreshClient();
    }

    /**
     * Tests the confirming of the user when some fields are invalid.
     */
    @Test
    public void testWithInvalidSettings() {
        when(validator.areCredentialsValid()).thenReturn(false);

        controller.actionPerformed(event);

        verify(validator).showFailedValidations();
    }

    private class TestModule extends BasicTestModule {

        @Override
        protected void configure() {
            super.configure();
            bindSettingsView();
            bind(Settings.class).toInstance(settings);
            bind(ValidationUtil.class).toInstance(validator);
            bind(CredentialsSaver.class).toInstance(credentialsSaver);
            bind(BuildCompletionChecker.class).toInstance(job);
        }
    }
}
