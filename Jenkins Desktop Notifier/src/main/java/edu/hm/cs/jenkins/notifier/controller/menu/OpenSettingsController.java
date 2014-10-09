package edu.hm.cs.jenkins.notifier.controller.menu;

import edu.hm.cs.jenkins.notifier.view.settings.Settings;

import javax.inject.Inject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller which opens the settings view if the user chose it in the menu.
 *
 * @author Tobias Wochinger
 */
public class OpenSettingsController implements ActionListener {

    @Inject
    private Settings settings;

    @Override
    public void actionPerformed(final ActionEvent e) {
        settings.setVisible(true);
    }
}
