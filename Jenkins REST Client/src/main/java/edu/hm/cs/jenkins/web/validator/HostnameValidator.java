package edu.hm.cs.jenkins.web.validator;

/**
 * Does a very simple url-validation.
 *
 * @author Tobias Wochinger
 */
public class HostnameValidator extends AbstractValidator {

    private static final String HTTP_SCHEME = "http://";

    private static final String HTTPS_SCHEME = "https://";

    /**
     * Validates a url. Does not try to establish a connection!
     *
     * @param hostname url to validate
     * @return <code>true</code> if valid, else <code>false</code>
     */
    public boolean validate(final String hostname) {

        if (isEmpty(hostname)) {
            return false;
        }

        return !isEmpty(hostname) && startsWithValidHttpScheme(hostname);
    }

    private boolean startsWithValidHttpScheme(final String hostname) {
        return hostname.startsWith(HTTP_SCHEME) || hostname.startsWith(HTTPS_SCHEME);
    }
}
