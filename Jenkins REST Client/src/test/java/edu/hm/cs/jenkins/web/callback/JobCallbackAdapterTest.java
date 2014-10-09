package edu.hm.cs.jenkins.web.callback;

import edu.hm.cs.jenkins.web.BaseTest;
import edu.hm.cs.jenkins.web.callback.adapter.JobCallbackAdapter;
import edu.hm.cs.jenkins.web.model.Job;
import edu.hm.cs.jenkins.web.model.Jobs;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Tests the class {@link edu.hm.cs.jenkins.web.callback.adapter.JobCallbackAdapter}.
 *
 * @author Tobias Wochinger
 */
public class JobCallbackAdapterTest extends BaseTest {

    @Mock
    private ClientCallback<List<Job>> callback;

    @Captor
    private ArgumentCaptor<List<Job>> jobCaptor;

    /**
     * Tests if the objects are processed by the adapter.
     */
    @Test
    public void testAdapterWithSuccess() {
        List<Job> expected = Collections.emptyList();
        Jobs jobs = createJobs(expected);

        JobCallbackAdapter adapter = new JobCallbackAdapter(callback);
        adapter.success(jobs, null);

        verify(callback).onSuccess(jobCaptor.capture());
        List<Job> delivered = jobCaptor.getValue();
        assertEquals("Lists must be the same", expected, delivered);
    }

    private Jobs createJobs(final List<Job> wrappedJobs) {
        Jobs jobs = new Jobs();
        jobs.setJobs(wrappedJobs);
        return jobs;
    }
}
