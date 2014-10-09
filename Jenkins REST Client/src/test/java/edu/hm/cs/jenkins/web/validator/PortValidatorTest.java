package edu.hm.cs.jenkins.web.validator;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the class {@link edu.hm.cs.jenkins.web.validator.PortValidator}.
 *
 * @author Tobias Wochinger
 */
public class PortValidatorTest {

    private PortValidator validator;

    /**
     * Initializes the test environment for each test.
     */
    @Before
    public void setUp() {
        validator = new PortValidator();
    }

    /**
     * Tests that correct ports as strings are detected as valid.
     */
    @Test
    public void testValidIntegerPorts() {
        assertValidPort(1);
        assertValidPort(8080);
        assertValidPort(65535);
    }

    private void assertValidPort(final int port) {
        assertTrue("Port is valid", validator.validate(port));
    }

    /**
     * Tests that correct ports as integer are detected as valid.
     */
    @Test
    public void testValidStringPorts() {
        assertValidPort("1");
        assertValidPort("8080");
        assertValidPort("65535");
    }

    private void assertValidPort(final String port) {
        assertTrue("Port is valid", validator.validate(port));
    }

    /**
     * Tests that ports with leading / trailing whitespace are detected
     * as  valid.
     */
    @Test
    public void testValidStringPortsWithWhitespace() {
        assertValidPort("1  ");
        assertValidPort(" 8080");
        assertValidPort("   65535  ");
    }

    /**
     * Tests that invalid ports as integer are detected as invalid.
     */
    @Test
    public void testInvalidIntegerPorts() {
        assertInvalidPort(0);
        assertInvalidPort(-8080);
        assertInvalidPort(65536);
    }

    private void assertInvalidPort(final int port) {
        assertFalse("Port is invalid", validator.validate(port));
    }

    /**
     * Tests that invalid ports as strings are detected as invalid.
     */
    @Test
    public void testInvalidStringPorts() {
        assertInvalidPort(null);
        assertInvalidPort("0");
        assertInvalidPort("-8080");
        assertInvalidPort("65536");
    }

    private void assertInvalidPort(final String port) {
        assertFalse("Port is invalid", validator.validate(port));
    }

    /**
     * Tests that invalid ports with whitespace are detected as invalid.
     */
    @Test
    public void testInvalidStringPortsWithWhitespace() {
        assertInvalidPort(" 0");
        assertInvalidPort("-8080 ");
        assertInvalidPort("65536    ");
    }

}
