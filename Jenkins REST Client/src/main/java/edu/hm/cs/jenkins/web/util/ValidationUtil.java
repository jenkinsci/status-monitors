package edu.hm.cs.jenkins.web.util;

import edu.hm.cs.jenkins.web.ValidatorFactory;
import edu.hm.cs.jenkins.web.validator.CredentialsValidator;

/**
 * Util which allows to perform validation of the credentials and to display the failed validations.
 *
 * @author Tobias Wochinger
 */
public abstract class ValidationUtil {

    private CredentialsLoader credentials;

    private ValidatorFactory factory;

    private boolean isHostnameValid;

    private boolean isPortValid;

    private boolean isTokenValid;

    private boolean isUsernameValid;

    /**
     * Creates a ValidationUtil.
     * @param credentials provides the credentials to validate
     * @param factory factory with the validators
     */
    public ValidationUtil(final CredentialsLoader credentials, final ValidatorFactory factory) {
        this.credentials = credentials;
        this.factory = factory;
    }

    /**
     * Checks whether all relevant entered settings are valid. If no
     * authentication is needed, those fields are not validated.
     *
     * @return <code>true</code> if all needed fields are valid,
     * else <code>false</code>
     */
    public boolean areCredentialsValid() {
        validateUrl();

        if (isAuthenticationNeeded()) {
            validateAuthenticationData();
            return isUrlValid() && isAuthenticationDataCorrect();
        } else {
            return isUrlValid();
        }
    }

    private void validateUrl() {
        validateHostname();
        validatePort();
    }

    private void validateHostname() {
        CredentialsValidator validator = factory.createHostnameValidator();
        String hostname = credentials.getHostname();
        isHostnameValid = validator.validate(hostname);
    }

    private void validatePort() {
        CredentialsValidator validator = factory.createPortValidator();
        String port = credentials.getPort();
        isPortValid = validator.validate(port);
    }

    private boolean isAuthenticationNeeded() {
        return credentials.isAuthenticationNeeded();
    }

    private void validateAuthenticationData() {
        validateUsername();
        validateToken();
    }

    private void validateUsername() {
        CredentialsValidator validator = factory.createUsernameValidator();
        String username = credentials.getUsername();
        isUsernameValid = validator.validate(username);
    }

    private void validateToken() {
        CredentialsValidator validator = factory.createTokenValidator();
        String token = credentials.getToken();
        isTokenValid = validator.validate(token);
    }

    private boolean isUrlValid() {
        return isHostnameValid && isPortValid;
    }

    private boolean isAuthenticationDataCorrect() {
        return isUsernameValid && isTokenValid;
    }

    /**
     * Displays a message containing what validations failed.
     */
    public abstract void showFailedValidations();

    public boolean isHostnameValid() {
        return isHostnameValid;
    }

    public boolean isPortValid() {
        return isPortValid;
    }

    public boolean isTokenValid() {
        return isTokenValid;
    }

    public boolean isUsernameValid() {
        return isUsernameValid;
    }
}
