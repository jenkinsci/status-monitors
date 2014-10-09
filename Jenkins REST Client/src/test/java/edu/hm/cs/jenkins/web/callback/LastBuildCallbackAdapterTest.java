package edu.hm.cs.jenkins.web.callback;

import edu.hm.cs.jenkins.web.callback.adapter.LastBuildCallbackAdapter;
import edu.hm.cs.jenkins.web.model.Build;
import edu.hm.cs.jenkins.web.model.Job;
import org.junit.Test;

/**
 * Tests the class {@link edu.hm.cs.jenkins.web.callback.adapter.LastBuildCallbackAdapter}.
 *
 * @author Tobias Wochinger
 */
public class LastBuildCallbackAdapterTest extends AbstractBuildCallbackAdapterTest {

    /**
     * Tests if the objects are processed by the adapter.
     */
    @Test
    public void testAdapter() {
        Job job = createTestJob();
        LastBuildCallbackAdapter adapter = new LastBuildCallbackAdapter(job, getCallback());
        job.setLastBuild(new Build());
        adapter.success(createJobWithBuild(), null);

        verifyCorrectPassing(job);
    }

    private Job createJobWithBuild() {
        Job job = new Job();
        job.setLastBuild(new Build());
        return job;
    }
}
