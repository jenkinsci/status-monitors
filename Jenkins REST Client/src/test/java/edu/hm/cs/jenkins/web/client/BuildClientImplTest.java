package edu.hm.cs.jenkins.web.client;

import edu.hm.cs.jenkins.web.callback.ClientCallback;
import edu.hm.cs.jenkins.web.model.Build;
import edu.hm.cs.jenkins.web.model.Job;
import edu.hm.cs.jenkins.web.model.Result;
import net.iharder.Base64;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.charset.Charset;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

/**
 * Tests the class {@link edu.hm.cs.jenkins.web.client.BuildClientImpl}.
 *
 * @author Tobias Wochinger
 */
public class BuildClientImplTest extends BaseWebTest {

    private static final String TEST_JOBNAME = "Jobname";

    private static final String BUILDS_URL =
            "/job/" + TEST_JOBNAME + "/api/json?tree=builds[url,id,number,timestamp,duration,result]";

    private static final String LAST_BUILD_URL =
            "/job/" + TEST_JOBNAME + "/api/json?tree=lastBuild[url,id,number,timestamp," + "duration,result]";

    private static final String BASE_RESOURCES_URL = "buildTests/";

    private Job testJob;

    private BuildClient client;

    @Captor
    private ArgumentCaptor<Job> jobCaptor;

    @Mock
    private ClientCallback<Job> callback;

    /**
     * Initializes the test environment for each test.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testJob = createJob();
        client = new BuildClientImpl(MOCK_SERVER_URL, getTestAuthentication());
    }

    private Job createJob() {
        Job job = new Job();
        job.setName(TEST_JOBNAME);
        return job;
    }

    /**
     * Tests if authorization header is present.
     */
    @Test
    public void testHeader() {
        createMockServer(BUILDS_URL, getResourceUrl("emptyBuilds.json"));
        String authorizationValues = TEST_USERNAME + ":" + TEST_TOKEN;
        String expectedHeaderValue = "Basic " + Base64.encodeBytes(authorizationValues.getBytes(
                Charset.defaultCharset()));

        client.getAllBuilds(testJob, callback);
        verify(callback, timeout(VERIFY_TIME_OUT)).onSuccess(jobCaptor.capture());

        verifyHeaderPresence(BUILDS_URL, expectedHeaderValue);
    }

    /**
     * Tests if authorization header is not present, if client without authorization was chosen.
     */
    @Test
    public void testWithoutHeader() {
        createMockServer(BUILDS_URL, getResourceUrl("emptyBuilds.json"));
        client = new BuildClientImpl(MOCK_SERVER_URL);

        client.getAllBuilds(testJob, callback);
        verify(callback, timeout(VERIFY_TIME_OUT)).onSuccess(jobCaptor.capture());

        verifyHeaderAbsence(BUILDS_URL);
    }

    /**
     * Tests getAllBuilds if the server return 0 builds.
     */
    @Test
    public void testGetAllBuildsWithZeroBuilds() {
        createMockServer(BUILDS_URL, getResourceUrl("emptyBuilds.json"));

        client.getAllBuilds(testJob, callback);

        verify(callback, timeout(VERIFY_TIME_OUT)).onSuccess(jobCaptor.capture());
        Job result = jobCaptor.getValue();
        validateListLength(result, 0);
    }

    private void validateListLength(final Job job, final int expectedSize) {
        List<Build> receivedBuilds = job.getBuilds();
        assertEquals("Length must be equal", expectedSize, receivedBuilds.size());
    }

    /**
     * Tests getAllBuilds if the server return 1 build.
     */
    @Test
    public void testGetAllBuildsWithOneBuild() {
        createMockServer(BUILDS_URL, getResourceUrl("oneBuild.json"));

        client.getAllBuilds(testJob, callback);

        verify(callback, timeout(VERIFY_TIME_OUT)).onSuccess(jobCaptor.capture());
        Job result = jobCaptor.getValue();
        validateListLength(result, 1);
        validateFirstBuild(result);
    }

    private void validateFirstBuild(final Job job) {
        List<Build> builds = job.getBuilds();
        Build first = createTestBuild1();
        assertTrue("Element must be in list", builds.contains(first));
    }

    private Build createTestBuild1() {
        Build build = new Build();
        build.setDuration(60);
        build.setId("2014-08-13_11-50-54");
        build.setNumber(2);
        build.setResult(Result.SUCCESS);
        build.setTimestamp(1407923454741L);
        build.setUrl("http://192.168.178.37:8080/job/Neues%20Project/1/");
        return build;
    }

    /**
     * Tests getAllBuilds if the server return 2 builds.
     */
    @Test
    public void testGetAllBuildsWithTwoBuilds() {
        createMockServer(BUILDS_URL, getResourceUrl("twoBuilds.json"));

        client.getAllBuilds(testJob, callback);

        verify(callback, timeout(VERIFY_TIME_OUT)).onSuccess(jobCaptor.capture());
        Job result = jobCaptor.getValue();
        validateListLength(result, 2);
        validateFirstBuild(result);
        validateSecondBuild(result);
    }

    private void validateSecondBuild(final Job job) {
        List<Build> builds = job.getBuilds();

        Build first = createTestBuild2();

        assertTrue("Equal Element must be in list", builds.contains(first));
    }

    private Build createTestBuild2() {
        Build build = new Build();
        build.setDuration(3);
        build.setId("2014-08-13_12-26-57");
        build.setNumber(5);
        build.setResult(Result.FAILURE);
        build.setTimestamp(1407925617834L);
        build.setUrl("http://192.168.178.37:8080/job/Neues%20Project/5/");
        return build;
    }

    /**
     * Tests whether all possible result values are parsed
     * correctly to an enum.
     */
    @Test
    public void testResultParsing() {
        createMockServer(BUILDS_URL, getResourceUrl("results.json"));

        client.getAllBuilds(testJob, callback);

        verify(callback, timeout(VERIFY_TIME_OUT)).onSuccess(jobCaptor.capture());
        Job result = jobCaptor.getValue();
        validateResults(result);
    }

    private void validateResults(final Job job) {
        List<Build> builds = job.getBuilds();
        for (Build build : builds) {
            Result result = build.getResult();
            assertNotNull("Each result must have a value", result);
        }
    }

    /**
     * Tests to recieve the last build if there was no build yet.
     */
    @Test
    public void testGetLastBuildWithNoBuild() {
        createMockServer(LAST_BUILD_URL, getResourceUrl("noLastBuild.json"));

        client.getLastBuild(testJob, callback);

        verify(callback, timeout(VERIFY_TIME_OUT)).onSuccess(jobCaptor.capture());
        Job result = jobCaptor.getValue();
        assertNull("There is no lastBuild.json", result.getLastBuild());
    }

    /**
     * Tests to recieve the last build if there was one.
     */
    @Test
    public void testGetLastBuildWithOneBuild() {
        createMockServer(LAST_BUILD_URL, getResourceUrl("lastBuild.json"));
        Build expected = createTestBuild1();

        client.getLastBuild(testJob, callback);

        verify(callback, timeout(VERIFY_TIME_OUT)).onSuccess(jobCaptor.capture());

        Job result = jobCaptor.getValue();
        assertEquals("The values must be correct", expected, result.getLastBuild());
    }

    @Override
    protected String getResourceUrl(final String fileName) {
        return BASE_RESOURCES_URL + fileName;
    }
}
