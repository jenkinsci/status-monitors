package edu.hm.cs.jenkins.web.validator;

/**
 * Validates the Jenkins-Token, which is used for authentication
 * against the Rest-Api.
 *
 * @author Tobias Wochinger
 */
public class TokenValidator extends AbstractValidator {

    private static final int VALID_TOKEN_LENGTH = 32;

    /**
     * Validates if a token is syntactical correct (this does not mean,
     * that it's accepted by Jenkins).
     *
     * @param token token to validate
     * @return <code>true</code> if valid, else <code>false</code>
     */
    public boolean validate(final String token) {
        if (isEmpty(token)) {
            return false;
        }

        String trimmedToken = token.trim();

        return isLengthCorrect(trimmedToken) && areCharsValid(trimmedToken.toCharArray());

    }

    /**
     * Validates the length of the token. Length must be 32 characters.
     *
     * @param token token to validate
     * @return <code>true</code> if length is okay, else <code>false</code>
     */
    private boolean isLengthCorrect(final String token) {
        return token.length() == VALID_TOKEN_LENGTH;
    }

    /**
     * Checks whether the token contains only valid chars.
     * Lower Case letters and digits are allowed.
     *
     * @param chars token as single chars
     * @return <code>true</code> if only valid chars, else <code>false</code>
     */
    private boolean areCharsValid(final char[] chars) {
        boolean isValid = true;
        int counter = 0;

        while (isValid && counter < chars.length) {
            char current = chars[counter];
            if (Character.isDigit(current) || isLowerCaseLetter(current)) {
                counter++;
            } else {
                isValid = false;
            }
        }

        return isValid;
    }

    private boolean isLowerCaseLetter(final char letter) {
        return Character.isLetter(letter) && Character.isLowerCase(letter);
    }
}
