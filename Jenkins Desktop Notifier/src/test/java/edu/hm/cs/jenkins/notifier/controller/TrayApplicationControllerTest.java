package edu.hm.cs.jenkins.notifier.controller;

import com.google.inject.util.Providers;
import edu.hm.cs.jenkins.notifier.SwingTestModule;
import edu.hm.cs.jenkins.notifier.BaseTest;
import edu.hm.cs.jenkins.notifier.controller.
        communication.BuildCompletionChecker;
import edu.hm.cs.jenkins.notifier.view.dialog.ApplicationErrorDialog;
import edu.hm.cs.jenkins.web.util.ValidationUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;
import java.awt.*;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Tests the class {@link edu.hm.cs.jenkins.notifier.controller
 * .TrayApplicationController}.
 *
 * @author Tobias Wochinger
 */
public class TrayApplicationControllerTest extends BaseTest {

    @Mock
    private SystemTray tray;

    @Mock
    private TrayIcon icon;

    @Mock
    private BuildCompletionChecker job;

    @Mock
    private ApplicationErrorDialog error;

    @Mock
    private ValidationUtil validations;

    @Inject
    private TrayApplicationController controller;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
    }

    /**
     * Tests that the case when the system supports the tray bar and there
     * are valid settings.
     *
     * @throws AWTException if the desktop system tray is missing
     */
    @Test
    public void testIfTraySupportedAndCredentialsValid() throws AWTException {
        when(validations.areCredentialsValid()).thenReturn(true);

        controller.start();

        verify(job).start();
        verify(tray).add(icon);
    }

    /**
     * Tests that the case when the system supports the tray bar,
     * but there are no valid settings.
     *
     * @throws AWTException if the desktop system tray is missing
     */
    @Test
    public void testIfTraySupportedAndCredentialsInvalid() throws AWTException {
        when(validations.areCredentialsValid()).thenReturn(false);

        controller.start();

        verifyZeroInteractions(job);
        verify(error).show(anyString());
        verify(tray).add(icon);
    }

    /**
     * Tests that the case when the tray is not supported on the system.
     */
    @Test
    public void testIfTrayNotSupported() {
        tray = null;
        injectMembers(new NotSupportedTrayModule());

        controller.start();

        verifyZeroInteractions(job);
        verify(error).show(anyString());
    }

    /**
     * Tests if the icon was correctly removed.
     */
    @Test
    public void testRemovingOfIcon() {
        controller.removeTrayIcon();

        verify(tray).remove(icon);
    }

    /**
     * Tests if an error is shown if the adding of the icon fails.
     * @throws AWTException if thrown it was not correctly handled and the test failed
     */
    @Test
    public void testAwtExceptionAtIconAdding() throws AWTException {
        when(validations.areCredentialsValid()).thenReturn(true);
        doThrow(AWTException.class).when(tray).add(icon);

        controller.start();

        verify(error).show(anyString());
    }

    private class TestModule extends SwingTestModule {

        @Override
        protected void configure() {
            super.configure();
            bind(SystemTray.class).toInstance(tray);
            bind(TrayIcon.class).toInstance(icon);
            bind(BuildCompletionChecker.class).toInstance(job);
            bind(ApplicationErrorDialog.class).toInstance(error);
            bind(ValidationUtil.class).toInstance(validations);
        }
    }

    private class NotSupportedTrayModule extends SwingTestModule {

        @Override
        protected void configure() {
            super.configure();
            bind(SystemTray.class).toProvider(Providers.of(tray));
            bind(TrayIcon.class).toInstance(icon);
            bind(BuildCompletionChecker.class).toInstance(job);
            bind(ApplicationErrorDialog.class).toInstance(error);
            bind(ValidationUtil.class).toInstance(validations);
        }
    }
}
