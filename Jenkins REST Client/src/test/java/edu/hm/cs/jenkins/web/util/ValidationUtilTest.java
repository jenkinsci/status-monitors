package edu.hm.cs.jenkins.web.util;

import edu.hm.cs.jenkins.web.BaseTest;
import edu.hm.cs.jenkins.web.JenkinsValidatorFactory;
import edu.hm.cs.jenkins.web.validator.CredentialsValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Tests the class {@link edu.hm.cs.jenkins.web.util.ValidationUtil}.
 *
 * @author Tobias Wochinger
 */
public class ValidationUtilTest extends BaseTest {

    @Mock
    private CredentialsLoader loader;

    @Mock
    private JenkinsValidatorFactory factory;

    @Mock
    private CredentialsValidator hostnameValidator;

    @Mock
    private CredentialsValidator portValidator;

    @Mock
    private CredentialsValidator usernameValidator;

    @Mock
    private CredentialsValidator tokenValidator;

    private ValidationUtil util;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        setUpValidationUtil();
        setUpValidatorFactory();
    }

    private void setUpValidationUtil() {
        util = new TestValidationUtil(loader, factory);
    }

    private void setUpValidatorFactory() {
        when(factory.createHostnameValidator()).thenReturn(hostnameValidator);
        when(factory.createPortValidator()).thenReturn(portValidator);
        when(factory.createUsernameValidator()).thenReturn(usernameValidator);
        when(factory.createTokenValidator()).thenReturn(tokenValidator);
    }

    /**
     * Tests the correct validation result if the username is invalid,
     * but the field is not relevant.
     */
    @Test
    public void testInvalidUsernameIfAuthenticationNotNeeded() {
        setUpMocks(false, true, true, false, true);

        assertValidCredentials();
    }

    private void setUpMocks(final boolean authenticationNeeded, final boolean isHostnameValid,
                            final boolean isPortValid, final boolean isUsernameValid, final boolean isTokenValid) {
        when(loader.isAuthenticationNeeded()).thenReturn(authenticationNeeded);
        when(hostnameValidator.validate(anyString())).thenReturn(isHostnameValid);
        when(portValidator.validate(anyString())).thenReturn(isPortValid);
        when(usernameValidator.validate(anyString())).thenReturn(isUsernameValid);
        when(tokenValidator.validate(anyString())).thenReturn(isTokenValid);
    }

    private void assertValidCredentials() {
        assertTrue("All relevant fields are correct", util.areCredentialsValid());

    }

    /**
     * Tests the correct validation result if the token is invalid,
     * but the field is not relevant.
     */
    @Test
    public void testInvalidTokenIfAuthenticationNotNeeded() {
        setUpMocks(false, true, true, true, false);

        assertValidCredentials();
    }

    /**
     * Tests the correct validation result if the hostname is invalid and no
     * authentication is needed.
     */
    @Test
    public void testInvalidHostnameIfAuthenticationNotNeeded() {
        setUpMocks(false, false, true, true, true);

        assertInvalidCredentials();
    }

    private void assertInvalidCredentials() {
        assertFalse("Not all relevant fields are correct", util.areCredentialsValid());

    }

    /**
     * Tests the correct validation result if the port is invalid and no
     * authentication is needed.
     */
    @Test
    public void testInvalidPortIfAuthenticationNotNeeded() {
        setUpMocks(false, true, false, true, true);

        assertInvalidCredentials();
    }

    /**
     * Tests the correct validation result if the hostname and port are invalid
     * and no authentication is needed.
     */
    @Test
    public void testInvalidUrlIfAuthenticationNotNeeded() {
        setUpMocks(false, false, false, true, true);

        assertInvalidCredentials();
    }

    /**
     * Tests the correct validation result if all fields are invalid and no
     * authentication is needed.
     */
    @Test
    public void testEverythingInvalidIfAuthenticationNotNeeded() {
        setUpMocks(false, false, false, false, false);

        assertInvalidCredentials();
    }

    /**
     * Tests the correct validation result if the username is invalid and the
     * field is relevant.
     */
    @Test
    public void testInvalidUsernameIfAuthenticationNeeded() {
        setUpMocks(true, true, true, false, true);

        assertInvalidCredentials();
    }

    /**
     * Tests the correct validation result if the token is invalid and the
     * field is relevant.
     */
    @Test
    public void testInvalidTokenIfAuthenticationNeeded() {
        setUpMocks(true, true, true, true, false);

        assertInvalidCredentials();
    }

    /**
     * Tests the correct validation result if the username and token are invalid
     * and the field is relevant.
     */
    @Test
    public void testInvalidAuthenticationDataIfAuthenticationNeeded() {
        setUpMocks(true, true, true, false, false);

        assertInvalidCredentials();
    }

    /**
     * Tests the correct validation result if all fields are valid and
     * authentication is needed.
     */
    @Test
    public void testEverythingValidIfAuthenticationNeeded() {
        setUpMocks(true, true, true, true, true);

        assertValidCredentials();
    }

    /**
     * Tests the correct validation result if all fields are invalid and
     * authentication is needed.
     */
    @Test
    public void testEverythingInvalidIfAuthenticationNeeded() {
        setUpMocks(true, false, false, false, false);

        assertInvalidCredentials();
    }

    /**
     * Implementation of {@link edu.hm.cs.jenkins.web.util.ValidationUtil} to test the class although it's abstract.
     * With this approach nearly the complete class can be tested.
     */
    private static class TestValidationUtil extends ValidationUtil {

        public TestValidationUtil(final CredentialsLoader credentials, final JenkinsValidatorFactory factory) {
            super(credentials, factory);
        }

        @Override
        public void showFailedValidations() {
            //tested in implementation classes
        }
    }
}
