package edu.hm.cs.jenkins.web.util;

import edu.hm.cs.jenkins.web.validator.HostnameValidator;
import edu.hm.cs.jenkins.web.validator.PortValidator;

/**
 * Provides methodes to build a url out of separate parts.
 *
 * @author Tobias Wochinger
 */
public final class UrlBuilder {

    private static final HostnameValidator HOSTNAME_VALIDATOR = new HostnameValidator();

    private static final PortValidator PORT_VALIDATOR = new PortValidator();

    private UrlBuilder() {
        //should not be used
    }

    /**
     * Joins host and port to a target url.
     *
     * @param hostname Name of the host where Jenkins lives
     * @param port     port on which jenkins runs
     * @return join url
     */
    public static String buildUrl(final String hostname, final String port) {

        if (!HOSTNAME_VALIDATOR.validate(hostname)) {
            throw new IllegalArgumentException("No valid host");
        }

        if (!PORT_VALIDATOR.validate(port)) {
            throw new IllegalArgumentException("No valid port");
        }
        return hostname + ":" + port;
    }
}
