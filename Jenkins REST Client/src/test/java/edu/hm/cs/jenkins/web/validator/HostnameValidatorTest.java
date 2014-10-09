package edu.hm.cs.jenkins.web.validator;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the class {@link HostnameValidator}.
 *
 * @author Tobias Wochinger
 */
public class HostnameValidatorTest {

    private HostnameValidator validator;

    /**
     * Initializes the test environment for each test.
     */
    @Before
    public void setUp() {
        validator = new HostnameValidator();
    }

    /**
     * Tests that correct http hostnames are detected as correct.
     */
    @Test
    public void testValidHttpHostname() {
        assertValidHostname("http://jenkins.de");
        assertValidHostname("http://example.com/test/12");
    }

    private void assertValidHostname(final String url) {
        assertTrue("Url should be valid", validator.validate(url));
    }

    /**
     * Tests that correct https hostnames are detected as correct.
     */
    @Test
    public void testValidHttpsHostname() {
        assertValidHostname("https://example.com/test/12");
        assertValidHostname("https://jenkins.de");
    }

    /**
     * Tests that invalid hostnames are detected as invalid.
     */
    @Test
    public void testInvalidHostname() {
        assertInvalidHostname(null);
        assertInvalidHostname("http//jenkins.de");
        assertInvalidHostname("example.com/test/12/");
        assertInvalidHostname("ftp://jenkins.de");
    }

    private void assertInvalidHostname(final String url) {
        assertFalse("Url should be invalid", validator.validate(url));
    }
}
