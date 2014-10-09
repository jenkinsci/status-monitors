package edu.hm.cs.jenkins.web.security;

import net.iharder.Base64;
import retrofit.RequestInterceptor;

import java.nio.charset.Charset;

/**
 * Interceptor which adds the authentication header to each request.
 *
 * @author Tobias Wochinger
 */
public class AuthorizationInterceptor implements RequestInterceptor {

    private static final String HEADER_VALUE_PREFIX = "Basic ";

    private static final String HEADER_FIELD = "Authorization";

    private String headerValue;

    /**
     * Creates the interceptor for authentication.
     *
     * @param authentication authentication data
     */
    public AuthorizationInterceptor(final Authentication authentication) {
        if (authentication != null && authentication.getUsername() != null && authentication.getToken() != null) {
            createBase64HeaderValue(authentication);
        } else {
            throw new IllegalArgumentException("Authentication cannot be null");
        }
    }

    @Override
    public void intercept(final RequestFacade request) {
        request.addHeader(HEADER_FIELD, headerValue);
    }

    /**
     * The Authentication-header-value is the concatenation of a prefix,
     * username and the token converted to Base64.
     *
     * @param authentication authentication data including username and token
     */
    private void createBase64HeaderValue(final Authentication authentication) {
        String unconverted = authentication.getUsername() + ":" + authentication.getToken();
        byte[] unconvertedAsBytes = unconverted.getBytes(Charset.defaultCharset());
        String converted = Base64.encodeBytes(unconvertedAsBytes);

        this.headerValue = HEADER_VALUE_PREFIX + converted;
    }
}
