package edu.hm.cs.jenkins.web.validator;

/**
 * Abstract interface for all classes which validate credentials of the
 * web service.
 *
 * @author Tobias Wochinger
 */
public interface CredentialsValidator {

    /**
     * Validates of the value is syntactical correct.
     *
     * @param toValidate value which should be validated
     * @return <code>true</code> if valid, else <code>false</code>
     */
    boolean validate(final String toValidate);
}
