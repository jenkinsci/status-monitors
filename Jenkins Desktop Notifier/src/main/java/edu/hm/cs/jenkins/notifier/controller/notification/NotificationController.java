package edu.hm.cs.jenkins.notifier.controller.notification;

import edu.hm.cs.jenkins.notifier.view.notification.NotificationView;
import edu.hm.cs.jenkins.web.model.Result;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Controller which manages the displaying of the notification popups.
 *
 * @author Tobias Wochinger
 */
public class NotificationController {

    protected static final int VISIBLE_TIME_IN_MS = 15000;

    private static int currentVisibleNotifications = 0;

    @Inject
    private ResourceBundle bundle;

    @Inject
    private NotificationFactory factory;

    /**
     * Shows the user an popup notification that a build just finished.
     *
     * @param jobName name of the job whose build just finished
     * @param result  result of the finished job
     */
    public void show(final String jobName, final Result result) {
        String messageTemplate = bundle.getString("job.finished");
        String completeMessage = MessageFormat.format(messageTemplate, jobName, result.name());
        createNotification(completeMessage, result.name());
    }

    /**
     * Shows an error popup the user (e.g. no Jenkins access was possible).
     *
     * @param error message to be shown
     */
    public void show(final String error) {
        String title = bundle.getString("error.web.title");
        createNotification(title, error);
    }

    private void createNotification(final String title, final String message) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                int currentNumber = incrementNumberOfVisible();
                NotificationView notificationView = factory.createNotificationView(currentNumber);
                notificationView.setMessage(title, message);
                windUpDisappearingTimer(notificationView);
                notificationView.setVisible(true);
            }
        });
    }

    private void windUpDisappearingTimer(final NotificationView view) {
        Timer timer = new Timer(VISIBLE_TIME_IN_MS, new DelayListener(view));
        timer.setRepeats(false);
        timer.start();
    }

    private synchronized int incrementNumberOfVisible() {
        return ++currentVisibleNotifications;
    }

    private synchronized void decrementNumberOfVisible() {
        currentVisibleNotifications--;
    }

    /**
     * Class which handles it, to dispose the notification after it was shown
     * a specified period.
     */
    private class DelayListener implements ActionListener {

        private NotificationView view;

        /**
         * Creates the listener.
         *
         * @param view view to dispose when the event occurs.
         */
        public DelayListener(final NotificationView view) {
            this.view = view;
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            view.dispose();
            decrementNumberOfVisible();
        }
    }
}
