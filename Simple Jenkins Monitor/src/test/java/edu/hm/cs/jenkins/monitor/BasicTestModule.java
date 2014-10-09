package edu.hm.cs.jenkins.monitor;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import edu.hm.cs.jenkins.monitor.view.dialog.Dialog;

import static org.mockito.Mockito.mock;

/**
 * Provides a basic module needed for the most test modules.
 *
 * @author Tobias Wochinger
 */
public abstract class BasicTestModule extends AbstractModule {

    private final Dialog mockedDialog = mock(Dialog.class);

    /**
     * Binds dialogs annotated with "silent" to a mock.
     */
    protected void bindSilentDialog() {
        bind(Dialog.class).annotatedWith(Names.named("silent")).toInstance(mockedDialog);
    }

    /**
     * Binds dialogs annotated with "striking" to a mock.
     */
    protected void bindStrikingDialog() {
        bind(Dialog.class).annotatedWith(Names.named("striking")).toInstance(mockedDialog);
    }
}
