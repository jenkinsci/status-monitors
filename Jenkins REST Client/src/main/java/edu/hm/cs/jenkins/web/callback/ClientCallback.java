package edu.hm.cs.jenkins.web.callback;

/**
 * Own Interface for client operations.
 * Not using {@link retrofit.Callback} in order to have a clean API without
 * framework dependencies.
 *
 * @param <T> Typ of the requested answer
 * @author Tobias Wochinger
 */
public interface ClientCallback<T> {

    /**
     * Called if the request is successful.
     *
     * @param result requested information
     */
    void onSuccess(final T result);

    /**
     * Called if the request failed.
     *
     * @param error detailed information about the error
     */
    void onError(final ClientError error);
}
