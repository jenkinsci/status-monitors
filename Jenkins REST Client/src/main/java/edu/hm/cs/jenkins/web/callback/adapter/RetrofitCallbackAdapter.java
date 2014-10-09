package edu.hm.cs.jenkins.web.callback.adapter;

import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.callback.ClientError;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Adapter between the callback shown to the user and the callback used by
 * the retrofit framework.
 *
 * @param <T> information which should be recieved
 * @author Tobias Wochinger
 */
public class RetrofitCallbackAdapter<T> implements Callback<T> {

    private final ClientCallback<T> target;

    /**
     * Wraps a {@link edu.hm.cs.jenkins.web.callback.ClientCallback} in a
     * {@link retrofit.Callback}.
     *
     * @param target callback which should be adapted
     */
    public RetrofitCallbackAdapter(final ClientCallback<T> target) {
        this.target = target;
    }

    @Override
    public void success(final T t, final Response response) {
        target.onSuccess(t);
    }

    @Override
    public void failure(final RetrofitError error) {
        ClientError clientError = new ClientError(error);
        target.onError(clientError);
    }
}
