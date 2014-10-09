package edu.hm.cs.jenkins.notifier.controller.communication;

import com.google.inject.TypeLiteral;
import edu.hm.cs.jenkins.notifier.SwingTestModule;
import edu.hm.cs.jenkins.notifier.BaseTest;
import edu.hm.cs.jenkins.web.ClientFactory;
import edu.hm.cs.jenkins.web.JenkinsClientFactory;
import edu.hm.cs.jenkins.web.client.JobClient;
import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.model.Job;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Tests the class {@link edu.hm.cs.jenkins.notifier.controller
 * .communication.BuildCompletionChecker}.
 *
 * @author Tobias Wochinger
 */
public class BuildCompletionCheckerTest extends BaseTest {

    private static final int TIMEOUT_IN_MS = 1000;

    @Mock
    private JenkinsClientFactory clientFactory;

    @Mock
    private ClientCallback<List<Job>> jobController;

    @Mock
    private JobClient jobClient;

    @Inject
    private BuildCompletionChecker checker;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
        configureClientFactoryMock();
    }

    private void configureClientFactoryMock() {
        when(clientFactory.createJobClient()).thenReturn(jobClient);
    }

    /**
     * Tests the starting of the job.
     */
    @Test
    public void testStart() {
        checker.start();

        verify(clientFactory).createJobClient();
        verify(jobClient, timeout(TIMEOUT_IN_MS)).getAllJobs(jobController);
    }

    /**
     * Tests the refreshing of the clients.
     */
    @Test
    public void testRefresh() {
        checker.refreshClient();

        verify(clientFactory).createJobClient();
    }

    private class TestModule extends SwingTestModule {

        @Override
        protected void configure() {
            super.configure();
            bind(new TypeLiteral<ClientCallback<List<Job>>>() { }).toInstance(jobController);
            bind(ClientFactory.class).toInstance(clientFactory);
        }
    }
}
