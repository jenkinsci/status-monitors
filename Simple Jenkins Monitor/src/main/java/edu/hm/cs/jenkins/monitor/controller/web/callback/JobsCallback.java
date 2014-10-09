package edu.hm.cs.jenkins.monitor.controller.web.callback;

import android.widget.ArrayAdapter;
import edu.hm.cs.jenkins.web.model.Job;

import javax.inject.Inject;
import java.util.List;

/**
 * Provides a callback for client operations which shows an error dialog
 * in case of failure.
 *
 * @author Tobias Wochinger
 */
public class JobsCallback extends ErrorAsDialogCallback<List<Job>> {

    @Inject
    private ArrayAdapter<Job> jobAdapter;

    @Override
    public final void onSuccess(final List<Job> jobs) {
        jobAdapter.clear();
        jobAdapter.addAll(jobs);
        jobAdapter.notifyDataSetChanged();
    }
}