package edu.hm.cs.jenkins.monitor.controller.web.callback;

import android.widget.ArrayAdapter;
import javax.inject.Inject;
import com.google.inject.TypeLiteral;
import edu.hm.cs.jenkins.monitor.BasicTestModule;
import edu.hm.cs.jenkins.web.model.Job;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Tests the class {@link edu.hm.cs.jenkins.monitor.controller.web.callback.JobsCallback}.
 * @author Tobias Wochinger
 */
@RunWith(RobolectricTestRunner.class)
public class JobsCallbackTest extends BaseAdapterCallbackTest<Job> {

    @Mock
    private List<Job> jobs;

    @Inject
    private JobsCallback callback;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
    }

    /**
     * Tests the success method of the callback.
     */
    @Test
    public void testOnSuccess() {
        callback.onSuccess(jobs);

        verifyBasicListCallbackBehaviour();
        verify(getAdapter()).addAll(jobs);
    }

    private class TestModule extends BasicTestModule {

        @Override
        protected void configure() {
            bindSilentDialog();
            bind(new TypeLiteral<ArrayAdapter<Job>>() { }).toInstance(getAdapter());
        }
    }
}
