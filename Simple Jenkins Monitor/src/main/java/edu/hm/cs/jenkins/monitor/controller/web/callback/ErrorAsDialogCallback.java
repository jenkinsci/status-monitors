package edu.hm.cs.jenkins.monitor.controller.web.callback;

import edu.hm.cs.jenkins.monitor.R;
import edu.hm.cs.jenkins.monitor.view.dialog.Dialog;
import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.callback.ClientError;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Provides a callback for client operations which shows an error dialog
 * in case of failure.
 *
 * @param <T> type of the non used success object (e.g Object)
 * @author Tobias Wochinger
 */
public abstract class ErrorAsDialogCallback<T> implements ClientCallback<T> {

    @Inject
    @Named("silent")
    private Dialog alert;

    @Override
    public final void onError(final ClientError error) {
        alert.show(R.string.jenkins_client_error);
    }
}