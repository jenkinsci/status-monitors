package edu.hm.cs.jenkins.monitor.view.adapter;

import android.view.View;
import edu.hm.cs.jenkins.monitor.R;
import edu.hm.cs.jenkins.web.model.BallColor;
import edu.hm.cs.jenkins.web.model.Job;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertSame;

/**
 * Tests the class
 * {@link edu.hm.cs.jenkins.monitor.view.adapter.JobAdapter}.
 *
 * @author Tobias Wochinger
 */
@RunWith(RobolectricTestRunner.class)
public class JobAdapterTest extends BaseAdapterTest {

    @Inject
    private JobAdapter jobAdapter;

    private List<Job> getListOfExampleJobs(final Job... jobs) {
        return Arrays.asList(jobs);
    }

    private Job getExampleJob(final String name, final String displayName, final BallColor color) {
        Job example = new Job();
        example.setName(name);
        example.setDisplayName(displayName);
        example.setColor(color);
        return example;
    }

    /**
     * Tests the adapter with a list with length 0.
     */
    @Test
    public void testWithZeroJobs() {
        List<Job> emptyList = Collections.<Job>emptyList();
        jobAdapter.addAll(emptyList);
        assertSame("JobAdapter must hold zero elements", emptyList.size(), jobAdapter.getCount());
    }

    /**
     * Tests the adapter with a list including one job.
     */
    @Test
    public void testMappingOfOneJob() {
        Job jobToAdapt = getExampleJob("Job", "DisplayName", BallColor.BLUE);

        jobAdapter.add(jobToAdapt);
        assertCorrectListRow(jobAdapter, 0, jobToAdapt);
    }

    private void assertCorrectListRow(final JobAdapter jobAdapter, final int rowNumberToTest, final Job adapted) {
        View row = jobAdapter.getView(rowNumberToTest, null, null);
        assertCorrectDisplayName(row, adapted);
        assertCorrectStatusIcon(row, adapted);
    }

    private void assertCorrectDisplayName(final View view, final Job adaptedJob) {
        String expectedName = adaptedJob.getDisplayName();
        assertCorrectTextView(view, R.id.jobname, expectedName);
    }

    private void assertCorrectStatusIcon(final View view, final Job adaptedJob) {
        BallColor adaptedColor = adaptedJob.getColor();
        int expectedId = getIdToColor(adaptedColor);
        assertCorrectImageView(view, R.id.jobstatus_icon, expectedId);
    }

    /**
     * Tests the adapter with a list including job with all possible
     * variation of ball colors.
     */
    @Test
    public void testMappingWithListOfAllColors() {
        Job example1 = getExampleJob("Job", "1", BallColor.BLUE);
        Job example2 = getExampleJob("Job2", "223", BallColor.BLUE_ANIME);
        Job example3 = getExampleJob("Job5", "3123", BallColor.RED);
        Job example4 = getExampleJob("Job99", "33", BallColor.RED_ANIME);
        Job example5 = getExampleJob("Job22", "32", BallColor.YELLOW);
        Job example6 = getExampleJob("Job22323", "32", BallColor.YELLOW_ANIME);
        Job example7 = getExampleJob("Job222", "33", BallColor.GREY);
        Job example8 = getExampleJob("Job223", "32", BallColor.GREY_ANIME);
        List<Job> testObjects =
                getListOfExampleJobs(example1, example2, example3, example4, example5, example6, example7, example8);
        jobAdapter.addAll(testObjects);

        for (int i = 0; i < testObjects.size(); i++) {
            assertCorrectListRow(jobAdapter, i, testObjects.get(i));
        }
    }

    private int getIdToColor(final BallColor color) {
        switch (color) {
            case BLUE:
                return R.drawable.blue_ball;
            case RED:
                return R.drawable.red_ball;
            case GREY:
                return R.drawable.grey_ball;
            case YELLOW:
                return R.drawable.yellow_ball;
            default:
                return R.drawable.in_progress;
        }
    }
}