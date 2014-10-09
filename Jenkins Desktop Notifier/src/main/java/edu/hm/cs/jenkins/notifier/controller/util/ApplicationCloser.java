package edu.hm.cs.jenkins.notifier.controller.util;

/**
 * Exits the application.
 *
 * @author Tobias Wochinger
 */
public class ApplicationCloser {

    private static final int SUCCESSFUL_EXIT = 0;

    /**
     * Exits the application with status 0.
     */
    public void exitApplication() {
        System.exit(SUCCESSFUL_EXIT);
    }
}
