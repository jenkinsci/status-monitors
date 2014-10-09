package edu.hm.cs.jenkins.notifier.controller.menu;

import edu.hm.cs.jenkins.notifier.BasicTestModule;
import edu.hm.cs.jenkins.notifier.BaseTest;
import edu.hm.cs.jenkins.notifier.view.dialog.AboutDialog;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests the class {@link edu.hm.cs.jenkins.notifier.controller.menu
 * .AboutController}.
 *
 * @author Tobias Wochinger
 */
public class AboutControllerTest extends BaseTest {

    @Mock
    private AboutDialog dialog;

    @Mock
    private ResourceBundle bundle;

    @Inject
    private AboutController controller;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
    }

    /**
     * Tests the displaying of the about dialog.
     */
    @Test
    public void testDialogShownIfEvent() {
        ActionEvent mocked = mock(ActionEvent.class);

        controller.actionPerformed(mocked);

        verify(dialog).show();
    }

    private class TestModule extends BasicTestModule {

        @Override
        protected void configure() {
            super.configure();
            bind(AboutDialog.class).toInstance(dialog);
        }
    }
}
