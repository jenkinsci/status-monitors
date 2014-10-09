package edu.hm.cs.jenkins.monitor.controller.fragment.list;

import android.os.Bundle;
import android.view.*;
import android.widget.ArrayAdapter;
import edu.hm.cs.jenkins.monitor.R;
import edu.hm.cs.jenkins.web.ClientFactory;
import edu.hm.cs.jenkins.web.util.CredentialsLoader;
import edu.hm.cs.jenkins.web.callback.ErrorCallback;
import edu.hm.cs.jenkins.web.client.BuildClient;
import edu.hm.cs.jenkins.web.client.JobClient;
import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.model.Build;
import edu.hm.cs.jenkins.web.model.Job;

import javax.inject.Inject;

/**
 * Shows all builds of a job in a list and allows to trigger a job.
 *
 * @author Tobias Wochinger
 */
public class BuildFragment extends AbstractListFragment {

    public static final String TAG = BuildFragment.class.getSimpleName();

    @Inject
    private ArrayAdapter<Build> buildAdapter;

    @Inject
    private ClientFactory factory;

    @Inject
    private ErrorCallback triggerCallback;

    @Inject
    private ClientCallback<Job> buildsCallback;

    @Inject
    private CredentialsLoader credentials;


    private Job job;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        setListAdapter(buildAdapter);
        clearAdapterToAvoidOldView();
        return inflater.inflate(R.layout.build_list_layout, container, false);
    }

    private void clearAdapterToAvoidOldView() {
        buildAdapter.clear();
    }
    @Override
    protected void retrieveData() {
        BuildClient client = factory.createBuildClient();
        client.getAllBuilds(job, buildsCallback);
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        inflater.inflate(R.menu.builds, menu);
        MenuItem triggerBuildItem = menu.findItem(R.id.trigger_builds);
        setVisibilityOfTriggerMenu(triggerBuildItem);
    }


    private void setVisibilityOfTriggerMenu(final MenuItem menuItem) {
        boolean isVisible = credentials.isAuthenticationNeeded();
        menuItem.setVisible(isVisible);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int idOfSelected = item.getItemId();
        if (idOfSelected == R.id.trigger_builds) {
            triggerNewBuild();
        }
        return super.onOptionsItemSelected(item);
    }

    private void triggerNewBuild() {
        if (checkClientCallDoability()) {
            JobClient jobClient = factory.createJobClient();
            jobClient.triggerJob(job, triggerCallback);
        }
    }

    /**
     * Setter to pass the information needed for the retrieving of the builds.
     *
     * @param job job whose builds should be shown
     */
    public void setJob(final Job job) {
        this.job = job;
    }
}