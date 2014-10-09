package edu.hm.cs.jenkins.notifier.controller.notification;

import edu.hm.cs.jenkins.notifier.view.notification.BottomNotificationView;
import edu.hm.cs.jenkins.notifier.view.notification.NotificationView;
import edu.hm.cs.jenkins.notifier.view.notification.TopNotificationView;

import javax.inject.Inject;
import java.awt.*;

/**
 * Factory which provides notification views depending whether is system tray
 * is located on the top or the bottom of the screen.
 *
 * @author Tobias Wochinger
 */
public class NotificationFactory {

    @Inject
    private Toolkit toolkit;

    @Inject
    private GraphicsConfiguration configuration;


    /**
     * Creates an notification view suitable to the position of the system tray.
     *
     * @param position slot on the y-axis where the notification should be
     *                 shown so that it does not overlap with an other
     *                 notification
     * @return notification view which can be displayed to the user
     */
    public NotificationView createNotificationView(final int position) {
        Insets insets = getScreenInsets();
        if (insets.top > 0) {
            return new TopNotificationView(toolkit, configuration, position);
        } else {
            return new BottomNotificationView(toolkit, configuration, position);

        }
    }

    private Insets getScreenInsets() {
        return toolkit.getScreenInsets(configuration);
    }
}
