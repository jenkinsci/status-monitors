package edu.hm.cs.jenkins.notifier.controller.communication;

import edu.hm.cs.jenkins.notifier.BasicTestModule;
import edu.hm.cs.jenkins.notifier.BaseTest;
import edu.hm.cs.jenkins.notifier.controller.notification.NotificationController;
import edu.hm.cs.jenkins.web.model.Build;
import edu.hm.cs.jenkins.web.model.Job;
import edu.hm.cs.jenkins.web.model.Result;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;

import static org.mockito.Mockito.*;

/**
 * Tests the class {@link edu.hm.cs.jenkins.notifier.controller
 * .communication.LastBuildController}.
 *
 * @author Tobias Wochinger
 */
public class LastBuildControllerTest extends BaseTest {

    @Mock
    private Job job;

    @Mock
    private Build lastBuild;

    @Mock
    private NotificationController notificationController;

    @Inject
    private LastBuildController controller;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
        configureJobMock();
    }

    private void configureJobMock() {
        when(job.getLastBuild()).thenReturn(lastBuild);
    }

    /**
     * Test with a last build which is not yet finished.
     */
    @Test
    public void testNotFinishedBuild() {
        when(lastBuild.getDuration()).thenReturn(0L);

        controller.onSuccess(job);

        verifyZeroInteractions(notificationController);
    }

    /**
     * Test case with completion time in future.
     */
    @Test
    public void testFinishInFuture() {
        when(lastBuild.getTimestamp()).thenReturn(System.currentTimeMillis());
        when(lastBuild.getDuration()).thenReturn(1000L);

        controller.onSuccess(job);

        verifyZeroInteractions(notificationController);
    }

    /**
     * Test case when a build is finished but this was before this check
     * interval.
     */
    @Test
    public void testFinishedInPreviousCheckInterval() {
        long duration = 5;
        long buildStartWithEndingAtLastCheck =
                System.currentTimeMillis() - BuildCompletionChecker.INTERVAL_IN_MS - duration;
        when(lastBuild.getTimestamp()).thenReturn(buildStartWithEndingAtLastCheck);
        when(lastBuild.getDuration()).thenReturn(duration);

        controller.onSuccess(job);

        verifyZeroInteractions(notificationController);
    }

    /**
     * Tests the correct recognizing if a build finished since the last time
     * checking.
     */
    @Test
    public void testFinishedInThisCheckInterval() {
        long duration = 5;
        long timeOfLastCheck = System.currentTimeMillis() - BuildCompletionChecker.INTERVAL_IN_MS;
        when(lastBuild.getTimestamp()).thenReturn(timeOfLastCheck);
        when(lastBuild.getDuration()).thenReturn(duration);

        String jobName = "test";
        Result buildResult = Result.SUCCESS;
        when(job.getDisplayName()).thenReturn(jobName);
        when(lastBuild.getResult()).thenReturn(buildResult);

        controller.onSuccess(job);

        verify(notificationController).show(jobName, buildResult);
    }


    private class TestModule extends BasicTestModule {

        @Override
        protected void configure() {
            super.configure();
            bind(NotificationController.class).toInstance(notificationController);
        }
    }
}
