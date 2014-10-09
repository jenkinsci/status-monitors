package edu.hm.cs.jenkins.web.client;

import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.model.Job;

/**
 * Client which provides operations to retrieve builds of a job.
 *
 * @author Tobias Wochinger
 */
public interface BuildClient {

    /**
     * Retrieves all builds of a job.
     *
     * @param job      job which builds should be retrieved.
     * @param callback callback which returns the given
     *                 job including all his builds
     */
    void getAllBuilds(final Job job, final ClientCallback<Job> callback);

    /**
     * Retrieves the last build of a job (no matter whether
     * it was successfull or not).
     *
     * @param job      job which last build should be retrieved
     * @param callback callback which returns the given job
     *                 including his last build
     */
    void getLastBuild(final Job job, final ClientCallback<Job> callback);
}
