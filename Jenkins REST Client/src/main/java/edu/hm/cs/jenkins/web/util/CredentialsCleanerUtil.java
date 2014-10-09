package edu.hm.cs.jenkins.web.util;

/**
 * Provides methods to clean credentials entered by the user.
 *
 * @author Tobias Wochinger
 */
public final class CredentialsCleanerUtil {

    private static final String SLASH = "/";

    private CredentialsCleanerUtil() {
        //not needed
    }

    /**
     * Basic cleaning.
     * Removes leading and trailing whitespace from text.
     *
     * @param toClean text to clean
     * @return cleaned text
     */
    public static String clean(final String toClean) {
        return toClean.trim();
    }

    /**
     * Cleans a hostname from leading / trailing whitespace and removes
     * an eventually trailing slash.
     *
     * @param toClean hostname to clean
     * @return cleaned hostname
     */
    public static String cleanHostname(final String toClean) {
        String withoutWhitespace = clean(toClean);

        if (withoutWhitespace.endsWith(SLASH)) {
            withoutWhitespace = withoutWhitespace.substring(0, withoutWhitespace.length() - 1);
        }
        return withoutWhitespace;
    }
}
