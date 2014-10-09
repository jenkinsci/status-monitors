package edu.hm.cs.jenkins.web.model;

import java.util.List;

/**
 * Wraps a list of {@link edu.hm.cs.jenkins.web.model.Job}.
 * Needed for correct JSON-Parsing.
 *
 * @author Tobias Wochinger
 */
public class Jobs {

    private List<Job> jobs;

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(final List<Job> jobs) {
        this.jobs = jobs;
    }
}
