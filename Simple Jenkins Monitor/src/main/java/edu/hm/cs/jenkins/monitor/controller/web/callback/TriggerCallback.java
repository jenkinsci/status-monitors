package edu.hm.cs.jenkins.monitor.controller.web.callback;

import edu.hm.cs.jenkins.monitor.R;
import edu.hm.cs.jenkins.monitor.view.dialog.Dialog;
import edu.hm.cs.jenkins.web.callback.ClientError;
import edu.hm.cs.jenkins.web.callback.ErrorCallback;

import javax.inject.Inject;
import javax.inject.Named;


/**
 * Callback for the triggering of jobs.
 *
 * @author Tobias Wochinger
 */
public class TriggerCallback extends ErrorCallback {

    @Inject
    @Named("silent")
    private Dialog alert;

    @Override
    public final void onError(final ClientError error) {
        alert.show(R.string.jenkins_client_error);
    }
}
