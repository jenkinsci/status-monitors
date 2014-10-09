package edu.hm.cs.jenkins.notifier.controller.menu;

import edu.hm.cs.jenkins.notifier.controller.TrayApplicationController;
import edu.hm.cs.jenkins.notifier.controller.util.ApplicationCloser;

import javax.inject.Inject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles the exiting of the appliction when thee user chooses it in the menu.
 */
public class ExitController implements ActionListener {

    @Inject
    private TrayApplicationController trayApplicationController;

    @Inject
    private ApplicationCloser closer;

    @Override
    public void actionPerformed(final ActionEvent e) {
        trayApplicationController.removeTrayIcon();
        closer.exitApplication();
    }
}
