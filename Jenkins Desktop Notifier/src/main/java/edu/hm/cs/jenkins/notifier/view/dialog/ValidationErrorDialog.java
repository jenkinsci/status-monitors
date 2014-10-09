package edu.hm.cs.jenkins.notifier.view.dialog;

import edu.hm.cs.jenkins.notifier.view.settings.Settings;

import javax.inject.Inject;
import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Dialog which is shown when a validation error occurs.
 *
 * @author Tobias Wochinger
 */
public class ValidationErrorDialog {

    @Inject
    private Settings container;

    @Inject
    private ResourceBundle bundle;

    private String message;

    /**
     * Shows a dialog which shows the user which fields are invalid and the
     * user marked authentication fields as needed.
     *
     * @param validHostname validation result of the hostname
     * @param validPort     validation result of the port
     * @param validUsername validation result of the username
     * @param validToken    validation result of the token
     */
    public void showValidationErrors(final boolean validHostname, final boolean validPort, final boolean validUsername,
                                     final boolean validToken) {
        reset();
        handleUrlResults(validHostname, validPort);
        handleAuthenticationData(validUsername, validToken);
        showDialog();
    }

    private void reset() {
        message = bundle.getString("error.validation.basic");
    }

    private void handleUrlResults(final boolean validHostname, final boolean validPort) {
        handleHostnameResult(validHostname);
        handlePortResult(validPort);
    }

    private void handleHostnameResult(final boolean isValid) {
        String errorMessage = bundle.getString("error.validation.hostname");
        addMessageIfInvalid(isValid, errorMessage);
    }

    private void addMessageIfInvalid(final boolean isValid, final String errorMessage) {
        if (!isValid) {
            message += errorMessage;
            message += "\n";
        }
    }

    private void handlePortResult(final boolean isValid) {
        String errorMessage = bundle.getString("error.validation.port");
        addMessageIfInvalid(isValid, errorMessage);
    }

    private void handleAuthenticationData(final boolean validUsername, final boolean validToken) {
        handleUsernameResult(validUsername);
        handleTokenResult(validToken);
    }

    private void handleUsernameResult(final boolean isValid) {
        String errorMessage = bundle.getString("error.validation.username");
        addMessageIfInvalid(isValid, errorMessage);
    }

    private void handleTokenResult(final boolean isValid) {
        String errorMessage = bundle.getString("error.validation.token");
        addMessageIfInvalid(isValid, errorMessage);
    }

    private void showDialog() {
        String header = bundle.getString("error.validation");

        JOptionPane.showMessageDialog(container, message, header, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows a dialog which shows the user which fields are invalid and the
     * user marked authentication fields as not needed.
     *
     * @param validHostname validation result of the hostname
     * @param validPort     validation result of the port
     */
    public void showUrlValidationResults(final boolean validHostname, final boolean validPort) {
        reset();
        handleHostnameResult(validHostname);
        handlePortResult(validPort);
        showDialog();
    }
}
