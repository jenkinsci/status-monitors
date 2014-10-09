package edu.hm.cs.jenkins.web.client;

import edu.hm.cs.jenkins.web.callback.adapter.BuildsCallbackAdapter;
import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.callback.adapter.LastBuildCallbackAdapter;
import edu.hm.cs.jenkins.web.model.Job;
import edu.hm.cs.jenkins.web.security.Authentication;
import edu.hm.cs.jenkins.web.service.BuildService;
import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Implementation of a client which provides operations
 * to retrieve builds of a job.
 *
 * @author Tobias Wochinger
 */
@SuppressWarnings("unchecked")
public class BuildClientImpl extends BaseClient implements BuildClient {

    /**
     * Creates a build client without authentication header.
     *
     * @param url url of the jenkins server including port
     *            (e.g http://example.org:8080)
     */
    public BuildClientImpl(final String url) {
        super(url);
    }

    /**
     * Creates a build client with authentication header.
     *
     * @param url            url of the jenkins server including port
     *                       (e.g. http://example.org:8080)
     * @param authentication credentials needed for the authentication
     */
    public BuildClientImpl(final String url, final Authentication authentication) {
        super(url, authentication);
    }


    @Override
    public void getAllBuilds(final Job job, final ClientCallback<Job> callback) {
        BuildService buildService = createBuildService();
        Callback adapted = new BuildsCallbackAdapter(job, callback);
        buildService.getAllBuilds(job.getName(), adapted);
    }

    /**
     * Extends the {@link edu.hm.cs.jenkins.web.service.BuildService}.
     *
     * @return extended BuildService
     */
    private BuildService createBuildService() {
        RestAdapter adapter = createAdapter();
        return adapter.create(BuildService.class);
    }

    @Override
    public void getLastBuild(final Job job, final ClientCallback<Job> callback) {
        BuildService buildService = createBuildService();
        Callback adapted = new LastBuildCallbackAdapter(job, callback);
        buildService.getLastBuild(job.getName(), adapted);
    }
}
