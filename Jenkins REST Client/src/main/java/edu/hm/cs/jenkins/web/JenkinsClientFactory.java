package edu.hm.cs.jenkins.web;

import edu.hm.cs.jenkins.web.client.BuildClient;
import edu.hm.cs.jenkins.web.client.BuildClientImpl;
import edu.hm.cs.jenkins.web.client.JobClient;
import edu.hm.cs.jenkins.web.client.JobClientImpl;
import edu.hm.cs.jenkins.web.security.Authentication;
import edu.hm.cs.jenkins.web.util.CredentialsCleanerUtil;
import edu.hm.cs.jenkins.web.util.CredentialsLoader;
import edu.hm.cs.jenkins.web.util.UrlBuilder;

/**
 * Factory which provide instances of {@link edu.hm.cs.jenkins.web.client.JobClient} && {@link
 * edu.hm.cs.jenkins.web.client.BuildClient}.
 * <p/>
 * Those provided clients already include the credentials (url and
 * authentication data).
 *
 * @author Tobias Wochinger
 */
public class JenkinsClientFactory implements ClientFactory {

    private final CredentialsLoader credentials;

    /**
     * Creates a client factory.
     * @param credentials credentials needed for a client call
     */
    public JenkinsClientFactory(final CredentialsLoader credentials) {
        this.credentials = credentials;
    }

    @Override
    public JobClient createJobClient() {
        String targetUrl = buildUrl();

        if (credentials.isAuthenticationNeeded()) {
            Authentication authentication = createAuthentication();
            return new JobClientImpl(targetUrl, authentication);
        } else {
            return new JobClientImpl(targetUrl);
        }
    }

    private String buildUrl() {
        String hostname = credentials.getHostname();
        hostname = CredentialsCleanerUtil.cleanHostname(hostname);

        String port = credentials.getPort();
        port = CredentialsCleanerUtil.clean(port);

        return UrlBuilder.buildUrl(hostname, port);
    }

    private Authentication createAuthentication() {
        String username = credentials.getUsername();
        username = CredentialsCleanerUtil.clean(username);

        String token = credentials.getToken();
        token = CredentialsCleanerUtil.clean(token);

        return new Authentication(username, token);
    }

    @Override
    public BuildClient createBuildClient() {
        String targetUrl = buildUrl();

        if (credentials.isAuthenticationNeeded()) {
            Authentication authentication = createAuthentication();
            return new BuildClientImpl(targetUrl, authentication);
        } else {
            return new BuildClientImpl(targetUrl);
        }
    }

}
