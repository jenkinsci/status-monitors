package edu.hm.cs.jenkins.notifier.view;


import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Icon which is shown in the system tray and allows the user
 * to interact with the application.
 *
 * @author Tobias Wochinger
 */
@Singleton
public class JenkinsTrayIcon extends java.awt.TrayIcon {

    @Inject
    private ResourceBundle bundle;

    /**
     * Creates an tray icon.
     *
     * @param bundle bundle which contains the tooltip
     * @param image  image which is shown
     */
    @Inject
    public JenkinsTrayIcon(final ResourceBundle bundle, final Image image) {
        super(image);
        this.bundle = bundle;
        configure();
    }

    private void configure() {
        setImageAutoSize(true);
        addToolTip();
    }

    private void addToolTip() {
        String tip = bundle.getString("tray.tooltip");
        setToolTip(tip);
    }

    /**
     * Sets the menu of the tray icon which is shown to the user if he
     * performs a right click on it.
     *
     * @param menu menu to provide several actions
     */
    @Inject
    public void setMenu(final TrayMenu menu) {
        setPopupMenu(menu);
    }
}
