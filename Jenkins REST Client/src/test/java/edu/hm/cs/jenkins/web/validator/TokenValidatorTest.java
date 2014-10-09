package edu.hm.cs.jenkins.web.validator;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the class {@link edu.hm.cs.jenkins.web.validator.TokenValidator}.
 *
 * @author Tobias Wochinger
 */
public class TokenValidatorTest {

    private TokenValidator validator;

    /**
     * Initializes the test environment for each test.
     */
    @Before
    public void setUp() {
        validator = new TokenValidator();
    }

    /**
     * Tests whether correct tokens without leading / trailing whitespace
     * are detected as valid.
     */
    @Test
    public void testValidTokenWithoutWhitespace() {
        assertTokenIsValid("96afe0befccde1a40b1c1ed4ac00a294");
        assertTokenIsValid("31e9137b81692a427531c92124497677");
        assertTokenIsValid("fd398b70f9e3900ee92e80fde5658b17");
    }

    private void assertTokenIsValid(final String token) {
        boolean valid = validator.validate(token);
        assertTrue("Token is valid", valid);
    }

    /**
     * Tests whether correct tokens with leading / trailing whitespace
     * are detected as valid.
     */
    @Test
    public void testValidTokenWithWhitespace() {
        assertTokenIsValid("    96afe0befccde1a40b1c1ed4ac00a294");
        assertTokenIsValid("31e9137b81692a427531c92124497677   ");
        assertTokenIsValid(" fd398b70f9e3900ee92e80fde5658b17    ");
    }

    /**
     * Tests whether null is detected as invalid token.
     */
    @Test
    public void testInvalidNullToken() {
        assertTokenIsInvalid(null);
    }

    private void assertTokenIsInvalid(final String token) {
        boolean valid = validator.validate(token);
        assertFalse("Token is not valid", valid);
    }

    /**
     * Tests whether too longs tokens are detected as invalid.
     */
    @Test
    public void testTooLongToken() {
        assertTokenIsInvalid("31e9137b81692a427531c921244976771");
        assertTokenIsInvalid("afd398b70f9e3900ee92e80fde5658b17");
    }

    /**
     * Tests whether too short tokens are detected as invalid.
     */
    @Test
    public void testTooShortToken() {
        assertTokenIsInvalid("fd98b70f9e3900ee92e80fde5658b17");
        assertTokenIsInvalid("96afe0befccde1a40b1c1ed4ac00a29");
    }

    /**
     * Tests whether tokens with invalid chars are detected as invalid.
     */
    @Test
    public void testInvalidChars() {
        assertTokenIsInvalid("fd398b70f9e3900ee92e80%de5658b17");
        assertTokenIsInvalid("31-e9137b81692a427531c92124497677");
    }

    /**
     * Tests whether tokens with uppercase letters are detected as invalid.
     */
    @Test
    public void testInvalidUppercaseLetter() {
        assertTokenIsInvalid("31E9137b81692a427531c92124497677");
        assertTokenIsInvalid("fd398B70f9e3900ee92e80fde5658b17");
    }
}
