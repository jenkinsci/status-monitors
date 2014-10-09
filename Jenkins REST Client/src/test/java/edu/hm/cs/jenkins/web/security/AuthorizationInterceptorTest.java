package edu.hm.cs.jenkins.web.security;


import edu.hm.cs.jenkins.web.BaseTest;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

/**
 * Tests the class {@link edu.hm.cs.jenkins.web.security.AuthorizationInterceptor}.
 * @author Tobias Wochinger
 */
public class AuthorizationInterceptorTest extends BaseTest {

    @Mock
    private Authentication authentication;

    private AuthorizationInterceptor interceptor;

    /**
     * Tests the constructor with a null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWithNullAuthentication() {
        authentication = null;
        interceptor = new AuthorizationInterceptor(authentication);
    }

    /**
     * Tests the constructor with an authentication whose username is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWithUsernameNull() {
        when(authentication.getUsername()).thenReturn(null);
        interceptor = new AuthorizationInterceptor(authentication);
    }

    /**
     * Tests the constructor with an authentication whose token is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWithTokenNull() {
        when(authentication.getToken()).thenReturn(null);
        interceptor = new AuthorizationInterceptor(authentication);
    }
}
