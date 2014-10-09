package edu.hm.cs.jenkins.monitor.controller.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import edu.hm.cs.jenkins.monitor.JenkinsMonitor;
import edu.hm.cs.jenkins.monitor.controller.fragment.list.BuildFragment;
import edu.hm.cs.jenkins.monitor.controller.fragment.list.JobFragment;
import edu.hm.cs.jenkins.monitor.controller.fragment.settings.SettingsFragment;
import edu.hm.cs.jenkins.web.model.Job;

import javax.inject.Inject;

/**
 * Handles the insertions of the fragments in the container
 * of the {@link edu.hm.cs.jenkins.monitor.JenkinsMonitor}.
 *
 * @author Tobias Wochinger
 */
public class FragmentUtil {

    @Inject
    private FragmentManager fragmentManager;

    /**
     * Shows the settings to the user.
     */
    public void showSettingsFragment() {
        Fragment settings = getSavedInstances(SettingsFragment.TAG);
        if (settings == null) {
            settings = new SettingsFragment();
        }
        replaceFragment(settings, SettingsFragment.TAG);
    }

    private Fragment getSavedInstances(final String tag) {
        return fragmentManager.findFragmentByTag(tag);
    }

    /**
     * Replaces the current fragment with a new one allowing to go back to
     * the previous fragment by pressing
     * the back key.
     *
     * @param fragment    new fragment which should be shown
     * @param fragmentTag unique identifier of the fragment, so that it can
     *                    be found again with it
     */
    private void replaceFragment(final Fragment fragment, final String fragmentTag) {
        fragmentManager.beginTransaction().
                replace(JenkinsMonitor.CONTAINER_ID, fragment).
                addToBackStack(fragmentTag).
                commitAllowingStateLoss();
    }

    /**
     * Shows the jobs list to the user.
     */
    public void showJobsFragment() {
        Fragment jobs = getSavedInstances(JobFragment.TAG);
        if (jobs == null) {
            jobs = new JobFragment();
        }
        replaceFragment(jobs, JobFragment.TAG);
    }

    /**
     * Shows the build list to the user.
     *
     * @param job Job which builds should be shown.
     */
    public void showBuildsFragment(final Job job) {
        Fragment fragment = getSavedInstances(BuildFragment.TAG);
        if (fragment == null) {
            fragment = new BuildFragment();
        }
        BuildFragment castet = (BuildFragment) fragment;
        castet.setJob(job);
        replaceFragment(castet, BuildFragment.TAG);
    }

    /**
     * Checks whether there was a fragment shown before the current.
     *
     * @return <code>true</code> if a previous fragment exists,
     * otherwise <code>false</code>
     */
    public boolean existsPreviousFragment() {
        return fragmentManager.getBackStackEntryCount() > 0;
    }

    /**
     * Shows the fragment which was shown to the user.
     * before the current fragment.
     */
    public void showPreviousFragment() {
        fragmentManager.popBackStack();
    }
}