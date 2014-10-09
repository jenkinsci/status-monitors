package edu.hm.cs.jenkins.notifier.controller.util;

import edu.hm.cs.jenkins.notifier.view.settings.SettingsView;

import javax.inject.Inject;
import java.util.prefs.Preferences;

/**
 * Class which fulfills the task to store the user settings beyond the
 * runtime of the application and to to get those values again.
 *
 * @author Tobias Wochinger
 */
public class CredentialsSaver {

    protected static final String HOSTNAME_KEY = "hostname";

    protected static final String PORT_KEY = "port";

    protected static final String AUTHENTICATION_NEEDED_KEY = "authenticationNeeded";

    protected static final String USERNAME_KEY = "username";

    protected static final String TOKEN_KEY = "token";

    @Inject
    private Preferences preferences;

    @Inject
    private SettingsView view;

    /**
     * Saves all the data entered in the settings view.
     */
    public void saveSettings() {
        saveHostname();
        savePort();
        saveAuthenticationNeeded();
        saveUsername();
        saveToken();
    }

    private void saveHostname() {
        String textInput = view.getHostname();
        preferences.put(HOSTNAME_KEY, textInput);
    }

    private void savePort() {
        String textInput = view.getPort();
        preferences.put(PORT_KEY, textInput);
    }

    private void saveAuthenticationNeeded() {
        boolean authenticationNeeded = view.isAuthenticationNeeded();
        preferences.putBoolean(AUTHENTICATION_NEEDED_KEY, authenticationNeeded);
    }

    private void saveUsername() {
        String textInput = view.getUsername();
        preferences.put(USERNAME_KEY, textInput);
    }

    private void saveToken() {
        String textInput = view.getToken();
        preferences.put(TOKEN_KEY, textInput);
    }
}