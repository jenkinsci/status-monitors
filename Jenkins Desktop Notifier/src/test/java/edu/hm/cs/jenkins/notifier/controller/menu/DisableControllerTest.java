package edu.hm.cs.jenkins.notifier.controller.menu;

import edu.hm.cs.jenkins.notifier.SwingTestModule;
import edu.hm.cs.jenkins.notifier.BaseTest;
import edu.hm.cs.jenkins.notifier.controller.communication.BuildCompletionChecker;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;
import java.awt.event.ItemEvent;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests the class {@link edu.hm.cs.jenkins.notifier.controller.menu
 * .DisableController}.
 *
 * @author Tobias Wochinger
 */
public class DisableControllerTest extends BaseTest {

    @Mock
    private BuildCompletionChecker job;

    @Mock
    private ItemEvent event;

    @Inject
    private DisableController controller;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
    }

    /**
     * Tests the disabling of the job when the user clicked in the menu.
     */
    @Test
    public void testDisableSelected() {
        when(event.getStateChange()).thenReturn(ItemEvent.SELECTED);

        controller.itemStateChanged(event);

        verify(job).stop();
    }

    /**
     * Tests the restarting of the job when the user clicked in the menu.
     */
    @Test
    public void testDisableDeselected() {
        when(event.getStateChange()).thenReturn(ItemEvent.DESELECTED);

        controller.itemStateChanged(event);

        verify(job).start();
    }

    private class TestModule extends SwingTestModule {

        @Override
        protected void configure() {
            super.configure();
            bind(BuildCompletionChecker.class).toInstance(job);
        }
    }
}
