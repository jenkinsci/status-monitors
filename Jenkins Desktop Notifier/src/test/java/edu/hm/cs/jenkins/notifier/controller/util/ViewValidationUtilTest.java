package edu.hm.cs.jenkins.notifier.controller.util;

import com.google.inject.name.Names;
import edu.hm.cs.jenkins.notifier.BaseTest;
import edu.hm.cs.jenkins.notifier.SwingTestModule;
import edu.hm.cs.jenkins.notifier.view.dialog.ValidationErrorDialog;
import edu.hm.cs.jenkins.web.JenkinsValidatorFactory;
import edu.hm.cs.jenkins.web.util.CredentialsLoader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.inject.Inject;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests the class {@link edu.hm.cs.jenkins.notifier.controller.util.ViewValidationUtil}.
 *
 * @author Tobias Wochinger
 */
public class ViewValidationUtilTest extends BaseTest {

    @Mock
    private ValidationErrorDialog dialog;

    @Mock
    private CredentialsLoader loader;

    @Mock
    private JenkinsValidatorFactory factory;

    @Inject
    private ViewValidationUtil util;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
    }

    /**
     * Tests displaying of failed validations when only the url fields are relevant.
     */
    @Test
    public void testIfValidationNotNeeded() {
        when(loader.isAuthenticationNeeded()).thenReturn(false);

        util.showFailedValidations();

        verify(dialog).showUrlValidationResults(anyBoolean(), anyBoolean());
    }

    /**
     * Tests displaying of failed validations when all fields are relevant.
     */
    @Test
    public void testIfValidationNeeded() {
        when(loader.isAuthenticationNeeded()).thenReturn(true);

        util.showFailedValidations();

        verify(dialog).showValidationErrors(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean());
    }


    private class TestModule extends SwingTestModule {

        @Override
        protected void configure() {
            super.configure();
            bind(ValidationErrorDialog.class).toInstance(dialog);
            bind(JenkinsValidatorFactory.class).toInstance(factory);
            bind(CredentialsLoader.class).annotatedWith(Names.named("view")).toInstance(loader);

        }
    }
}
