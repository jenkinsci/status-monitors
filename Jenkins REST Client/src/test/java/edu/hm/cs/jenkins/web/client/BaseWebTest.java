package edu.hm.cs.jenkins.web.client;

import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;
import edu.hm.cs.jenkins.web.security.Authentication;
import junit.framework.TestCase;
import org.junit.Rule;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.*;

/**
 * Abstract Test which provides some basic values / methods for testing
 * with the Wiremock-Framework.
 *
 * @author Tobias Wochinger
 */
public abstract class BaseWebTest {

    protected static final int TEST_PORT = 8089;

    protected static final String MOCK_SERVER_URL = "http://localhost:" + TEST_PORT;

    protected static final int VERIFY_TIME_OUT = 1000;

    private static final String CONTENT_HEADER_FIELD = "Content-Type";

    private static final String AUTHORIZATION_HEADER_FIELD = "Authorization";

    private static final String JSON_MIME = "application/json";

    protected static final String TEST_USERNAME = "Max Mustermann";

    protected static final String TEST_TOKEN = "valueDoesNotMatter";

    @Rule
    //CHECKSTYLE IGNORE VisibilityModifierCheck FOR NEXT 1 LINES"
    public WireMockRule wireMockRule = new WireMockRule(TEST_PORT);

    /**
     * Creates a mock-server which returns a content.
     *
     * @param url                  url on which the mock-server is listening for requests
     * @param fileWithBodyToReturn content the mock-server returns
     *                             if there is a request
     */
    protected void createMockServer(final String url, final String fileWithBodyToReturn) {
        stubFor(get(urlEqualTo(url)).willReturn(
                aResponse().withHeader(CONTENT_HEADER_FIELD, JSON_MIME).withBodyFile(fileWithBodyToReturn)));
    }

    /**
     * Creates a mock-server which returns only a status.
     *
     * @param url    url on which the mock-server is listening for requests
     * @param status status which the mock-server returns if there is a request.
     */
    protected void createMockServer(final String url, final int status) {
        stubFor(post(urlEqualTo(url)).willReturn(aResponse().withStatus(status)));
    }

    /**
     * Every test has a different subdirectory for its resources.
     *
     * @param fileName name of the file
     * @return concatenated resource path
     */
    protected abstract String getResourceUrl(final String fileName);

    /**
     * Verifies that the authorization header is not part of the performed request.
     * @param url target url of the made request
     */
    protected void verifyHeaderAbsence(final String url) {
        List<LoggedRequest> requests = getRequests(url);
        boolean isHeaderPresent = requests.get(0).containsHeader(AUTHORIZATION_HEADER_FIELD);
        assertFalse("Constructor without header was chosen", isHeaderPresent);
    }

    private List<LoggedRequest> getRequests(final String url) {
        List<LoggedRequest> requests = findAll(getRequestedFor(urlEqualTo(url)));
        TestCase.assertEquals("Must be only one request", 1, requests.size());
        return requests;
    }

    /**
     * Verifies that the authorization header is part of the performed request.
     * @param url target url of the made request
     * @param expected expected header value
     */
    protected void verifyHeaderPresence(final String url, final String expected) {
        List<LoggedRequest> requests = getRequests(url);

        assertEquals("Must be only one request", 1, requests.size());
        HttpHeader authorization = requests.get(0).header(AUTHORIZATION_HEADER_FIELD);
        assertNotNull("Header must be present", authorization);
        assertEquals("Header-Content must be as expected", expected, authorization.firstValue());
    }

    /**
     * Returns an authentication object for testing.
     * @return authentication with username and token
     */
    protected Authentication getTestAuthentication() {
        return new Authentication(TEST_USERNAME, TEST_TOKEN);
    }
}
