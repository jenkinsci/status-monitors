package edu.hm.cs.jenkins.monitor.controller.fragment;

import android.support.v4.app.FragmentManager;
import android.widget.ArrayAdapter;
import com.google.inject.TypeLiteral;
import edu.hm.cs.jenkins.monitor.BaseTest;
import edu.hm.cs.jenkins.monitor.BasicTestModule;
import edu.hm.cs.jenkins.monitor.R;
import edu.hm.cs.jenkins.monitor.controller.fragment.list.BuildFragment;
import edu.hm.cs.jenkins.monitor.controller.util.NetworkUtil;
import edu.hm.cs.jenkins.monitor.view.adapter.BuildAdapter;
import edu.hm.cs.jenkins.web.ClientFactory;
import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.callback.ErrorCallback;
import edu.hm.cs.jenkins.web.client.BuildClient;
import edu.hm.cs.jenkins.web.client.JobClient;
import edu.hm.cs.jenkins.web.model.Build;
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

import static org.mockito.Mockito.*;
import static org.robolectric.util.FragmentTestUtil.startFragment;

/**
 * Tests the class
 * {@link edu.hm.cs.jenkins.monitor.controller.fragment.list.BuildFragment}.
 *
 * @author Tobias Wochinger
 */
@RunWith(RobolectricTestRunner.class)
public class BuildFragmentTest extends BaseTest {

    @Inject
    private BuildFragment buildFragment;

    @Mock
    private ClientFactory factory;

    @Mock
    private BuildClient buildClient;

    @Mock
    private JobClient jobClient;

    @Mock
    private ClientCallback<Job> buildsCallback;

    @Mock
    private ErrorCallback triggerCallback;

    @Mock
    private Job job;

    @Mock
    private NetworkUtil networkUtil;

    @Mock
    private ValidationUtil validationUtil;

    @Mock
    private BuildAdapter buildAdapter;

    @Mock
    private CredentialsLoader loader;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        injectMembers(new TestModule());

