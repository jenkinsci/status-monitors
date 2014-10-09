package edu.hm.cs.jenkins.notifier.controller.menu;

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
 * Tests the class {@link edu.hm.cs.jenkins.notifier.controller.menu
 * .OpenSettingsController}.
 *
 * @author Tobias Wochinger
 */
public class OpenSettingsControllerTest extends BaseTest {

    @Mock
    private Settings settings;

    @Mock
    private ActionEvent event;

    @Inject
    private OpenSettingsController controller;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
    }

    /**
     * Tests the displaying of the settings view.
     */
    @Test
    public void testSetVisibleOnClick() {
        controller.actionPerformed(event);

        verify(settings).setVisible(true);
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
