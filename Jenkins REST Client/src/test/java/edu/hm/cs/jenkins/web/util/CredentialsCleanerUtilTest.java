package edu.hm.cs.jenkins.web.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the class {@link edu.hm.cs.jenkins.web.util.CredentialsCleanerUtil}.
 *
 * @author Tobias Wochinger
 */
public class CredentialsCleanerUtilTest {

    /**
     * Tests the correct cleaning of leading tabs.
     */
    @Test
    public void testLeadingTabTrimming() {
        String expected = "value";

        String actual = CredentialsCleanerUtil.clean("\t" + expected);

        assertUnnecessaryWhitespaceIsRemoved(expected, actual);
    }

    private void assertUnnecessaryWhitespaceIsRemoved(final String expected, final String actual) {
        assertEquals("Unnecessary whitespace must be removed", expected, actual);
    }

    /**
     * Tests the correct cleaning of leading spaces.
     */
    @Test
    public void testLeadingSpacesTrimming() {
        String expected = "value";

        String actual = CredentialsCleanerUtil.clean("   " + expected);

        assertUnnecessaryWhitespaceIsRemoved(expected, actual);
    }

    /**
     * Tests the correct cleaning of leading newlines.
     */
    @Test
    public void testLeadingNewlineTrimming() {
        String expected = "value";

        String actual = CredentialsCleanerUtil.clean("\n" + expected);

        assertUnnecessaryWhitespaceIsRemoved(expected, actual);
    }

    /**
     * Tests the correct cleaning of trailing tabs.
     */
    @Test
    public void testTrailingTabTrimming() {
        String expected = "value";

        String actual = CredentialsCleanerUtil.clean(expected + "\t");

        assertUnnecessaryWhitespaceIsRemoved(expected, actual);
    }

    /**
     * Tests the correct cleaning of trailing spaces.
     */
    @Test
    public void testTrailingSpacesTrimming() {
        String expected = "value";

        String actual = CredentialsCleanerUtil.clean(expected + "    ");

        assertUnnecessaryWhitespaceIsRemoved(expected, actual);
    }

    /**
     * Tests the correct cleaning of trailing new lines.
     */
    @Test
    public void testTrailingNewlineTrimming() {
        String expected = "value";

        String actual = CredentialsCleanerUtil.clean(expected + "\n\n");

        assertUnnecessaryWhitespaceIsRemoved(expected, actual);
    }

    /**
     * Tests the correct cleaning of trailing and leading tabs.
     */
    @Test
    public void testTrimmingOfTabsOnBothSides() {
        String expected = "value";

        String actual = CredentialsCleanerUtil.clean("\t\t" + expected + "\t");

        assertUnnecessaryWhitespaceIsRemoved(expected, actual);
    }

    /**
     * Tests the correct cleaning of trailing and leading spaces.
     */
    @Test
    public void testTrimmingOfSpacesOnBothSides() {
        String expected = "value";

        String actual = CredentialsCleanerUtil.clean("   " + expected + "    ");

        assertUnnecessaryWhitespaceIsRemoved(expected, actual);
    }

    /**
     * Tests the correct cleaning of trailing and leading new lines.
     */
    @Test
    public void testTrimmingOfNewlineOnBothSides() {
        String expected = "value";

        String actual = CredentialsCleanerUtil.clean("\n" + expected + "\n\n");

        assertUnnecessaryWhitespaceIsRemoved(expected, actual);
    }

    /**
     * Tests the cleaning of a hostname without the need to remove a trailing slash.
     */
    @Test
    public void testHostnameCleaningWithoutTrailingSlash() {
        String expected = "http://hostname.de";

        String actual = CredentialsCleanerUtil.cleanHostname(expected + " ");

        assertUnnecessaryWhitespaceIsRemoved(expected, actual);
    }

    /**
     * Tests the correct removing of a trailing slash in a hostname.
     */
    @Test
    public void testTrailingSlashRemoving() {
        String expected = "http://hostname.de";

        String actual = CredentialsCleanerUtil.cleanHostname(expected + "/");

        assertUnnecessaryWhitespaceIsRemoved(expected, actual);
    }

    /**
     * Tests the correct removing of a trailing slash in a hostname when
     * there is also some whitespace.
     */
    @Test
    public void testTrailingSlashRemovingWithWhitespace() {
        String expected = "http://hostname.de";

        String actual = CredentialsCleanerUtil.cleanHostname("  " + expected + "/  ");

        assertUnnecessaryWhitespaceIsRemoved(expected, actual);
    }

    /**
     * Tests that only the last slash is removed from a hostname.
     */
    @Test
    public void testOnlyLastSlashRemoved() {
        String expected = "http://hostname.de/";

        String actual = CredentialsCleanerUtil.cleanHostname(expected + "/");

        assertUnnecessaryWhitespaceIsRemoved(expected, actual);
    }
}
