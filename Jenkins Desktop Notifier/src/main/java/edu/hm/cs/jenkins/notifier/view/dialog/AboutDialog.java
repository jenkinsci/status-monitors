package edu.hm.cs.jenkins.notifier.view.dialog;


import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Shows an about dialog about the application which provides some basic
 * information about the application.
 *
 * @author Tobias Wochinger
 */
public class AboutDialog {

    @Inject
    private ResourceBundle bundle;

    @Inject
    private Image icon;

    /**
     * Shows the about dialog.
     */
    public void show() {
        String header = bundle.getString("about");
        String aboutMessage = bundle.getString("about.message");
        JOptionPane.showMessageDialog(null, aboutMessage, header, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(icon));
    }
}
