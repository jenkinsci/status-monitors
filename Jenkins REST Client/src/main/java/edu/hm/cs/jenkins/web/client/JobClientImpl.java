package edu.hm.cs.jenkins.web.client;

import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.callback.ErrorCallback;
import edu.hm.cs.jenkins.web.callback.adapter.JobCallbackAdapter;
import edu.hm.cs.jenkins.web.callback.adapter.RetrofitCallbackAdapter;
import edu.hm.cs.jenkins.web.model.Job;
import edu.hm.cs.jenkins.web.security.Authentication;
import edu.hm.cs.jenkins.web.service.JobService;
import retrofit.Callback;
import retrofit.RestAdapter;

import java.util.List;

/**
 * Implementation of a client which provides operations
 * to retrieve jobs or to trigger a job.
 *
 * @author Tobias Wochinger
 */
public class JobClientImpl extends BaseClient implements JobClient {

    private Authentication authentication;

    /**
     * Creates a job client without authentication header.
     *
     * @param url url of the jenkins server including port
     *            (e.g. http://example.org:8080)
     */
    public JobClientImpl(final String url) {
        super(url);
    }

    /**
     * Creates a job client with authentication header.
     *
     * @param url            url of the jenkins server including port
     *                       (e.g http://example.org:8080)
     * @param authentication credentials needed for the authentication
     */
    public JobClientImpl(final String url, final Authentication authentication) {
        super(url, authentication);
        this.authentication = authentication;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getAllJobs(final ClientCallback<List<Job>> callback) {
        JobService jobService = createJobService();
        Callback adapted = new JobCallbackAdapter(callback);
        jobService.getAllJobs(adapted);
    }

    /**
     * Extends the {@link edu.hm.cs.jenkins.web.service.JobService}.
     *
     * @return extended JobService
     */
    private JobService createJobService() {
        RestAdapter adapter = createAdapter();
        return adapter.create(JobService.class);
    }

    @Override
    public void triggerJob(final Job job, final ErrorCallback callback) {
        JobService jobService = createJobService();
        RetrofitCallbackAdapter<Object> adapted = new RetrofitCallbackAdapter<Object>(callback);
        jobService.triggerBuild(job.getName(), authentication.getToken(), adapted);
    }
}
