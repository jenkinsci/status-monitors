package edu.hm.cs.jenkins.monitor.controller.fragment.list;

import android.os.Bundle;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import edu.hm.cs.jenkins.monitor.R;
import edu.hm.cs.jenkins.monitor.controller.util.FragmentUtil;
import edu.hm.cs.jenkins.web.ClientFactory;
import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.client.JobClient;
import edu.hm.cs.jenkins.web.model.Job;

import javax.inject.Inject;
import java.util.List;

/**
 * Shows all jobs received by Jenkins in a list.
 *
 * @author Tobias Wochinger
 */
public class JobFragment extends AbstractListFragment {

    public static final String TAG = JobFragment.class.getSimpleName();

    @Inject
    private ArrayAdapter<Job> jobAdapter;

    @Inject
    private ClientFactory clientFactory;

    @Inject
    private FragmentUtil fragmentUtil;

    @Inject
    private ClientCallback<List<Job>> jobsCallback;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        setListAdapter(jobAdapter);
        return inflater.inflate(R.layout.job_list_layout, container, false);
    }

    @Override
    protected void retrieveData() {
        JobClient client = clientFactory.createJobClient();
        client.getAllJobs(jobsCallback);
    }

    @Override
    public void onListItemClick(final ListView l, final View v, final int position, final long id) {
        super.onListItemClick(l, v, position, id);
        Job clicked = jobAdapter.getItem(position);
        fragmentUtil.showBuildsFragment(clicked);
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        inflater.inflate(R.menu.jobs, menu);
    }
}