package edu.hm.cs.jenkins.notifier;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

/**
 * Defines common behaviour for all tests.
 *
 * @author Tobias Wochinger
 */
public class BaseTest {

    /**
     * Initializes the test environment for each test case.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Injects the members of a class.
     *
     * @param testModule module which allows to inject mocks
     */
    public void injectMembers(final Module testModule) {
        Injector injector = Guice.createInjector(Modules.override(new BindingModule()).with(testModule));
        injector.injectMembers(this);
    }
}
