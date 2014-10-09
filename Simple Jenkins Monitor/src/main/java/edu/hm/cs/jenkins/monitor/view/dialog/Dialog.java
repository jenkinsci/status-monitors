package edu.hm.cs.jenkins.monitor.view.dialog;

/**
 * Base class for dialogs.
 *
 * @author Tobias Wochinger
 */
public interface Dialog {

    /**
     * Shows an error dialog to the user.
     *
     * @param messageId message to be shown
     */
    void show(final int messageId);
}
