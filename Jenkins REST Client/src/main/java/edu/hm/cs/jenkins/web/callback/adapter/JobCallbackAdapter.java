package edu.hm.cs.jenkins.web.callback.adapter;

import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.callback.ClientError;
import edu.hm.cs.jenkins.web.model.Job;
import edu.hm.cs.jenkins.web.model.Jobs;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.List;

/**
 * Adapts a callback which receives a
 * {@link edu.hm.cs.jenkins.web.model.Jobs}
 * object to a callback which recieves a list of
 * {@link edu.hm.cs.jenkins.web.model.Job} objects.
 *
 * @author Tobias Wochinger
 */
public class JobCallbackAdapter implements Callback<Jobs> {

    private final ClientCallback<List<Job>> target;

    /**
     * Creates the adapter.
     *
     * @param target callback to which the rearranged objects are passed
     */
    public JobCallbackAdapter(final ClientCallback<List<Job>> target) {
        this.target = target;
    }

    @Override
    public final void success(final Jobs jobs, final Response response) {
        target.onSuccess(jobs.getJobs());
    }

    @Override
    public final void failure(final RetrofitError error) {
        ClientError clientError = new ClientError(error);
        target.onError(clientError);
    }
}
