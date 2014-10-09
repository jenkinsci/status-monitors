package edu.hm.cs.jenkins.notifier.controller.menu;

import edu.hm.cs.jenkins.notifier.BaseTest;
import edu.hm.cs.jenkins.notifier.SwingTestModule;
import edu.hm.cs.jenkins.notifier.controller.TrayApplicationController;
import edu.hm.cs.jenkins.notifier.controller.util.ApplicationCloser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;
import java.awt.*;
import java.awt.event.ActionEvent;

import static org.mockito.Mockito.verify;

/**
 * Tests the class {@link edu.hm.cs.jenkins.notifier.controller.menu.ExitController}.
 *
 * @author Tobias Wochinger
 */
public class ExitControllerTest extends BaseTest {

    @Mock
    private TrayIcon icon;

    @Mock
    private TrayApplicationController trayApplicationController;

    @Mock
    private ActionEvent event;

    @Mock
    private ApplicationCloser closer;

    @Inject
    private ExitController controller;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
    }

    /**
     * Tests the correct exiting of application when the user chose it in the
     * menu.
     */
    @Test
    public void testExitClicked() {
        controller.actionPerformed(event);
        verify(closer).exitApplication();
        verify(trayApplicationController).removeTrayIcon();
    }

    private class TestModule extends SwingTestModule {

        @Override
        protected void configure() {
            super.configure();
            bindTrayIcon();
            bind(TrayApplicationController.class).toInstance(trayApplicationController);
            bind(ApplicationCloser.class).toInstance(closer);
        }
    }
}
