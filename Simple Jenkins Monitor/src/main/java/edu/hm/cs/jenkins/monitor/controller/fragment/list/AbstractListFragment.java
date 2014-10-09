package edu.hm.cs.jenkins.monitor.controller.fragment.list;

import android.view.MenuItem;
import edu.hm.cs.jenkins.monitor.R;
import edu.hm.cs.jenkins.monitor.controller.util.NetworkUtil;
import edu.hm.cs.jenkins.web.util.ValidationUtil;
import roboguice.fragment.RoboListFragment;

import javax.inject.Inject;

/**
 * Defines common behaviour for the used
 * {@link android.support.v4.app.ListFragment}s.
 *
 * @author Tobias Wochinger
 */
public abstract class AbstractListFragment extends RoboListFragment {

    @Inject
    private NetworkUtil networkUtil;

    @Inject
    private ValidationUtil validationUtil;

    @Override
    public void onResume() {
        super.onResume();
        fillList();
    }

    /**
     * Tries to fill the list as far as there is an internet connection.
     */
    protected void fillList() {
        if (checkClientCallDoability()) {
            retrieveData();
        }
    }

    /**
     * Checks whether a client call is doable. If not, a dialog is shown.
     * @return <code>true</code> if client is doable, else <code>false</code>
     */
    protected boolean checkClientCallDoability() {

        return checkCredentials() && checkNetwork();
    }

    private boolean checkNetwork() {
        if (networkUtil.isNetworkAvailable()) {
            return true;
        } else {
            networkUtil.showAlert();
            return false;
        }
    }

    private boolean checkCredentials() {
        if (validationUtil.areCredentialsValid()) {
            return true;
        } else {
            validationUtil.showFailedValidations();
            return false;
        }
    }

    /**
     * Contains the web request to retrieve the data.
     */
    protected abstract void retrieveData();

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int idOfSelected = item.getItemId();
        if (idOfSelected == R.id.refresh_list) {
            fillList();
        }
        return super.onOptionsItemSelected(item);
    }
}