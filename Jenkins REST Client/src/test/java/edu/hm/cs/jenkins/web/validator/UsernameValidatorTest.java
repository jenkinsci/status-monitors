package edu.hm.cs.jenkins.web.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the class {@link edu.hm.cs.jenkins.web.validator.UsernameValidator}.
 *
 * @author Tobias Wochinger
 */
public class UsernameValidatorTest {

    private UsernameValidator validator;

    /**
     * Initializes the test environment for each test.
     */
    @Before
    public void setUp() {
        validator = new UsernameValidator();
    }

    /**
     * Tests the detection of an invalid username.
     */
    @Test
    public void testInvalidUsername() {
        assertInvalidUsername(null);
        assertInvalidUsername("");
        assertInvalidUsername("   ");
    }

    private void assertInvalidUsername(final String username) {
        assertFalse("Username must be invalid", validator.validate(username));
    }

    /**
     * Tests the detection of a valid username.
     */
    @Test
    public void testValidUsernames() {
        assertValidUsername("Tester ");
        assertValidUsername("Hans");
        assertValidUsername("Frank");
    }

    private void assertValidUsername(final String username) {
        assertTrue("Username must be valid", validator.validate(username));
    }
}
