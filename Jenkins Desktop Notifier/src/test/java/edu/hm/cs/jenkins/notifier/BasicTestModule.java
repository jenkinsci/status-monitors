package edu.hm.cs.jenkins.notifier;

import com.google.inject.AbstractModule;
import edu.hm.cs.jenkins.notifier.view.settings.Settings;
import edu.hm.cs.jenkins.notifier.view.settings.SettingsView;

import java.awt.*;

import static org.mockito.Mockito.mock;

/**
 * Provides some basic bindings which are needed in most of the test cases.
 * Avoids {@link java.awt.HeadlessException}s.
 *
 * @author Tobias Wochinger
 */
public class BasicTestModule extends AbstractModule {

    private final Settings settingsMock = mock(Settings.class);
    private final SettingsView settingsViewMock = mock(SettingsView.class);
    private final TrayIcon iconMock = mock(TrayIcon.class);
    private GraphicsConfiguration configurationMock = mock(GraphicsConfiguration.class);

    @Override
    protected void configure() {
        bind(GraphicsConfiguration.class).toInstance(configurationMock);
    }

    /**
     * Binds {@link edu.hm.cs.jenkins.notifier.view.settings.SettingsView}
     * to a mock.
     */
    protected void bindSettingsView() {
        bind(SettingsView.class).toInstance(settingsViewMock);
    }

    /**
     * Binds {@link edu.hm.cs.jenkins.notifier.view.settings.Settings}
     * to a mock.
     */
    protected void bindSettings() {
        bind(Settings.class).toInstance(settingsMock);
    }

    /**
     * Binds {@link java.awt.TrayIcon} to a mock.
     */
    protected void bindTrayIcon() {
        bind(TrayIcon.class).toInstance(iconMock);
    }
}
