package edu.hm.cs.jenkins.monitor.controller.web.callback;

import com.google.inject.name.Names;
import edu.hm.cs.jenkins.monitor.BaseTest;
import edu.hm.cs.jenkins.monitor.BasicTestModule;
import edu.hm.cs.jenkins.monitor.R;
import edu.hm.cs.jenkins.monitor.view.dialog.Dialog;
import edu.hm.cs.jenkins.web.callback.ClientError;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import javax.inject.Inject;

import static org.mockito.Mockito.verify;

/**
 * Tests the class {@link edu.hm.cs.jenkins.monitor.controller.web.callback.ErrorAsDialogCallback}.
 * @author Tobias Wochinger
 */
@RunWith(RobolectricTestRunner.class)
public class ErrorAsDialogCallbackTest extends BaseTest {

    @Mock
    private Dialog dialog;

    @Mock
    private ClientError error;

    @Inject
    private TestCallback callback;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());
    }

    /**
     * Tests the displaying of the dialog in case of error.
     */
    @Test
    public void testErrorCase() {
        callback.onError(error);

        verify(dialog).show(R.string.jenkins_client_error);
    }

    private static class TestCallback extends ErrorAsDialogCallback<Object> {

        @Override
        public void onSuccess(final Object result) {
            //not needed
        }
    }

    private class TestModule extends BasicTestModule {

        @Override
        protected void configure() {
            bind(Dialog.class).annotatedWith(Names.named("silent")).toInstance(dialog);
        }
    }
}
