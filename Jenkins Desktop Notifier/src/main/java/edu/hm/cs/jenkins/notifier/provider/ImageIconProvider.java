package edu.hm.cs.jenkins.notifier.provider;

import com.google.inject.Provider;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Provides the icon for the tray.
 *
 * @author Tobias Wochinger
 */
public class ImageIconProvider implements Provider<Image> {

    @Override
    public Image get() {
        return getImage();
    }

    private Image getImage() {
        URL imageURL = ClassLoader.getSystemResource("icon.png");
        ImageIcon icon = new ImageIcon(imageURL);
        return icon.getImage();
    }
}
