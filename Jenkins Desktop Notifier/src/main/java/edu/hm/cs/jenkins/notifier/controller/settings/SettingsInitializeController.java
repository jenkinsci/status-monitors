package edu.hm.cs.jenkins.notifier.controller.settings;

import edu.hm.cs.jenkins.notifier.view.settings.SettingsView;
import edu.hm.cs.jenkins.web.util.CredentialsLoader;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Controller which initializes the settings view with the saved values.
 *
 * @author Tobias Wochinger
 */
public class SettingsInitializeController {

    @Inject @Named("stored")
    private CredentialsLoader credentialsLoader;

    @Inject
    private SettingsView view;

    /**
     * Initializes the settings view with the settings saved in the preferences.
     */
    public void initializeView() {
        String savedValue = credentialsLoader.getHostname();
        view.setHostname(savedValue);

        savedValue = credentialsLoader.getPort();
        view.setPort(savedValue);

        boolean savedAuthenticationNeeded = credentialsLoader.isAuthenticationNeeded();
        view.setAuthenticationNeeded(savedAuthenticationNeeded);

        savedValue = credentialsLoader.getUsername();
        view.setUsername(savedValue);

        savedValue = credentialsLoader.getToken();
        view.setToken(savedValue);
    }
}
