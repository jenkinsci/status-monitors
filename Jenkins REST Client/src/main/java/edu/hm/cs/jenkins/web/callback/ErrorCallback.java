package edu.hm.cs.jenkins.web.callback;

/**
 * Simplified callback if only the failure must be handled
 * (e.g. for POST-requests)
 *
 * @author Tobias Wochinger
 */
public abstract class ErrorCallback implements ClientCallback<Object> {

    @Override
    public void onSuccess(final Object result) {
        //do nothing
    }
}
