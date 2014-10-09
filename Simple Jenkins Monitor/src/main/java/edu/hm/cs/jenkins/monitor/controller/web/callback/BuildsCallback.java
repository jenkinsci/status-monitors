package edu.hm.cs.jenkins.monitor.controller.web.callback;

import android.widget.ArrayAdapter;
import edu.hm.cs.jenkins.web.model.Build;
import edu.hm.cs.jenkins.web.model.Job;

import javax.inject.Inject;

/**
 * Provides a callback for client operations which shows an error dialog
 * in case of failure.
 *
 * @author Tobias Wochinger
 */
public class BuildsCallback extends ErrorAsDialogCallback<Job> {

    @Inject
    private ArrayAdapter<Build> jobAdapter;

    @Override
    public final void onSuccess(final Job job) {
        jobAdapter.clear();
        jobAdapter.addAll(job.getBuilds());
        jobAdapter.notifyDataSetChanged();
    }
}