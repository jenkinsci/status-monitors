package edu.hm.cs.jenkins.monitor.controller.util;

import edu.hm.cs.jenkins.monitor.BaseTest;
import edu.hm.cs.jenkins.monitor.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import javax.inject.Inject;

import static junit.framework.Assert.assertEquals;

/**
 * Tests the class
 * {@link edu.hm.cs.jenkins.monitor.controller.util.DurationUtil}.
 *
 * @author Tobias Wochinger
 */
@RunWith(RobolectricTestRunner.class)
public class DurationUtilTest extends BaseTest {

    @Inject
    private DurationUtil durationUtil;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        injectMembers();
    }

    /**
     * Tests with a duration which which can be displayed as hours only.
     */
    @Test
    public void testWithHoursOnly() {
        long duration = 7200000;
        String expected = "2 " + getString(R.string.hours_abbreviation);

        assertCorrectReadableDuration(duration, expected);
    }

    private void assertCorrectReadableDuration(final long duration, final String expected) {
        String actual = durationUtil.getReadableDuration(duration);

        assertEquals("Duration must be displayed as expected", expected, actual);
    }

    /**
     * Tests with a duration which which can be displayed as hours and
     * minutes.
     */
    @Test
    public void testWithHoursAndMinutes() {
        long duration = 8100000;
        String expected =
                "2 " + getString(R.string.hours_abbreviation) + ", " + "15 " + getString(R.string.minutes_abbreviation);

        assertCorrectReadableDuration(duration, expected);
    }

    /**
     * Tests with a duration which which can be displayed as hours and
     * seconds.
     */
    @Test
    public void testWithHoursAndSeconds() {
        long duration = 7209000;
        String expected =
                "2 " + getString(R.string.hours_abbreviation) + ", " + "9 " + getString(R.string.seconds_abbreviation);

        assertCorrectReadableDuration(duration, expected);
    }

    /**
     * Tests with a duration which which can be displayed as hours, minutues
     * and seconds.
     */
    @Test
    public void testWithHoursAndMinutesAndSeconds() {
        long duration = 8105000;
        String expected = "2 " + getString(R.string.hours_abbreviation) + ", 15 "
                + getString(R.string.minutes_abbreviation) + ", 5 " + getString(R.string.seconds_abbreviation);

        assertCorrectReadableDuration(duration, expected);
    }

    /**
     * Tests with a duration which which can be displayed as minutes only.
     */
    @Test
    public void testWithMinutesOnly() {
        long duration = 2400000;
        String expected = "40 " + getString(R.string.minutes_abbreviation);

        assertCorrectReadableDuration(duration, expected);
    }

    /**
     * Tests with a duration which which can be displayed as minutes
     * and seconds.
     */
    @Test
    public void testWithMinutesAndSecondsOnly() {
        long duration = 2430000;
        String expected = "40 " + getString(R.string.minutes_abbreviation)
                + ", 30 " + getString(R.string.seconds_abbreviation);

        assertCorrectReadableDuration(duration, expected);
    }

    /**
     * Tests with a duration which which can be displayed as seconds only.
     */
    @Test
    public void testWithSecondsOnly() {
        long duration = 30000;
        String expected = "30 " + getString(R.string.seconds_abbreviation);

        assertCorrectReadableDuration(duration, expected);

    }

    /**
     * Tests with a duration of less than a second.
     */
    @Test
    public void testWithOnlyMilliseconds() {
        long duration = 500;
        String expected = "0 " + getString(R.string.seconds_abbreviation);

        assertCorrectReadableDuration(duration, expected);
    }

    /**
     * Tests with a duration of 0.
     */
    @Test
    public void testWithDurationOfZero() {
        long duration = 0;
        String expected = "0 " + getString(R.string.seconds_abbreviation);

        assertCorrectReadableDuration(duration, expected);
    }
}
