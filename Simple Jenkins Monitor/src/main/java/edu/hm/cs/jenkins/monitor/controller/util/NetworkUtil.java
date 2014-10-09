package edu.hm.cs.jenkins.monitor.controller.util;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import edu.hm.cs.jenkins.monitor.R;
import edu.hm.cs.jenkins.monitor.view.dialog.Dialog;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Provides methods around the network connection.
 *
 * @author Tobias Wochinger
 */
public class NetworkUtil {

    @Inject
    private ConnectivityManager connectivityManager;

    @Inject
    @Named("silent")
    private Dialog alert;

    /**
     * Checks whether there is an internet connection or not.
     * Be careful: Sometimes a network connection is available
     * but the network access is not working
     * (e.g the connection is very slow).
     *
     * @return <code>true</code> if connection is available,
     * otherwise <code>false</code>
     */
    public boolean isNetworkAvailable() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();

    }

    /**
     * Shows an alert to the user that there is no network connection.
     */
    public void showAlert() {
        alert.show(R.string.network_error);
    }

}