package edu.hm.cs.jenkins.web.validator;

/**
 * Does some simple validation with username.
 *
 * @author Tobias Wochinger
 */
public class UsernameValidator extends AbstractValidator {

    /**
     * Validates an username whether is syntactical correct.
     *
     * @param username username to validate
     * @return <code>true</code> if valid, else <code>false</code>
     */
    @Override
    public boolean validate(final String username) {
        return !isEmpty(username);
    }
}
