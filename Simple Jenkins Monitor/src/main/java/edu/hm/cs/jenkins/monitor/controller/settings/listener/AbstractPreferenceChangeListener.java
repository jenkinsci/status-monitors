package edu.hm.cs.jenkins.monitor.controller.settings.listener;

import android.preference.Preference;
import edu.hm.cs.jenkins.monitor.view.dialog.Dialog;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Defines an abstract listener which listens for changes of
 * a setting through an user.
 * New values are validated. In case of invalidity an error is shown.
 *
 * @author Tobias Wochinger
 */
public abstract class AbstractPreferenceChangeListener implements Preference.OnPreferenceChangeListener {

    @Inject
    @Named("striking")
    private Dialog alert;

    @Override
    public boolean onPreferenceChange(final Preference preference, final Object newValue) {
        String key = preference.getKey();
        String value = (String) newValue;
        boolean isValid = validate(value);

        if (isValid) {
            return true;
        } else {
            alert.show(getErrorMessage());
            return false;
        }
    }

    /**
     * Validates a new / changed value.
     *
     * @param value value which should be validated
     * @return <code>true</code> if valid, else <code>false</code>
     */
    protected abstract boolean validate(final String value);

    /**
     * Returns the id of an error message which should be shown
     * if a new / changed value is invalid.
     *
     * @return resource if of the error message
     */
    protected abstract int getErrorMessage();
}
