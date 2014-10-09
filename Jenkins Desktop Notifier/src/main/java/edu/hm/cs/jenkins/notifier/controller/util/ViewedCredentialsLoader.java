package edu.hm.cs.jenkins.notifier.controller.util;

import edu.hm.cs.jenkins.notifier.view.settings.SettingsView;
import edu.hm.cs.jenkins.web.util.CredentialsLoader;

import javax.inject.Inject;

/**
 * Provides access to the credentials stored in the {@link edu.hm.cs.jenkins.notifier.view.settings.SettingsView}.
 *
 * @author Tobias Wochinger
 */
public class ViewedCredentialsLoader implements CredentialsLoader {

    @Inject
    private SettingsView view;

    @Override
    public String getHostname() {
        return view.getHostname();
    }

    @Override
    public String getPort() {
        return view.getPort();
    }

    @Override
    public boolean isAuthenticationNeeded() {
        return view.isAuthenticationNeeded();
    }

    @Override
    public String getUsername() {
        return view.getUsername();
    }

    @Override
    public String getToken() {
        return view.getToken();
    }
}
