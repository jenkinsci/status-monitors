package edu.hm.cs.jenkins.notifier.provider;

import com.google.inject.Provider;

import java.awt.*;

/**
 * Provides an instance of {@link java.awt.Toolkit}.
 *
 * @author Tobias Wochinger
 */
public class ToolkitProvider implements Provider<Toolkit> {

    @Override
    public Toolkit get() {
        return Toolkit.getDefaultToolkit();
    }
}
