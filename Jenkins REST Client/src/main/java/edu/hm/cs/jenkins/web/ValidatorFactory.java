package edu.hm.cs.jenkins.web;

import edu.hm.cs.jenkins.web.validator.*;

/**
 * Factory which providers validators for the credentials needed to access a Jenkins server.
 *
 * @author Tobias Wochinger
 */
public interface ValidatorFactory {

    /**
     * Creates a validator to validate that a hostname is syntactical correct.
     * @return validator
     */
    CredentialsValidator createHostnameValidator();

    /**
     * Creates a validator to validate that a port is syntactical correct.
     * @return validator
     */
    CredentialsValidator createPortValidator();

    /**
     * Creates a validator to validate that a username is syntactical correct.
     * @return validator
     */
    CredentialsValidator createUsernameValidator();

    /**
     * Creates a validator to validate that a token is syntactical correct.
     * @return validator
     */
    CredentialsValidator createTokenValidator();
}
