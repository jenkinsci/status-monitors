package edu.hm.cs.jenkins.notifier.controller;

import edu.hm.cs.jenkins.notifier.controller.communication.BuildCompletionChecker;
import edu.hm.cs.jenkins.notifier.controller.settings.SettingsInitializeController;
import edu.hm.cs.jenkins.notifier.view.dialog.ApplicationErrorDialog;
import edu.hm.cs.jenkins.web.util.ValidationUtil;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.awt.*;

/**
 * Controller for the application.
 *
 * @author Tobias Wochinger
 */
public class TrayApplicationController {

    @Inject
    @Nullable
    private SystemTray tray;

    @Inject
    private TrayIcon icon;

    @Inject
    private BuildCompletionChecker job;

    @Inject
    private ApplicationErrorDialog error;

    @Inject
    private ValidationUtil validations;

    @Inject
    private SettingsInitializeController initializeController;

    /**
     * Starts the application in the tray.
     * If the system bar is not supported, an error is shown.
     */
    public void start() {
        if (isTraySupported()) {
            addIconToTray();
            initializeController.initializeView();
            startJob();
        } else {
            error.show("error.system.tray");
        }
    }

    private boolean isTraySupported() {
        return tray != null;
    }

    private void addIconToTray() {
        try {
            tray.add(icon);
        } catch (AWTException e) {
            error.show("error.system.tray");
        }
    }

    private void startJob() {
        if (validations.areCredentialsValid()) {
            job.start();
        } else {
            error.show("settings.invalid");
        }
    }

    /**
     * Removes the application from the tray.
     */
    public void removeTrayIcon() {
        tray.remove(icon);
    }
}
