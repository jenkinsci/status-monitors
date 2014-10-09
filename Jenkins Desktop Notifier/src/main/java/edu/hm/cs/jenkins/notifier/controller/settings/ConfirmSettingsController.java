package edu.hm.cs.jenkins.notifier.controller.settings;

import edu.hm.cs.jenkins.notifier.controller.
        communication.BuildCompletionChecker;
import edu.hm.cs.jenkins.notifier.controller.util.CredentialsSaver;
import edu.hm.cs.jenkins.notifier.view.settings.Settings;
import edu.hm.cs.jenkins.web.util.ValidationUtil;

import javax.inject.Inject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the event, when an usr confirm his entered settings.
 *
 * @author Tobias Wochinger
 */
public class ConfirmSettingsController implements ActionListener {

    @Inject
    private CredentialsSaver credentialsSaver;

    @Inject
    private ValidationUtil validator;

    @Inject
    private BuildCompletionChecker job;

    @Inject
    private Settings settings;

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (validator.areCredentialsValid()) {
            credentialsSaver.saveSettings();
            settings.dispose();
            refreshClient();
        } else {
            validator.showFailedValidations();
        }
    }

    private void refreshClient() {
        if (job.isRunning()) {
            job.refreshClient();
        } else {
            job.start();
        }
    }
}


