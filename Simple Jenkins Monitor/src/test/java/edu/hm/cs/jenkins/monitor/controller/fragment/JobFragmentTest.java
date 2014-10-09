package edu.hm.cs.jenkins.monitor.controller.fragment;

import android.support.v4.app.FragmentManager;
import android.widget.ArrayAdapter;
import com.google.inject.TypeLiteral;
import edu.hm.cs.jenkins.monitor.BaseTest;
import edu.hm.cs.jenkins.monitor.BasicTestModule;
import edu.hm.cs.jenkins.monitor.R;
import edu.hm.cs.jenkins.monitor.controller.fragment.list.JobFragment;
import edu.hm.cs.jenkins.monitor.controller.util.FragmentUtil;
import edu.hm.cs.jenkins.monitor.controller.util.NetworkUtil;
import edu.hm.cs.jenkins.monitor.view.adapter.JobAdapter;
import edu.hm.cs.jenkins.web.ClientFactory;
import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.client.JobClient;
import edu.hm.cs.jenkins.web.model.Job;
import edu.hm.cs.jenkins.web.util.CredentialsLoader;
import edu.hm.cs.jenkins.web.util.ValidationUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.tester.android.view.TestMenuItem;

import javax.inject.Inject;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.robolectric.util.FragmentTestUtil.startFragment;

/**
 * Tests the class
 * {@link edu.hm.cs.jenkins.monitor.controller.fragment.list.JobFragment}.
 *
 * @author Tobias Wochinger
 */
@RunWith(RobolectricTestRunner.class)
public class JobFragmentTest extends BaseTest {

    @Inject
    private JobFragment jobFragment;

    @Mock
    private ClientFactory factory;

    @Mock
    private JobClient jobClient;

    @Mock
    private ClientCallback<List<Job>> callback;

    @Mock
    private FragmentUtil fragmentUtil;

    @Mock
    private JobAdapter jobAdapter;

    @Mock
    private CredentialsLoader credentialsLoader;

    @Mock
    private NetworkUtil networkUtil;

    @Mock
    private ValidationUtil validationUtil;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());

        configureClientFactory();
        configureJobAdapter();
    }

    private void configureClientFactory() {
        when(factory.createJobClient()).thenReturn(jobClient);
    }

    private void configureJobAdapter() {
        //needed so that there is exception at the start of the fragment
        when(jobAdapter.getViewTypeCount()).thenReturn(1);
    }

    /**
     * Tests that the jobs are requested if the jobfragment starts.
     */
    @Test
    public void testRetrieveJobOnStart() {
        when(networkUtil.isNetworkAvailable()).thenReturn(true);
        when(validationUtil.areCredentialsValid()).thenReturn(true);

        startFragment(jobFragment);

        verify(jobClient).getAllJobs(callback);
    }

    /**
     * Tests if an error alert is shown and no client call is performed if the jobFragment starts and
     * no network connection is available.
     */
    @Test
    public void testRetrieveJobOnStartWithoutNetworkConnection() {
        when(networkUtil.isNetworkAvailable()).thenReturn(false);
        when(validationUtil.areCredentialsValid()).thenReturn(true);

        startFragment(jobFragment);

        verify(networkUtil).showAlert();
        verifyZeroInteractions(jobClient);
    }

    /**
     * Tests if an error alert is shown and no client call is performed if the jobFragment starts and
     * the credentials are not valid.
     */
    @Test
    public void testRetrieveJobOnStartWithInvalidCredentials() {
        when(networkUtil.isNetworkAvailable()).thenReturn(true);
        when(validationUtil.areCredentialsValid()).thenReturn(false);

        startFragment(jobFragment);

        verify(validationUtil).showFailedValidations();
        verifyZeroInteractions(jobClient);
    }

    /**
     * Tests that the jobs are requested if the jobFragment is refreshed
     * by the user.
     */
    @Test
    public void testRetrieveJobsOnRefresh() {
        when(networkUtil.isNetworkAvailable()).thenReturn(true);
        when(validationUtil.areCredentialsValid()).thenReturn(true);

        TestMenuItem refresh = new TestMenuItem(R.id.refresh_list);

        jobFragment.onOptionsItemSelected(refresh);

        verify(jobClient).getAllJobs(callback);
    }

    /**
     * Tests if an error alert is shown and no client call is performed if the jobfragment is refreshed
     * by the user and no network connection is available.
     */
    @Test
    public void testRetrieveJobsOnRefreshWithoutNetworkConnection() {
        when(networkUtil.isNetworkAvailable()).thenReturn(false);
        when(validationUtil.areCredentialsValid()).thenReturn(true);

        TestMenuItem refresh = new TestMenuItem(R.id.refresh_list);

        jobFragment.onOptionsItemSelected(refresh);

        verify(networkUtil).showAlert();
        verifyZeroInteractions(jobClient);
    }

    /**
     * Tests if an error alert is shown and no client call is performed if the jobfragment is refreshed
     * by the user and the credentials are invalid.
     */
    @Test
    public void testRetrieveJobsOnRefreshWithInvalidCredentials() {
        when(networkUtil.isNetworkAvailable()).thenReturn(true);
        when(validationUtil.areCredentialsValid()).thenReturn(false);

        TestMenuItem refresh = new TestMenuItem(R.id.refresh_list);

        jobFragment.onOptionsItemSelected(refresh);

        verify(validationUtil).showFailedValidations();
        verifyZeroInteractions(jobClient);
    }

    /**
     * Tests if the builds of the job are shown if the job was clicked.
     */
    @Test
    public void testShowBuildFragmentOnListItemClick() {
        Job mocked = mock(Job.class);
        int position = 10;
        int exampleId = 0;
        when(jobAdapter.getItem(position)).thenReturn(mocked);

        jobFragment.onListItemClick(null, null, position, exampleId);

        verify(fragmentUtil).showBuildsFragment(mocked);
    }

    private class TestModule extends BasicTestModule {

        @Override
        protected void configure() {
            bindSilentDialog();
            bind(FragmentManager.class).toInstance(getFragmentManager());
            bind(NetworkUtil.class).toInstance(networkUtil);
            bind(ClientFactory.class).toInstance(factory);
            bind(CredentialsLoader.class).toInstance(credentialsLoader);
            bind(new TypeLiteral<ClientCallback<List<Job>>>() { }).toInstance(callback);
            bind(FragmentUtil.class).toInstance(fragmentUtil);
            bind(new TypeLiteral<ArrayAdapter<Job>>() { }).toInstance(jobAdapter);
            bind(ValidationUtil.class).toInstance(validationUtil);
        }
    }
}