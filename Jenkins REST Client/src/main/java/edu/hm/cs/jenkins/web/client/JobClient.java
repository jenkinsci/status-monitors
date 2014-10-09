package edu.hm.cs.jenkins.web.client;

import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.callback.ErrorCallback;
import edu.hm.cs.jenkins.web.model.Job;

import java.util.List;

/**
 * Client which provides operations to retrieve jobs
 * or to trigger a job.
 *
 * @author Tobias Wochinger
 */
public interface JobClient {

    /**
     * Retrieves all jobs on the the jenkins server.
     *
     * @param callback callback to return all the jobs.
     */
    void getAllJobs(final ClientCallback<List<Job>> callback);

    /**
     * Triggers job, so that a new build is done.
     *
     * @param job      job which should be triggered
     * @param callback callback to handle errors or the
     *                 successfull triggering
     */
    void triggerJob(final Job job, final ErrorCallback callback);

}
