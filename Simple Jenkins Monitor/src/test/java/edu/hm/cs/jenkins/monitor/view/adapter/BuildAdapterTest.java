package edu.hm.cs.jenkins.monitor.view.adapter;

import android.view.View;
import edu.hm.cs.jenkins.monitor.R;
import edu.hm.cs.jenkins.monitor.controller.util.DurationUtil;
import edu.hm.cs.jenkins.web.model.Build;
import edu.hm.cs.jenkins.web.model.Result;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static junit.framework.Assert.assertSame;

/**
 * Tests the class
 * {@link edu.hm.cs.jenkins.monitor.view.adapter.BuildAdapter}.
 *
 * @author Tobias Wochinger
 */
@RunWith(RobolectricTestRunner.class)
public class BuildAdapterTest extends BaseAdapterTest {

    @Inject
    private BuildAdapter buildAdapter;

    @Inject
    private DurationUtil durationUtil;

    private DateFormat dateFormatter;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        initDateFormatter();
    }

    private void initDateFormatter() {
        String dateFormat = getString(R.string.date_format);
        dateFormatter = new SimpleDateFormat(dateFormat, Locale.getDefault());
    }

    @SuppressWarnings("unchecked")
    private List<Build> getListOfExampleBuilds(final Build... builds) {
        return Arrays.<Build>asList(builds);
    }

    private Build getExampleBuild(final int number, final long duration, final long timestamp, final Result result) {
        Build example = new Build();
        example.setNumber(number);
        example.setDuration(duration);
        example.setTimestamp(timestamp);
        example.setResult(result);
        return example;
    }

    /**
     * Tests the adapter with a list with length 0.
     */
    @Test
    public void testWithZeroBuilds() {
        List<Build> emptyList = Collections.emptyList();
        buildAdapter.addAll(emptyList);
        assertSame("JobAdapter must hold zero elements", emptyList.size(), buildAdapter.getCount());
    }

    /**
     * Tests the adapter with a list including one build.
     */
    @Test
    public void testMappingOfOneBuild() {
        Build buildToAdapt = getExampleBuild(5, 1000, 1408561525, Result.ABORTED);

        buildAdapter.add(buildToAdapt);
        assertCorrectListRow(buildAdapter, 0, buildToAdapt);
    }

    private void assertCorrectListRow(final BuildAdapter buildAdapter, final int rowNumberToTest, final Build adapted) {
        View row = buildAdapter.getView(rowNumberToTest, null, null);
        assertCorrectNumber(row, adapted);
        assertCorrectDuration(row, adapted);
        assertCorrectTimestamp(row, adapted);
        assertRightStatusIcon(row, adapted);
    }

    private void assertCorrectNumber(final View view, final Build adaptedBuild) {
        String expectedNumber = "#" + adaptedBuild.getNumber();
        assertCorrectTextView(view, R.id.build_number, expectedNumber);
    }

    private void assertCorrectDuration(final View view, final Build adaptedBuild) {
        long duration = adaptedBuild.getDuration();
        String expectedDuration = durationUtil.getReadableDuration(duration);
        assertCorrectTextView(view, R.id.build_duration, expectedDuration);
    }

    private void assertCorrectTimestamp(final View view, final Build adaptedBuild) {
        long timestamp = adaptedBuild.getTimestamp();
        Date expected = new Date(timestamp);
        String expectedAsString = dateFormatter.format(expected);
        assertCorrectTextView(view, R.id.build_date, expectedAsString);
    }

    private void assertRightStatusIcon(final View view, final Build adaptedBuild) {
        Result result = adaptedBuild.getResult();
        int expectedId = getIdToResult(result);
        assertCorrectImageView(view, R.id.build_result_icon, expectedId);
    }

    /**
     * Tests the adapter with a list including builds with all possible
     * variation of results.
     */
    @Test
    public void testMappingWithListOfAllResults() {
        Build example1 = getExampleBuild(1, 1000, 1408561525, Result.SUCCESS);
        Build example2 = getExampleBuild(5, 5, 1104537600, Result.FAILURE);
        Build example3 = getExampleBuild(100, 30000, 497664000, Result.UNSTABLE);
        Build example4 = getExampleBuild(99, 500, 1377302400, Result.ABORTED);
        Build example5 = getExampleBuild(3, 1234, 1104537600, Result.NOT_BUILT);
        Build example6 = getExampleBuild(3, 1234, 1104537600, null);
        List<Build> testObjects = getListOfExampleBuilds(example1, example2, example3, example4, example5, example6);
        buildAdapter.addAll(testObjects);

        for (int i = 0; i < testObjects.size(); i++) {
            assertCorrectListRow(buildAdapter, i, testObjects.get(i));
        }
    }

    private int getIdToResult(final Result result) {
        if (result == null) {
            return R.drawable.in_progress;
        }

        switch (result) {
            case SUCCESS:
                return R.drawable.blue_ball;
            case FAILURE:
                return R.drawable.red_ball;
            case UNSTABLE:
                return R.drawable.yellow_ball;
            default:
                return R.drawable.grey_ball;
        }
    }
}
