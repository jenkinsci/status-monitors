package edu.hm.cs.jenkins.web.callback.adapter;

import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.model.Build;
import edu.hm.cs.jenkins.web.model.Job;
import retrofit.client.Response;

/**
 * Merges the last build of a {@link Job} with a given
 * object of this job.
 *
 * @author Tobias Wochinger
 */
public class LastBuildCallbackAdapter extends RetrofitCallbackAdapter<Job> {

    private final Job jobOfBuild;

    private final ClientCallback<Job> target;

    /**
     * Creates the adapter.
     *
     * @param jobOfBuild job to which the retrieved values should be added
     * @param target     callback which gets the merged jobs.
     */
    public LastBuildCallbackAdapter(final Job jobOfBuild, final ClientCallback<Job> target) {
        super(target);
        this.target = target;
        this.jobOfBuild = jobOfBuild;
    }

    @Override
    public final void success(final Job job, final Response response) {
        Build receivedLastBuild = job.getLastBuild();
        jobOfBuild.setLastBuild(receivedLastBuild);
        target.onSuccess(jobOfBuild);
    }
}
