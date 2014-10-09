package edu.hm.cs.jenkins.monitor.controller.util;

import edu.hm.cs.jenkins.monitor.R;
import edu.hm.cs.jenkins.monitor.view.dialog.Dialog;
import edu.hm.cs.jenkins.web.ValidatorFactory;
import edu.hm.cs.jenkins.web.util.CredentialsLoader;
import edu.hm.cs.jenkins.web.util.ValidationUtil;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Implementation of {@link edu.hm.cs.jenkins.web.util.ValidationUtil} for the Android platform.
 * Failed validations are handled in a dialog.
 *
 * @author Tobias Wochinger
 */
public class StoredCredentialsValidationUtil extends ValidationUtil {

    @Inject
    @Named("striking")
    private Dialog alert;

    /**
     * Creates a StoredCredentialsValidationUtil.
     * @param credentials provides the credentials to validate
     * @param factory factory with the validators
     */
    @Inject
    public StoredCredentialsValidationUtil(final CredentialsLoader credentials, final ValidatorFactory factory) {
        super(credentials, factory);
    }

    @Override
    public void showFailedValidations() {
        alert.show(R.string.no_valid_settings);
    }
}
