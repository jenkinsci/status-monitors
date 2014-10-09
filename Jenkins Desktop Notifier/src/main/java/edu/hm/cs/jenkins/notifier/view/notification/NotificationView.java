package edu.hm.cs.jenkins.notifier.view.notification;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Notification which is shown as popup on the desktop.
 *
 * @author Tobias Wochinger
 */
public abstract class NotificationView extends JFrame {

    protected static final int HEIGHT_IN_PX = 60;

    protected static final int MARGIN_IN_PX = 20;

    private static final EmptyBorder BORDER = new EmptyBorder(15, 15, 15, 15);

    private static final float TRANSPARENCY = 0.8f;

    private static final int WIDTH_IN_PX = 250;

    private final Toolkit toolkit;

    private final GraphicsConfiguration configuration;

    private final int position;

    private JPanel contentPane = new JPanel();

    /**
     * Creates an Notification, but does not show it.
     *
     * @param toolkit       toolkit which provides information about the screen
     * @param configuration configuration of the screen device
     * @param position      position of the view on the desktop; a different
     *                      value results in a different y position,
     *                      so that the views don't overlap
     */
    public NotificationView(final Toolkit toolkit, final GraphicsConfiguration configuration, final int position) {
        this.toolkit = toolkit;
        this.configuration = configuration;
        this.position = position;

        initializeFrame();
        initializeContentPane();
    }

    private void initializeFrame() {
        setMinimumSize(new Dimension(WIDTH_IN_PX, HEIGHT_IN_PX));
        setUndecorated(true);
        getRootPane().setBorder(new LineBorder(Color.black));
        setOpacity(TRANSPARENCY);
        setAlwaysOnTop(true);
    }

    private void setLocation() {
        Dimension screen = toolkit.getScreenSize();
        Insets screenInsets = toolkit.getScreenInsets(configuration);
        int x = getX(screen);
        int y = getY(screen, screenInsets);

        setLocation(x, y);
    }

    private int getX(final Dimension screen) {
        return (int) screen.getWidth() - getWidth() - MARGIN_IN_PX;
    }

    /**
     * Calculates the y coordinate of the view.
     *
     * @param screen size of the screen
     * @param insets insets of the screen to get the height of the system tray
     * @return y coordinate of the left, top corner of the view.
     */
    protected abstract int getY(final Dimension screen, final Insets insets);

    private void initializeContentPane() {
        contentPane.setBorder(BORDER);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);
    }
    /**
     * Sets the message to be shown to the user.
     * @param title title of the pop up
     * @param message text to to inform the user about an event
     */
    public void setMessage(final String title, final String message) {
        add(Box.createVerticalGlue());
        add(new JLabel(title));
        add(new JLabel(message));
        add(Box.createVerticalGlue());
        refreshPosition();
    }

    private void refreshPosition() {
        pack();
        setLocation();
    }

    /**
     * Returns the slot on y-axis the notification gets so that it does not overlap with other notifications.
     * @return position
     */
    protected int getPosition() {
        return position;
    }
}
