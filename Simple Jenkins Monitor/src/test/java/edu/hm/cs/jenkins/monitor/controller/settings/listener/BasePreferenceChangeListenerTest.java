package edu.hm.cs.jenkins.monitor.controller.settings.listener;

import android.preference.Preference;
import android.support.v4.app.FragmentManager;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import edu.hm.cs.jenkins.monitor.BaseTest;
import edu.hm.cs.jenkins.monitor.view.dialog.Dialog;
import edu.hm.cs.jenkins.web.JenkinsValidatorFactory;
import edu.hm.cs.jenkins.web.ValidatorFactory;
import edu.hm.cs.jenkins.web.validator.CredentialsValidator;
import org.mockito.Mock;

import javax.inject.Inject;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Defines common behaviour for tests of subclasses of
 * {@link android.preference.Preference.OnPreferenceChangeListener}.
 *
 * @author Tobias Wochinger
 */
public abstract class BasePreferenceChangeListenerTest extends BaseTest {

    private static final String TEST_VALUE = "value";

    @Inject
    private Preference.OnPreferenceChangeListener listener;

    @Mock
    private Preference preference;

    @Mock
    private Dialog errorDialog;

    @Mock
    private CredentialsValidator validator;

    @Mock
    private JenkinsValidatorFactory factory;

    @Override
    public void setUp() {
        super.setUp();
        setUpFactoryMock();
    }

    private void setUpFactoryMock() {
        when(factory.createHostnameValidator()).thenReturn(validator);
        when(factory.createPortValidator()).thenReturn(validator);
        when(factory.createUsernameValidator()).thenReturn(validator);
        when(factory.createTokenValidator()).thenReturn(validator);
    }

    /**
     * Tests a preference with a valid value.
     *
     * @param key key of the preference
     */
    protected final void testValidValue(final int key) {
        String keyAsString = getString(key);
        configureMocks(keyAsString, true);

        boolean result = listener.onPreferenceChange(preference, TEST_VALUE);

        assertTrue("Valid values can be saved", result);
        assertCorrectHandlingOfValidValue(keyAsString);
    }

    private void configureMocks(final String key, final boolean validValue) {
        when(preference.getKey()).thenReturn(key);
        when(validator.validate(anyString())).thenReturn(validValue);
    }

    private void assertCorrectHandlingOfValidValue(final String key) {
        verifyZeroInteractions(errorDialog);
    }

    /**
     * Tests a preference with a invalid value.
     *
     * @param key           key of the preference
     * @param errorMessaage resourceId of the errorMessage which is expected
     *                      to be shown
     */
    protected final void testInvalidValue(final int key, final int errorMessaage) {
        String keyAsString = getString(key);
        configureMocks(keyAsString, false);

        boolean result = listener.onPreferenceChange(preference, TEST_VALUE);

        assertFalse("Invalid values are not saved", result);
        assertCorrectHandlingOfInvalidValue(keyAsString, errorMessaage);
    }

    private void assertCorrectHandlingOfInvalidValue(final String key, final int message) {
        verify(errorDialog).show(message);
    }

    /**
     * Provides the basic bindings for testing a PreferenceChangeListener.
     */
    protected class PreferenceTestModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(FragmentManager.class).
                    toInstance(getFragmentManager());
            bind(Dialog.class).annotatedWith(Names.named("silent")).toInstance(errorDialog);
            bind(Dialog.class).annotatedWith(Names.named("striking")).toInstance(errorDialog);
            bind(CredentialsValidator.class).toInstance(validator);
            bind(ValidatorFactory.class).toInstance(factory);
        }
    }
}
