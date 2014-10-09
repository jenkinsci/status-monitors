package edu.hm.cs.jenkins.web.client;

import edu.hm.cs.jenkins.web.security.Authentication;
import edu.hm.cs.jenkins.web.security.AuthorizationInterceptor;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Base class for a Jenkins-API access.
 *
 * @author Tobias Wochinger
 */
public class BaseClient {

    private final String url;

    private Authentication authentication;

    /**
     * Creates a client without authentication header.
     *
     * @param url url of the jenkins server including port
     *            (e.g. http://example.org:8080)
     */
    public BaseClient(final String url) {
        this.url = url;
    }

    /**
     * Creates the class.
     *
     * @param url            url of the jenkins server including port
     *                       (e.g. http://example.org:8080)
     * @param authentication credentials needed for the authentication
     */
    public BaseClient(final String url, final Authentication authentication) {
        this.url = url;
        this.authentication = authentication;
    }

    /**
     * Prepares a request.
     * This includes the adding of the authentication to a request.
     *
     * @return adapter for creating an implementation of a retrofit interface
     */
    protected RestAdapter createAdapter() {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(url);
        setAuthenticationIfProvided(builder);
        return builder.build();
    }

    /**
     * Adds the authentication header to the request
     * if the necessary data is provided.
     * Otherwise the request if performed without authentication.
     *
     * @param builder request which should get the authentication data
     */
    private void setAuthenticationIfProvided(final RestAdapter.Builder builder) {
        if (authentication != null) {
            RequestInterceptor authorization = createAuthorizationInterceptor();
            builder.setRequestInterceptor(authorization);
        }
    }

    /**
     * Creates an interceptor to for authentication with the token.
     *
     * @return Interceptor which adds the authentication header to each request.
     */
    private RequestInterceptor createAuthorizationInterceptor() {
        return new AuthorizationInterceptor(authentication);
    }
}
