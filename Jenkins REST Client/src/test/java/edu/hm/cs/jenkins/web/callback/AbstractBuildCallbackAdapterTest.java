package edu.hm.cs.jenkins.web.callback;


import edu.hm.cs.jenkins.web.BaseTest;
import edu.hm.cs.jenkins.web.model.BallColor;
import edu.hm.cs.jenkins.web.model.Job;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Abstract test for the tests
 * {@link edu.hm.cs.jenkins.web.callback.BuildsCallbackAdapterTest} and
 * {@link edu.hm.cs.jenkins.web.callback.LastBuildCallbackAdapterTest}.
 *
 * @author Tobias Wochinger
 */
public class AbstractBuildCallbackAdapterTest extends BaseTest {

    @Mock
    private ClientCallback<Job> callback;

    @Captor
    private ArgumentCaptor<Job> jobCaptor;

    /**
     * Creates a job object for testing purposes.
     *
     * @return job with example values
     */
    protected Job createTestJob() {
        Job job = new Job();
        job.setDisplayName("Displayname");
        job.setName("Testname");
        job.setColor(BallColor.NOTBUILT);
        job.setUrl("http://test.de");
        job.setDescription("Description");
        return job;
    }

    /**
     * Verify if a job is correctly passed through the callback.
     *
     * @param expected job which is expected to be passed
     */
    protected void verifyCorrectPassing(final Job expected) {
        verify(callback).onSuccess(jobCaptor.capture());
        Job delivered = jobCaptor.getValue();
        assertEquals("Jobs must be the same", expected, delivered);
    }

    /**
     * Returns callback.
     * @return callback
     */
    protected ClientCallback<Job> getCallback() {
        return callback;
    }
}
