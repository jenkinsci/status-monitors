package edu.hm.cs.jenkins.monitor.controller.web.callback;

import android.widget.ArrayAdapter;
import javax.inject.Inject;
import com.google.inject.TypeLiteral;
import edu.hm.cs.jenkins.monitor.BasicTestModule;
import edu.hm.cs.jenkins.web.model.Build;
import edu.hm.cs.jenkins.web.model.Job;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests the class {@link edu.hm.cs.jenkins.monitor.controller.web.callback.BuildsCallback}.
 * @author Tobias Wochinger
 */
@RunWith(RobolectricTestRunner.class)
public class BuildsCallbackTest extends BaseAdapterCallbackTest<Build> {

    @Mock
    private Job job;

    @Mock
    private List<Build> builds;

    @Inject
    private BuildsCallback callback;

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
        when(job.getBuilds()).thenReturn(builds);

        callback.onSuccess(job);

        verifyBasicListCallbackBehaviour();
        verify(getAdapter()).addAll(builds);
    }

    private class TestModule extends BasicTestModule {

        @Override
        protected void configure() {
            bindSilentDialog();
            bind(new TypeLiteral<ArrayAdapter<Build>>() { }).toInstance(getAdapter());
        }
    }
}
