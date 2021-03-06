package edu.hm.cs.jenkins.monitor.controller.settings.listener;

import edu.hm.cs.jenkins.monitor.R;
import edu.hm.cs.jenkins.web.ValidatorFactory;
import edu.hm.cs.jenkins.web.validator.CredentialsValidator;

import javax.inject.Inject;

/**
 * Listens for new / changed hostname values and validates them.
 * In case of invalidity an error message is shown to the user.
 *
 * @author Tobias Wochinger
 */
public class HostnameChangeListener extends AbstractPreferenceChangeListener {

    @Inject
    private ValidatorFactory factory;

    @Override
    protected boolean validate(final String value) {
        CredentialsValidator validator = factory.createHostnameValidator();
        return validator.validate(value);
    }

    @Override
    protected int getErrorMessage() {
        return R.string.invalid_hostname;
    }
}
