package edu.hm.cs.jenkins.notifier.provider;

import com.google.inject.Provider;

import java.util.prefs.Preferences;

/**
 * Provides an instance of {@link java.util.prefs.Preferences} to store
 * the user settings beyond the runtime of the application.
 *
 * @author Tobias Wochinger
 */
public class PreferencesProvider implements Provider<Preferences> {

    @Override
    public Preferences get() {
        return Preferences.userNodeForPackage(getClass());
    }
}
