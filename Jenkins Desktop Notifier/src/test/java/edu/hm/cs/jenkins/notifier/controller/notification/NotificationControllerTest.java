package edu.hm.cs.jenkins.notifier.controller.notification;

import edu.hm.cs.jenkins.notifier.BasicTestModule;
import edu.hm.cs.jenkins.notifier.BaseTest;
import edu.hm.cs.jenkins.notifier.view.notification.NotificationView;
import edu.hm.cs.jenkins.web.model.Result;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Tests the class {@link edu.hm.cs.jenkins.notifier.controller.notification
 * .NotificationController}.
 *
 * @author Tobias Wochinger
 */
public class NotificationControllerTest extends BaseTest {

    private static final int SMALL_THREADING_TIMEOUT = 100;

    @Mock
    private NotificationFactory factory;

    @Mock
    private NotificationView view;

    @Inject
    private NotificationController controller;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
        configureFactoryMock();
    }

    private void configureFactoryMock() {
        when(factory.createNotificationView(anyInt())).thenReturn(view);
    }

    /**
     * Tests the correct displaying of a job finished notification.
     */
    @Test
    public void testJobFinishedNotification() {
        String jobName = "job";
        Result result = Result.FAILURE;
        controller.show(jobName, result);

        verify(factory, timeout(SMALL_THREADING_TIMEOUT)).createNotificationView(anyInt());
        verify(view, timeout(SMALL_THREADING_TIMEOUT)).setMessage(anyString(), eq(result.name()));
        verify(view, timeout(SMALL_THREADING_TIMEOUT)).setVisible(true);
    }

    /**
     * Tests the correct displaying of an error notification.
     */
    @Test
    public void testErrorNotification() {
        String error = "error";
        controller.show(error);

        verify(factory, timeout(SMALL_THREADING_TIMEOUT)).createNotificationView(anyInt());
        verify(view, timeout(SMALL_THREADING_TIMEOUT)).setMessage(anyString(), eq(error));
        verify(view, timeout(SMALL_THREADING_TIMEOUT)).setVisible(true);
    }

    /**
     * Tests the correct disappearing of a notification.
     */
    @Test
    public void testDisappearing() {
        controller.show("any message");

        verify(view, timeout(SMALL_THREADING_TIMEOUT)).setVisible(true);
        verify(view, timeout(NotificationController.VISIBLE_TIME_IN_MS + SMALL_THREADING_TIMEOUT)).dispose();
    }

    private class TestModule extends BasicTestModule {

        @Override
        protected void configure() {
            super.configure();
            bind(NotificationFactory.class).toInstance(factory);
        }
    }
}
