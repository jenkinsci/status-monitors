package edu.hm.cs.jenkins.notifier.controller.util;


import edu.hm.cs.jenkins.notifier.view.dialog.ValidationErrorDialog;
import edu.hm.cs.jenkins.web.ValidatorFactory;
import edu.hm.cs.jenkins.web.util.CredentialsLoader;
import edu.hm.cs.jenkins.web.util.ValidationUtil;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Implementation of {@link edu.hm.cs.jenkins.web.util.ValidationUtil} for native Java environments. Handles the
 * displaying of failed validations with an {@link edu.hm.cs.jenkins.notifier.view.dialog.ValidationErrorDialog}.
 *
 * @author Tobias Wochinger
 */
public class ViewValidationUtil extends ValidationUtil {

    @Inject
    private ValidationErrorDialog error;

    private CredentialsLoader loader;

    /**
     * Creates a ValidationUtil.
     * @param credentials provides the credentials to validate
     * @param factory factory with the validators
     */
    @Inject
    public ViewValidationUtil(@Named("view") final CredentialsLoader credentials, final ValidatorFactory factory) {
        super(credentials, factory);
        this.loader = credentials;
    }

    @Override
    public void showFailedValidations() {
        if (loader.isAuthenticationNeeded()) {
            error.showValidationErrors(isHostnameValid(), isPortValid(), isUsernameValid(), isTokenValid());
        } else {
            error.showUrlValidationResults(isHostnameValid(), isPortValid());
        }
    }
}
