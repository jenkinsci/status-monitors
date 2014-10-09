package edu.hm.cs.jenkins.monitor.controller.settings;

import android.content.SharedPreferences;
import edu.hm.cs.jenkins.monitor.R;
import edu.hm.cs.jenkins.web.util.CredentialsLoader;
import roboguice.inject.InjectResource;

import javax.inject.Inject;

/**
 * Provides access to the stored credentials.
 *
 * @author Tobias Wochinger
 */
public class StoredCredentialsLoader implements CredentialsLoader {

    private static final String STRING_IF_KEY_NOT_AVAILABLE = "";

    private static final boolean BOOLEAN_IF_KEY_NOT_AVAILABLE = false;

    @Inject
    private SharedPreferences preferences;

    @InjectResource(R.string.hostname_key)
    private String hostnameKey;

    @InjectResource(R.string.port_key)
    private String portKey;

    @InjectResource(R.string.username_key)
    private String usernameKey;

    @InjectResource(R.string.authentication_needed_key)
    private String authenticationNeededKey;

    @InjectResource(R.string.authentication_token_key)
    private String tokenKey;

    @Override
    public String getHostname() {
        return getString(hostnameKey);
    }

    private String getString(final String key) {
        return preferences.getString(key, STRING_IF_KEY_NOT_AVAILABLE);
    }

    @Override
    public String getPort() {
        return getString(portKey);
    }

    @Override
    public boolean isAuthenticationNeeded() {
        return getBoolean(authenticationNeededKey);
    }

    private boolean getBoolean(final String key) {
        return preferences.getBoolean(key, BOOLEAN_IF_KEY_NOT_AVAILABLE);
    }

    @Override
    public String getUsername() {
        return getString(usernameKey);
    }

    @Override
    public String getToken() {
        return getString(tokenKey);
    }
}
