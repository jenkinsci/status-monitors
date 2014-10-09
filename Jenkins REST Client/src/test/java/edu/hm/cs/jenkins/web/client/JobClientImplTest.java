package edu.hm.cs.jenkins.web.client;

import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.callback.ErrorCallback;
import edu.hm.cs.jenkins.web.model.BallColor;
import edu.hm.cs.jenkins.web.model.Job;
import net.iharder.Base64;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.charset.Charset;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Tests the class {@link edu.hm.cs.jenkins.web.client.JobClientImpl}.
 *
 * @author Tobias Wochinger
 */
@SuppressWarnings("unchecked")
public class JobClientImplTest extends BaseWebTest {

    private static final int HTTP_CREATED = 201;

    private static final String JOBS_URL = "/api/json?tree=jobs[name,url,description,displayName,color]";

    private static final String TEST_JOBNAME = "Jobname";

    private static final String TEST_TOKEN = "valueDoesNotMatter";

    private static final String BASE_RESOURCES_URL = "jobTests/";

    private JobClient client;

    @Mock
    private ClientCallback<List<Job>> callback;

    @Captor
    private ArgumentCaptor<List<Job>> jobsCaptor;

    /**
     * Initializes the test environment for each test.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        client = new JobClientImpl(MOCK_SERVER_URL, getTestAuthentication());
    }

    /**
     * Tests if authorization header is present.
     */
    @Test
    public void testHeader() {
        createMockServer(JOBS_URL, getResourceUrl("emptyJobs.json"));
        String authorizationValues = TEST_USERNAME + ":" + TEST_TOKEN;
        String expectedHeaderValue = "Basic " + Base64.encodeBytes(authorizationValues.getBytes(
                Charset.defaultCharset()));

        client.getAllJobs(callback);
        verify(callback, timeout(VERIFY_TIME_OUT)).onSuccess(jobsCaptor.capture());

        verifyHeaderPresence(JOBS_URL, expectedHeaderValue);
    }

    /**
     * Tests if authorization header is not present, if client without authorization was chosen.
     */
    @Test
    public void testWithoutHeader() {
        createMockServer(JOBS_URL, getResourceUrl("emptyJobs.json"));
        client = new JobClientImpl(MOCK_SERVER_URL);

        client.getAllJobs(callback);
        verify(callback, timeout(VERIFY_TIME_OUT)).onSuccess(jobsCaptor.capture());

        verifyHeaderAbsence(JOBS_URL);
    }

    /**
     * Tests to receive all jobs if the server return 0 jobs.
     */
    @Test
    public void testWithZeroJobs() {
        createMockServer(JOBS_URL, getResourceUrl("emptyJobs.json"));

        client.getAllJobs(callback);

        verify(callback, timeout(VERIFY_TIME_OUT)).onSuccess(jobsCaptor.capture());

        List<Job> result = jobsCaptor.getValue();
        validateListLength(result, 0);
    }

    @Override
    protected String getResourceUrl(final String fileName) {
        return BASE_RESOURCES_URL + fileName;
    }

    private void validateListLength(final List<Job> jobs, final int expectedSize) {
        assertEquals("List must have correct number of elements", expectedSize, jobs.size());
    }

    /**
     * Tests to receive all jobs if the server return 1 job.
     */
    @Test
    public void testWithOneJob() {
        createMockServer(JOBS_URL, getResourceUrl("oneJob.json"));

        client.getAllJobs(callback);

        verify(callback, timeout(VERIFY_TIME_OUT)).onSuccess(jobsCaptor.capture());

        List<Job> result = jobsCaptor.getValue();
        validateListLength(result, 1);
        validateSmartJenkinsJob(result);
    }

    private void validateSmartJenkinsJob(final List<Job> jobs) {
        Job expected = new Job();
        expected.setDescription("Test-Description");
        expected.setDisplayName("SmartJenkins");
        expected.setName("SmartJenkins");
        expected.setUrl("http://192.168.178.37:8080/job/SmartJenkins/");
        expected.setColor(BallColor.BLUE);

        assertTrue("Equal Element must be in list", jobs.contains(expected));
    }

    /**
     * Tests to receive all jobs if the server return 2 jobs.
     */
    @Test
    public void testWithTwoJobs() {
        createMockServer(JOBS_URL, getResourceUrl("twoJobs.json"));

        client.getAllJobs(callback);

        verify(callback, timeout(VERIFY_TIME_OUT)).onSuccess(jobsCaptor.capture());

        List<Job> result = jobsCaptor.getValue();
        validateListLength(result, 2);
        validateSmartJenkinsJob(result);
        validateWebserviceJob(result);
    }

    private void validateWebserviceJob(final List<Job> jobs) {
        Job expected = new Job();
        expected.setDescription("Test-Description2");
        expected.setDisplayName("Webservice");
        expected.setName("Webservice");
        expected.setUrl("http://192.168.178.37:8080/job/Webservice/");
        expected.setColor(BallColor.NOTBUILT);

        assertTrue("Equal Element must be in list", jobs.contains(expected));
    }

    /**
     * Tests the correct parsing from all possible ball colors to enum.
     */
    @Test
    public void testBallColorParsing() {
        createMockServer(JOBS_URL, getResourceUrl("jobsWithAllColors.json"));
        int expectedNumberOfBallColors = 14;

        client.getAllJobs(callback);

        verify(callback, timeout(VERIFY_TIME_OUT)).onSuccess(jobsCaptor.capture());

        List<Job> result = jobsCaptor.getValue();
        validateListLength(result, expectedNumberOfBallColors);
        validateColors(result);
    }

    private void validateColors(final List<Job> result) {
        for (Job job : result) {
            BallColor ballColor = job.getColor();
            assertNotNull("Each color must have a value", ballColor);
        }
    }

    /**
     * Tests the triggering of a job.
     */
    @Test
    public void testTriggerJob() {
        String targetUrl = "/job/" + TEST_JOBNAME + "/build?token=" + TEST_TOKEN;
        createMockServer(targetUrl, HTTP_CREATED);

        Job jobToTrigger = mock(Job.class);
        when(jobToTrigger.getName()).thenReturn(TEST_JOBNAME);

        ErrorCallback callback = mock(ErrorCallback.class);

        client.triggerJob(jobToTrigger, callback);
        verify(callback, timeout(VERIFY_TIME_OUT)).onSuccess(jobsCaptor.capture());
    }
}
