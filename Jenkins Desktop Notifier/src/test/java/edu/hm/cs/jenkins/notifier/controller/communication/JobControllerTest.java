package edu.hm.cs.jenkins.notifier.controller.communication;

import com.google.inject.TypeLiteral;
import edu.hm.cs.jenkins.notifier.SwingTestModule;
import edu.hm.cs.jenkins.notifier.BaseTest;
import edu.hm.cs.jenkins.notifier.controller.notification.NotificationController;
import edu.hm.cs.jenkins.web.ClientFactory;
import edu.hm.cs.jenkins.web.JenkinsClientFactory;
import edu.hm.cs.jenkins.web.client.BuildClient;
import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.model.Job;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Tests the class {@link edu.hm.cs.jenkins.notifier.controller
 * .communication.JobController}.
 *
 * @author Tobias Wochinger
 */
public class JobControllerTest extends BaseTest {

    @Mock
    private JenkinsClientFactory clientFactory;

    @Mock
    private ClientCallback<Job> buildController;

    @Mock
    private BuildClient buildClient;

    @Mock
    private Job exampleJob;

    @Mock
    private NotificationController notificationController;

    @Inject
    private JobController jobController;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
        configureClientFactoryMock();
    }

    private void configureClientFactoryMock() {
        when(clientFactory.createBuildClient()).thenReturn(buildClient);
    }

    /**
     * Tests the receiving of zero jobs.
     */
    @Test
    public void testSuccessWithZeroJobs() {
        List<Job> retrievedJobs = createJobList();
        jobController.onSuccess(retrievedJobs);

        verifyNoMoreInteractions(buildClient);
    }

    /**
     * Tests the receiving of one job.
     */
    @Test
    public void testSuccessWithOneJob() {
        List<Job> retrievedJobs = createJobList(exampleJob);

        jobController.onSuccess(retrievedJobs);

        verify(buildClient, times(1)).getLastBuild(exampleJob, buildController);
    }

    private List<Job> createJobList(final Job... job) {
        return Arrays.<Job>asList(job);
    }

    /**
     * Tests the receiving of more than one jobs.
     */
    @Test
    public void testSuccessWithMoreThanOneJobs() {
        List<Job> retrievedJobs = createJobList(exampleJob, exampleJob, exampleJob);

        jobController.onSuccess(retrievedJobs);

        verify(buildClient, times(3)).getLastBuild(exampleJob, buildController);
    }

    /**
     * Tests that a notification is shown in case of an error.
     */
    @Test
    public void testErrorCase() {
        //ResourceBundle cannot be mocked with Mockito (final method)
        jobController.onError(null);

        verify(notificationController).show(anyString());
    }

    private class TestModule extends SwingTestModule {

        @Override
        protected void configure() {
            super.configure();
            bind(new TypeLiteral<ClientCallback<Job>>() { }).toInstance(buildController);
            bind(ClientFactory.class).toInstance(clientFactory);
            bind(NotificationController.class).toInstance(notificationController);
        }
    }
}
