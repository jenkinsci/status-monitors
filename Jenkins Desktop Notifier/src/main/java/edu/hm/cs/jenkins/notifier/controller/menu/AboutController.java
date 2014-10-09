package edu.hm.cs.jenkins.notifier.controller.menu;

import edu.hm.cs.jenkins.notifier.view.dialog.AboutDialog;

import javax.inject.Inject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Shows the about dialog if the user chose it in the menu.
 *
 * @author Tobias Wochinger
 */
public class AboutController implements ActionListener {

    @Inject
    private AboutDialog aboutDialog;

    @Override
    public void actionPerformed(final ActionEvent e) {
        aboutDialog.show();
    }
}
