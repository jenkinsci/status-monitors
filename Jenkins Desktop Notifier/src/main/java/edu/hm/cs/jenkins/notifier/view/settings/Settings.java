package edu.hm.cs.jenkins.notifier.view.settings;


import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Frame which contains the settings and is shown to the user if the user
 * wants to see the settings.
 *
 * @author Tobias Wochinger
 */
@Singleton
public class Settings extends JFrame {

    private static final int WIDTH = 350;

    private static final int HEIGHT = 330;

    private final ResourceBundle bundle;

    private SettingsView view;

    /**
     * Creates the settings.
     *
     * @param bundle bundle which contains the header for frame
     */
    @Inject
    public Settings(final ResourceBundle bundle) {
        this.bundle = bundle;
        addTitle();
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        placeInCenterOfScreen();
        setAlwaysOnTop(true);
    }

    /**
     * Sets the icon of the frame.
     *
     * @param image icon
     */
    @Inject
    public void setImageIcon(final Image image) {
        setIconImage(image);
    }

    private void addTitle() {
        String title = bundle.getString("settings");
        setTitle(title);
    }

    private void placeInCenterOfScreen() {
        setLocationRelativeTo(null);
    }

    public SettingsView getSettingsView() {
        return view;
    }

    /**
     * Sets the actual settings view.
     *
     * @param view view with fields and buttons to enter new settings
     */
    @Inject
    public void setSettingsView(final SettingsView view) {
        this.view = view;
        setContentPane(this.view);
    }
}