        configureClientFactory();
        configureBuildAdapter();
    }

    private void configureClientFactory() {
        when(factory.createBuildClient()).thenReturn(buildClient);
        when(factory.createJobClient()).thenReturn(jobClient);
    }

    private void configureBuildAdapter() {
        when(buildAdapter.getViewTypeCount()).thenReturn(1);
    }

    /**
     * Tests that the builds are requested if the buildfragment starts.
     */
    @Test
    public void testRetrieveBuildsOnStart() {
        when(networkUtil.isNetworkAvailable()).thenReturn(true);
        when(validationUtil.areCredentialsValid()).thenReturn(true);
        buildFragment.setJob(job);

        startFragment(buildFragment);

        verify(buildClient).getAllBuilds(job, buildsCallback);
    }

    /**
     * Tests if an error alert is shown and no client call is performed if the buildfragment starts and
     * no network connection is available.
     */
    @Test
    public void testRetrieveBuildsOnStartWithoutNetworkConnection() {
        when(networkUtil.isNetworkAvailable()).thenReturn(false);
        when(validationUtil.areCredentialsValid()).thenReturn(true);
        buildFragment.setJob(job);

        startFragment(buildFragment);

        verify(networkUtil).showAlert();
        verifyZeroInteractions(buildClient);
    }

    /**
     * Tests if an error alert is shown if the buildfragment starts and no client call is performed and
     * the credentials are invalid.
     */
    @Test
    public void testRetrieveBuildsOnStartWithInvalidCredentials() {
        when(networkUtil.isNetworkAvailable()).thenReturn(true);
        when(validationUtil.areCredentialsValid()).thenReturn(false);
        buildFragment.setJob(job);

        startFragment(buildFragment);

        verify(validationUtil).showFailedValidations();
        verifyZeroInteractions(buildClient);
    }

    /**
     * Tests that the builds are requested if the buildfragment is refreshed
     * by the user.
     */
    @Test
    public void testRetrieveBuildsOnRefresh() {
        when(networkUtil.isNetworkAvailable()).thenReturn(true);
        when(validationUtil.areCredentialsValid()).thenReturn(true);
        buildFragment.setJob(job);
        TestMenuItem refresh = new TestMenuItem(R.id.refresh_list);

        buildFragment.onOptionsItemSelected(refresh);

        verify(buildClient).getAllBuilds(job, buildsCallback);
    }

    /**
     * Tests if an error alert is shown and no client call is performed  if the buildfragment is refreshed
     * by the user and no network connection is available.
     */
    @Test
    public void testRetrieveBuildsOnRefreshWithoutNetworkConnection() {
        when(networkUtil.isNetworkAvailable()).thenReturn(false);
        when(validationUtil.areCredentialsValid()).thenReturn(true);
        buildFragment.setJob(job);
        TestMenuItem refresh = new TestMenuItem(R.id.refresh_list);

        buildFragment.onOptionsItemSelected(refresh);

        verify(networkUtil).showAlert();
        verifyZeroInteractions(buildClient);
    }

    /**
     * Tests if an error alert is shown and no client call is performed  if the buildfragment is refreshed
     * by the user and the credentials are invalid.
     */
    @Test
    public void testRetrieveBuildsOnRefreshWithInvalidCredentials() {
        when(networkUtil.isNetworkAvailable()).thenReturn(true);
        when(validationUtil.areCredentialsValid()).thenReturn(false);
        buildFragment.setJob(job);
        TestMenuItem refresh = new TestMenuItem(R.id.refresh_list);

        buildFragment.onOptionsItemSelected(refresh);

        verify(validationUtil).showFailedValidations();
        verifyZeroInteractions(buildClient);
    }

    /**
     * Tests if a build is triggered if the user clicks it.
     */
    @Test
    public void testTriggerOnClick() {
        when(networkUtil.isNetworkAvailable()).thenReturn(true);
        when(validationUtil.areCredentialsValid()).thenReturn(true);
        buildFragment.setJob(job);
        TestMenuItem refresh = new TestMenuItem(R.id.trigger_builds);

        buildFragment.onOptionsItemSelected(refresh);

        verify(jobClient).triggerJob(job, triggerCallback);
    }

    /**
     * Tests if an error alert is shown and no client call is performed if the user clicks the
     * trigger button and no network connection is available.
     */
    @Test
    public void testTriggerOnClickWithoutNetworkConnection() {
        when(networkUtil.isNetworkAvailable()).thenReturn(false);
        when(validationUtil.areCredentialsValid()).thenReturn(true);
        buildFragment.setJob(job);
        TestMenuItem refresh = new TestMenuItem(R.id.trigger_builds);

        buildFragment.onOptionsItemSelected(refresh);

        verify(networkUtil).showAlert();
        verifyZeroInteractions(buildClient);
    }

    /**
     * Tests if an error alert is shown and no client call is performed if the user clicks the
     * trigger button and no network connection is available.
     */
    @Test
    public void testTriggerOnClickWithInvalidCredentials() {
        when(networkUtil.isNetworkAvailable()).thenReturn(true);
        when(validationUtil.areCredentialsValid()).thenReturn(false);
        buildFragment.setJob(job);
        TestMenuItem refresh = new TestMenuItem(R.id.trigger_builds);

        buildFragment.onOptionsItemSelected(refresh);

        verify(validationUtil).showFailedValidations();
        verifyZeroInteractions(buildClient);
    }


    private class TestModule extends BasicTestModule {

        @Override
        protected void configure() {
            bindSilentDialog();
            bind(FragmentManager.class).toInstance(getFragmentManager());
            bind(ClientFactory.class).toInstance(factory);
            bind(new TypeLiteral<ClientCallback<Job>>() { }).toInstance(buildsCallback);
            bind(NetworkUtil.class).toInstance(networkUtil);
            bind(new TypeLiteral<ArrayAdapter<Build>>() { }).toInstance(buildAdapter);
            bind(ErrorCallback.class).toInstance(triggerCallback);
            bind(CredentialsLoader.class).toInstance(loader);
            bind(ValidationUtil.class).toInstance(validationUtil);
        }
    }
}