package edu.hm.cs.jenkins.notifier.controller.util;

import edu.hm.cs.jenkins.web.util.CredentialsLoader;

import javax.inject.Inject;
import java.util.prefs.Preferences;

/**
 * Provides access to the credentials stored on the system.
 *
 * @author Tobias Wochinger
 */
public class StoredCredentialsLoader implements CredentialsLoader {

    private static final String DEFAULT_STRING = "";

    private static final boolean DEFAULT_BOOLEAN = false;

    @Inject
    private Preferences preferences;

    @Inject
    private CredentialsSaver credentialsSaver;

    @Override
    public String getHostname() {
        return preferences.get(CredentialsSaver.HOSTNAME_KEY, DEFAULT_STRING);
    }

    @Override
    public String getPort() {
        return preferences.get(CredentialsSaver.PORT_KEY, DEFAULT_STRING);
    }

    @Override
    public boolean isAuthenticationNeeded() {
        return preferences.getBoolean(CredentialsSaver.AUTHENTICATION_NEEDED_KEY, DEFAULT_BOOLEAN);
    }

    @Override
    public String getUsername() {
        return preferences.get(CredentialsSaver.USERNAME_KEY, DEFAULT_STRING);
    }

    @Override
    public String getToken() {
        return preferences.get(CredentialsSaver.TOKEN_KEY, DEFAULT_STRING);
    }
}
