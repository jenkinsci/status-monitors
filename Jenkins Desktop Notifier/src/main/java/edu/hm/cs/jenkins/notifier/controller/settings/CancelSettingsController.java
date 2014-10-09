package edu.hm.cs.jenkins.notifier.controller.settings;

import edu.hm.cs.jenkins.notifier.view.settings.Settings;

import javax.inject.Inject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller which handles the event, when a user cancels the editing of the
 * settings.
 *
 * @author Tobias Wochinger
 */
public class CancelSettingsController implements ActionListener {

    @Inject
    private Settings settings;

    @Override
    public void actionPerformed(final ActionEvent e) {
        settings.dispose();
    }
}
