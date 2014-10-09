package edu.hm.cs.jenkins.web;

import edu.hm.cs.jenkins.web.client.BuildClient;
import edu.hm.cs.jenkins.web.client.JobClient;

/**
 * Base class for factories which provide instances of {@link edu.hm.cs.jenkins.web.client.JobClient} && {@link
 * edu.hm.cs.jenkins.web.client.BuildClient}.
 *
 * @author Tobias Wochinger
 */
public interface ClientFactory {

    /**
     * Creates an job client.
     * Depending whether authentication is needed or not, the header is set.
     *
     * @return prepared job client.
     */
    JobClient createJobClient();

    /**
     * Creates an build client.
     * Depending whether authentication is needed or not, the header is set.
     *
     * @return prepared job client.
     */
    BuildClient createBuildClient();
}
