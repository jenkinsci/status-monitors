package edu.hm.cs.jenkins.web.callback;


import edu.hm.cs.jenkins.web.callback.adapter.BuildsCallbackAdapter;
import edu.hm.cs.jenkins.web.model.Build;
import edu.hm.cs.jenkins.web.model.Job;
import org.junit.Test;

import java.util.Collections;

/**
 * Tests the class {@link edu.hm.cs.jenkins.web.callback.adapter.BuildsCallbackAdapter}.
 *
 * @author Tobias Wochinger
 */
public class BuildsCallbackAdapterTest extends AbstractBuildCallbackAdapterTest {

    /**
     * Tests if the objects are processed by the adapter.
     */
    @Test
    public void testAdapter() {
        Job job = createTestJob();
        BuildsCallbackAdapter adapter = new BuildsCallbackAdapter(job, getCallback());
        job.setBuilds(Collections.<Build>emptyList());
        adapter.success(createJobWithBuilds(), null);

        verifyCorrectPassing(job);
    }

    private Job createJobWithBuilds() {
        Job job = new Job();
        job.setBuilds(Collections.<Build>emptyList());
        return job;
    }
}
