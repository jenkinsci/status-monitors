package edu.hm.cs.jenkins.web;

import edu.hm.cs.jenkins.web.client.BuildClient;
import edu.hm.cs.jenkins.web.client.JobClient;
import edu.hm.cs.jenkins.web.util.CredentialsLoader;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests the class {@link JenkinsClientFactory}.
 * @author Tobias Wochinger
 */
public class ConcreteClientFactoryTest extends BaseTest {

    @Mock
    private CredentialsLoader credentials;

    private JenkinsClientFactory factory;

    private void initCredentialsMock(final boolean authenticationNeeded) {
        when(credentials.getHostname()).thenReturn("http://test.de");
        when(credentials.getPort()).thenReturn("8080");
        when(credentials.isAuthenticationNeeded()).thenReturn(authenticationNeeded);
        when(credentials.getUsername()).thenReturn("username");
        when(credentials.getToken()).thenReturn("Token");
    }

    /**
     * Tests the creation of a job client if authentication is needed.
     */
    @Test
    public void testCreateJobClientWithAuthentication() {
        initCredentialsMock(true);
        assertJobClientNotNull();
    }

    private void assertJobClientNotNull() {
        factory = new JenkinsClientFactory(credentials);

        JobClient actual = factory.createJobClient();

        assertNotNull("Factory must provide a value", actual);
    }

    /**
     * Tests the creation of a job client if no authentication is needed.
     */
    @Test
    public void testCreateJobClientWithoutAuthentication() {
        initCredentialsMock(false);
        assertJobClientNotNull();
    }

    /**
     * Tests the creation of a build client if authentication is needed.
     */
    @Test
    public void testCreateBuildClientWithAuthentication() {
        initCredentialsMock(true);
        assertBuildClientNotNull();
    }

    private void assertBuildClientNotNull() {
        factory = new JenkinsClientFactory(credentials);

        BuildClient actual = factory.createBuildClient();

        assertNotNull("Factory must provide a value", actual);
    }

    /**
     * Tests the creation of a build client if no authentication is needed.
     */
    @Test
    public void testCreateBuildClientWithoutAuthentication() {
        initCredentialsMock(false);
        assertBuildClientNotNull();
    }
}
