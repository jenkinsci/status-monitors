package edu.hm.cs.jenkins.notifier.provider;

import edu.hm.cs.jenkins.web.JenkinsClientFactory;
import edu.hm.cs.jenkins.web.util.CredentialsLoader;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

/**
 * Provides an instance of {@link edu.hm.cs.jenkins.web.JenkinsClientFactory}.
 *
 * @author Tobias Wochinger
 */
public class ClientFactoryProvider implements Provider<JenkinsClientFactory> {

    @Inject @Named("stored")
    private CredentialsLoader loader;

    @Override
    public JenkinsClientFactory get() {
        return new JenkinsClientFactory(loader);
    }
}
