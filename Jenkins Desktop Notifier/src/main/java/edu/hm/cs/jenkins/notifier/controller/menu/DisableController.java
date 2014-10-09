package edu.hm.cs.jenkins.notifier.controller.menu;

import edu.hm.cs.jenkins.notifier.controller.communication.BuildCompletionChecker;

import javax.inject.Inject;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Controller which enables / disables the the background job when the user
 * chose it in the menu.
 *
 * @author Tobias Wochinger
 */
public class DisableController implements ItemListener {

    @Inject
    private BuildCompletionChecker job;

    @Override
    public void itemStateChanged(final ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            job.stop();
        } else {
            job.start();
        }
    }
}
