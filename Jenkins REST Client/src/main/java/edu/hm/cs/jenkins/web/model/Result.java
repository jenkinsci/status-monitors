package edu.hm.cs.jenkins.web.model;

/**
 * Describes the result of a {@link edu.hm.cs.jenkins.web.model.Build}.
 *
 * @author Tobias Wochinger
 */
public enum Result {

    SUCCESS,
    UNSTABLE,
    FAILURE,
    NOT_BUILT,
    ABORTED
}
