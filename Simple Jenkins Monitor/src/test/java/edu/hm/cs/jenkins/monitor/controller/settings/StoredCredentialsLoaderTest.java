package edu.hm.cs.jenkins.monitor.controller.settings;

import android.content.SharedPreferences;
import com.google.inject.AbstractModule;
import edu.hm.cs.jenkins.monitor.BaseTest;
import edu.hm.cs.jenkins.monitor.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import javax.inject.Inject;

import static junit.framework.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

/**
 * Tests the class {@link StoredCredentialsLoader}.
 *
 * @author Tobias Wochinger
 */
@RunWith(RobolectricTestRunner.class)
public class StoredCredentialsLoaderTest extends BaseTest {

    private static final String EXPECTED = "expected";

    private static final String WRONG_KEY = "wrong Key";

    @Inject
    private StoredCredentialsLoader credentials;

    @Mock
    private SharedPreferences sharedPreferences;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
    }

    /**
     * Tests if the actual stored hostname is returned.
     */
    @Test
    public void testGetHostname() {
        String hostnameKey = getString(R.string.hostname_key);
        when(sharedPreferences.getString(eq(hostnameKey), anyString())).
                thenReturn(EXPECTED);

        String actual = credentials.getHostname();

        assertEquals("Hostname must be the same as provided", EXPECTED, actual);
    }

    /**
     * Tests if the actual stored port is returned.
     */
    @Test
    public void testGetPort() {
        String portKey = getString(R.string.port_key);
        when(sharedPreferences.getString(eq(portKey), anyString())).
                thenReturn(EXPECTED);

        String actual = credentials.getPort();

        assertEquals("Port must be the same as provided", EXPECTED, actual);
    }

    /**
     * Tests if the actual stored username is returned.
     */
    @Test
    public void testGetUsername() {
        String usernameKey = getString(R.string.username_key);
        when(sharedPreferences.getString(eq(usernameKey), anyString())).
                thenReturn(EXPECTED);

        String actual = credentials.getUsername();

        assertEquals("Username must be the same as provided", EXPECTED, actual);
    }

    /**
     * Tests if the actual stored token is returned.
     */
    @Test
    public void testGetToken() {
        String tokenKey = getString(R.string.authentication_token_key);
        when(sharedPreferences.getString(eq(tokenKey), anyString())).
                thenReturn(EXPECTED);

        String actual = credentials.getToken();

        assertEquals("Token must be the same as provided", EXPECTED, actual);
    }

    /**
     * Tests if the actual stored value for isAuthenticationNeeded is returned,
     * if the value is <code>true</code>.
     */
    @Test
    public void testIsAuthenticationNeededIsTrue() {
        String authenticationNeededKey = getString(R.string.authentication_needed_key);
        boolean expected = true;
        when(sharedPreferences.getBoolean(eq(authenticationNeededKey), anyBoolean())).thenReturn(expected);

        boolean actual = credentials.isAuthenticationNeeded();

        assertTrue("Authentication is set to 'needed'", actual);
    }

    /**
     * Tests if the actual stored value for isAuthenticationNeeded is returned,
     * if the value is <code>false</code>.
     */
    @Test
    public void testIsAuthenticationNeededIsFalse() {
        String authenticationNeededKey = getString(R.string.authentication_needed_key);
        boolean expected = false;
        when(sharedPreferences.getBoolean(eq(authenticationNeededKey), anyBoolean())).thenReturn(expected);

        boolean actual = credentials.isAuthenticationNeeded();

        assertFalse("Authentication is set to 'not needed'", actual);
    }

    private class TestModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(SharedPreferences.class).toInstance(sharedPreferences);
        }
    }
}
