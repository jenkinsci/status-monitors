package edu.hm.cs.jenkins.notifier.view.dialog;


import javax.inject.Inject;
import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Shows an error dialog to the user when a heavy system error occurs.
 *
 * @author Tobias Wochinger
 */
public class ApplicationErrorDialog {

    @Inject
    private ResourceBundle bundle;

    /**
     * Shows the error dialog.
     *
     * @param messageKey resource id of the message
     */
    public void show(final String messageKey) {
        String header = bundle.getString("error");
        String message = bundle.getString(messageKey);

        JOptionPane.showMessageDialog(null, message, header, JOptionPane.ERROR_MESSAGE);
    }
}
