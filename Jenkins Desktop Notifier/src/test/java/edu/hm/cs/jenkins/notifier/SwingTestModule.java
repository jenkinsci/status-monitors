package edu.hm.cs.jenkins.notifier;

/**
 * Extends {@link BasicTestModule} to provide some more mock bindings of
 * the views.
 *
 * @author Tobias Wochinger
 */
public class SwingTestModule extends BasicTestModule {

    @Override
    protected void configure() {
        super.configure();
        bindSettings();
        bindSettingsView();
    }
}
