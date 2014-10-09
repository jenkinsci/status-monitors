package edu.hm.cs.jenkins.web;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

/**
 * Common behaviour for tests.
 * @author Tobias Wochinger
 */
public class BaseTest {

    /**
     * Set up of the test environment.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
}
