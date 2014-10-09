package edu.hm.cs.jenkins.web.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the class {@link edu.hm.cs.jenkins.web.util.UrlBuilder}.
 *
 * @author Tobias Wochinger
 */
public class UrlBuilderTest {

    private static final String TEST_HTTP_HOST = "http://example.de";

    private static final String TEST_HTTPS_HOST = "https://example.de";

    private static final String TEST_PORT = "8080";

    /**
     * Tests the class with valid parameters and protocol http.
     */
    @Test
    public void testWithValidParamsHttp() {
        String expected = TEST_HTTP_HOST + ":" + TEST_PORT;

        String actual = UrlBuilder.buildUrl(TEST_HTTP_HOST, TEST_PORT);

        assertEquals("Url must be build correct", expected, actual);
    }

    /**
     * Tests the class with valid parameters and protocol https.
     */
    @Test
    public void testWithValidParamsHttps() {
        String expected = TEST_HTTPS_HOST + ":" + TEST_PORT;

        String actual = UrlBuilder.buildUrl(TEST_HTTPS_HOST, TEST_PORT);

        assertEquals("Url must be build correct", expected, actual);
    }

    /**
     * Tests the class with an invalid host.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWithInvalidHost() {
        UrlBuilder.buildUrl(null, TEST_PORT);
    }

    /**
     * Tests the class with an invalid url.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWithInvalidPort() {
        UrlBuilder.buildUrl(TEST_HTTP_HOST, null);
    }

    /**
     * Tests the class with invalid host and port.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWithBothParamsInvalid() {
        UrlBuilder.buildUrl(null, null);
    }
}
