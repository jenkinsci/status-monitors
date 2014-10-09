package edu.hm.cs.jenkins.monitor.controller.fragment.settings;

import android.os.Bundle;
import android.preference.Preference;
import edu.hm.cs.jenkins.monitor.R;
import edu.hm.cs.jenkins.monitor.controller.settings.listener.HostnameChangeListener;
import edu.hm.cs.jenkins.monitor.controller.settings.listener.PortChangeListener;
import edu.hm.cs.jenkins.monitor.controller.settings.listener.TokenChangeListener;
import edu.hm.cs.jenkins.monitor.controller.settings.listener.UsernameChangeListener;

import javax.inject.Inject;

/**
 * Shows the settings which are needed to access Jenkins via web.
 *
 * @author Tobias Wochinger
 */
public class SettingsFragment extends RoboPreferenceFragment {

    public static final String TAG = SettingsFragment.class.getSimpleName();

    @Inject
    private HostnameChangeListener hostnameListener;

    @Inject
    private PortChangeListener portListener;

    @Inject
    private UsernameChangeListener usernameListener;

    @Inject
    private TokenChangeListener tokenListener;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_preferences);
        addPreferenceChangedListeners();
    }

    /**
     * Adds listeners which validate new / changed values.
     */
    private void addPreferenceChangedListeners() {
        addOnPreferenceChangedListener(R.string.hostname_key, hostnameListener);
        addOnPreferenceChangedListener(R.string.port_key, portListener);
        addOnPreferenceChangedListener(R.string.username_key, usernameListener);
        addOnPreferenceChangedListener(R.string.authentication_token_key, tokenListener);
    }

    /**
     * Adds a listener to a preference.
     *
     * @param key      of preference
     * @param listener listener to be registered
     */
    private void addOnPreferenceChangedListener(final int key, final Preference.OnPreferenceChangeListener listener) {
        String keyAsString = getString(key);
        Preference hostname = findPreference(keyAsString);
        hostname.setOnPreferenceChangeListener(listener);
    }
}