package edu.hm.cs.jenkins.notifier.provider;

import com.google.inject.Provider;

import java.awt.*;

/**
 * Provides an instance of {@link java.awt.GraphicsConfiguration}.
 *
 * @author Tobias Wochinger
 */
public class GraphicsConfigurationProvider implements Provider<GraphicsConfiguration> {

    @Override
    public GraphicsConfiguration get() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    }
}
