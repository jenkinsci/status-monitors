package edu.hm.cs.jenkins.notifier.view.notification;

import java.awt.*;

/**
 * Notification which is shown on systems which have the system bar on
 * the bottom of the screen.
 *
 * @author Tobias Wochinger
 */
public class BottomNotificationView extends NotificationView {

    /**
     * Creates an Notification, but does not show it.
     *
     * @param toolkit       toolkit which provides information about the screen
     * @param configuration configuration of the screen device
     * @param position      position of the view on the desktop; a different
     *                      value results in a different y position,
     *                      so that the views don't overlap
     */
    public BottomNotificationView(final Toolkit toolkit, final GraphicsConfiguration configuration,
                                  final int position) {
        super(toolkit, configuration, position);
    }

    @Override
    protected int getY(final Dimension screen, final Insets insets) {
        int position = getPosition();
        int screenHeightWithoutBar = (int) screen.getHeight() - insets.bottom;
        return screenHeightWithoutBar - position * (HEIGHT_IN_PX + MARGIN_IN_PX);
    }
}
