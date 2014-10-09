package edu.hm.cs.jenkins.web.validator;

/**
 * Defines common behaviour for validators.
 *
 * @author Tobias Wochinger
 */
public abstract class AbstractValidator implements CredentialsValidator {

    /**
     * Checks if a string is empty.
     *
     * @param value value to check
     * @return <code>true</code> if empty, else <code>false</code>
     */
    protected boolean isEmpty(final String value) {
        if (value == null) {
            return true;
        }

        String trimmedValue = value.trim();

        return trimmedValue.length() == 0;
    }
}
