package edu.hm.cs.jenkins.notifier.controller.settings;

import edu.hm.cs.jenkins.notifier.BasicTestModule;
import edu.hm.cs.jenkins.notifier.BaseTest;
import edu.hm.cs.jenkins.notifier.view.settings.Settings;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;
import java.awt.event.ActionEvent;

import static org.mockito.Mockito.verify;

/**
 * Tests the class {@link edu.hm.cs.jenkins.notifier.controller.settings
 * .CancelSettingsController}.
 *
 * @author Tobias Wochinger
 */
public class CancelSettingsControllerTest extends BaseTest {

    @Mock
    private Settings settings;

    @Mock
    private ActionEvent event;

    @Inject
    private CancelSettingsController controller;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
    }

    /**
     * Tests the case that the user cancels the settings.
     */
    @Test
    public void testCancelingOfSettings() {
        controller.actionPerformed(event);

        verify(settings).dispose();
    }


    private class TestModule extends BasicTestModule {

        @Override
        protected void configure() {
            super.configure();
            bindSettingsView();
            bind(Settings.class).toInstance(settings);
        }
    }
}
