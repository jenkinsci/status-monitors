package edu.hm.cs.jenkins.notifier.controller.settings;

import edu.hm.cs.jenkins.notifier.view.settings.SettingsView;

import javax.inject.Inject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller which handles the event, when an user triggers the
 * checkbox in the settings view in order to declare that authentication is
 * needed to access Jenkins or not.
 *
 * @author Tobias Wochinger
 */
public class AuthenticationNeededController implements ActionListener {

    @Inject
    private SettingsView view;

    @Override
    public void actionPerformed(final ActionEvent e) {
        boolean enableCredentialsInput = view.isAuthenticationNeeded();

        view.setCredentialFieldsEnabled(enableCredentialsInput);
    }
}
