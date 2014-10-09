package edu.hm.cs.jenkins.web.util;

/**
 * Interface to provide access to the credentials given by the user.
 *
 * @author Tobias Wochinger
 */
public interface CredentialsLoader {

    /**
     * Gets the hostname of Jenkins entered by the user.
     * @return hostname
     */
    String getHostname();

    /**
     * Gets the port of Jenkins entered by the user.
     * @return port
     */
    String getPort();

    /**
     * Gets if authentication is needed to access Jenkins.
     * @return <code>true</code> if needed, else <code>false</code>
     */
    boolean isAuthenticationNeeded();

    /**
     * Gets the username of the user who wants to access Jenkins.
     * @return username
     */
    String getUsername();

    /**
     * Gets the token needed to authenticate at Jenkins.
     * @return hostname
     */
    String getToken();

}
