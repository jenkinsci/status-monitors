package edu.hm.cs.jenkins.monitor.controller.util;

import edu.hm.cs.jenkins.monitor.R;
import roboguice.inject.InjectResource;

/**
 * Provides operations around values which represent durations.
 *
 * @author Tobias Wochinger
 */
public class DurationUtil {

    private static final long MILLIS_PER_SECOND = 1000;

    private static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;

    private static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;

    @InjectResource(R.string.hours_abbreviation)
    private String hoursAbbreviation;

    @InjectResource(R.string.minutes_abbreviation)
    private String minutesAbbreviation;

    @InjectResource(R.string.seconds_abbreviation)
    private String secondsAbbreviation;

    /**
     * Converts a duration in a readable String.
     *
     * @param durationInMillis duration in milliseconds
     *                         to be displayed readable
     * @return duration in a more readable format
     */
    public String getReadableDuration(final long durationInMillis) {
        StringBuilder builder = new StringBuilder();

        long hours = getHours(durationInMillis);
        long minutes = getMinutes(durationInMillis);
        long seconds = getSeconds(durationInMillis);

        if (hours > 0) {
            append(builder, hours, hoursAbbreviation);
        }

        if (hours > 0 && minutes > 0) {
            builder.append(", ");
        }

        if (minutes > 0) {
            append(builder, minutes, minutesAbbreviation);
        }

        if ((hours > 0 || minutes > 0) && seconds > 0) {
            builder.append(", ");
        }

        if (hours == 0 && minutes == 0 || seconds > 0) {
            append(builder, seconds, secondsAbbreviation);
        }
        return builder.toString();
    }

    private long getSeconds(final long durationInMillis) {
        return (durationInMillis % MILLIS_PER_MINUTE) / MILLIS_PER_SECOND;
    }

    private long getMinutes(final long durationInMillis) {
        return (durationInMillis % MILLIS_PER_HOUR) / MILLIS_PER_MINUTE;
    }

    private long getHours(final long durationInMillis) {
        return durationInMillis / MILLIS_PER_HOUR;
    }

    private void append(final StringBuilder builder, final long value, final String abbrevation) {
        builder.append(value);
        builder.append(' ');
        builder.append(abbrevation);
    }
}
