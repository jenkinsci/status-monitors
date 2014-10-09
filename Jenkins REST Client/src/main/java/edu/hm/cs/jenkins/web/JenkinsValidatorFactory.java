package edu.hm.cs.jenkins.web;

import edu.hm.cs.jenkins.web.validator.*;

/**
 * Implementation of {@link edu.hm.cs.jenkins.web.ValidatorFactory}.
 *
 * @author Tobias Wochinger
 */
public class JenkinsValidatorFactory implements ValidatorFactory {

    @Override
    public CredentialsValidator createHostnameValidator() {
        return new HostnameValidator();
    }

    @Override
    public CredentialsValidator createPortValidator() {
        return new PortValidator();
    }

    @Override
    public CredentialsValidator createUsernameValidator() {
        return new UsernameValidator();
    }

    @Override
    public CredentialsValidator createTokenValidator() {
        return new TokenValidator();
    }
}
