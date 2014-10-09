package edu.hm.cs.jenkins.web.validator;

/**
 * Validates if a port is valid.
 *
 * @author Tobias Wochinger
 */
public class PortValidator extends AbstractValidator {

    private static final int MIN_PORT_NUMBER = 1;

    private static final int MAX_PORT_NUMBER = 65535;

    /**
     * Checks if a port  is in a valid range.
     *
     * @param port port as Integer to validate
     * @return <code>true</code> if valid, else <code>false</code>
     */
    public boolean validate(final int port) {
        return port >= MIN_PORT_NUMBER && port <= MAX_PORT_NUMBER;
    }

    /**
     * Checks if a port  is in a valid range.
     *
     * @param port port as String to validate
     * @return <code>true</code> if valid, else <code>false</code>
     */
    public boolean validate(final String port) {
        return !isEmpty(port) && doFurtherValidationAsInt(port);
    }

    private boolean doFurtherValidationAsInt(final String port) {
        String trimmed = port.trim();
        try {
            int parsed = Integer.parseInt(trimmed);
            return validate(parsed);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
