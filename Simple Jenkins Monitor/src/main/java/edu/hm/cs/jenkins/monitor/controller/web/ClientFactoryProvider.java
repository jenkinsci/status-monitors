package edu.hm.cs.jenkins.monitor.controller.web;

import com.google.inject.Inject;
import edu.hm.cs.jenkins.web.JenkinsClientFactory;
import edu.hm.cs.jenkins.web.util.CredentialsLoader;

import javax.inject.Provider;

/**
 * Provides an instance of {@link edu.hm.cs.jenkins.web.JenkinsClientFactory}.
 *
 * @author Tobias Wochinger
 */
public class ClientFactoryProvider implements Provider<JenkinsClientFactory> {

    @Inject
    private CredentialsLoader credentials;

    @Override
    public JenkinsClientFactory get() {
        return new JenkinsClientFactory(credentials);
    }
}
