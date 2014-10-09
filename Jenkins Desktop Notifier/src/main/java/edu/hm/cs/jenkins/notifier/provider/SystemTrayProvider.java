package edu.hm.cs.jenkins.notifier.provider;

import com.google.inject.Provider;

import java.awt.*;

/**
 * Provides an instance of the system bar.
 *
 * @author Tobias Wochinger
 */
public class SystemTrayProvider implements Provider<SystemTray> {

    /**
     * Returns an instance of the system bar.
     *
     * @return system bar, if no system bar is supported <code>null</code>
     */
    @Override
    public SystemTray get() {
        if (SystemTray.isSupported()) {
            return SystemTray.getSystemTray();
        } else {
            return null;
        }
    }
}
