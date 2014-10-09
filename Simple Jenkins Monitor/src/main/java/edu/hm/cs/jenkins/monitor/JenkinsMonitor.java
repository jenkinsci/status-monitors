package edu.hm.cs.jenkins.monitor;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import edu.hm.cs.jenkins.monitor.controller.util.FragmentUtil;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.ContentView;

import javax.inject.Inject;

/**
 * Activity which contains the fragments
 * {@link edu.hm.cs.jenkins.monitor.controller.fragment.list.JobFragment} and
 * {@link edu.hm.cs.jenkins.monitor.controller.fragment.list.BuildFragment}.
 * Furthermore the class includes behaviour which are applied to the whole app.
 *
 * @author Tobias Wochinger
 */
@ContentView(R.layout.activity_main)
public class JenkinsMonitor extends RoboFragmentActivity {

    public static final int CONTAINER_ID = R.id.fragment_container;

    @Inject
    private FragmentUtil fragmentUtil;

    @Inject
    private Thread.UncaughtExceptionHandler handler;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(handler);
        fragmentUtil.showJobsFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.settings) {
            fragmentUtil.showSettingsFragment();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //as there are only two list views that approach is valid enough
        fragmentUtil.showJobsFragment();
    }
}