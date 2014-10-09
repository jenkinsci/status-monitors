package edu.hm.cs.jenkins.web;

import edu.hm.cs.jenkins.web.validator.CredentialsValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Tests the class {@link JenkinsValidatorFactory}.
 * @author Tobias Wochinger
 */
public class ValidatorFactoryTest {

    private JenkinsValidatorFactory factory;

    /**
     * Set up of the test environment.
     */
    @Before
    public void setUp() {
        factory = new JenkinsValidatorFactory();
    }

    /**
     * Tests the creation of the hostname validator.
     */
    @Test
    public void testHostnameValidatorCreation() {
        CredentialsValidator validator = factory.createHostnameValidator();
        assertValidatorNotNull(validator);
    }

    private void assertValidatorNotNull(final CredentialsValidator validator) {
        assertNotNull("Factory must provide validator", validator);
    }

    /**
     * Tests the creation of the port validator.
     */
    @Test
    public void testPortValidatorCreation() {
        CredentialsValidator validator = factory.createPortValidator();
        assertValidatorNotNull(validator);
    }

    /**
     * Tests the creation of the username validator.
     */
    @Test
    public void testUsernameValidatorCreation() {
        CredentialsValidator validator = factory.createUsernameValidator();
        assertValidatorNotNull(validator);
    }

    /**
     * Tests the creation of the token validator.
     */
    @Test
    public void testTokenValidatorCreation() {
        CredentialsValidator validator = factory.createTokenValidator();
        assertValidatorNotNull(validator);
    }

}
