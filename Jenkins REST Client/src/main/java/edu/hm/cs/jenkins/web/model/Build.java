package edu.hm.cs.jenkins.web.model;


import java.util.Date;

/**
 * Represents one specific build of a {@link edu.hm.cs.jenkins.web.model.Job}.
 *
 * @author Tobias Wochinger
 */
public class Build {

    private String url;

    private String id;

    private int number;

    private long timestamp;

    private long duration;

    private Result result;

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
    }

    public Date getTimestampAsDate() {
        return new Date(timestamp);
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(final long duration) {
        this.duration = duration;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(final Result result) {
        this.result = result;
    }

    //BEGIN GENERATED CODE
    @SuppressWarnings("PMD")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Build build = (Build) o;

        if (duration != build.duration) return false;
        if (number != build.number) return false;
        if (timestamp != build.timestamp) return false;
        if (id != null ? !id.equals(build.id) : build.id != null) return false;
        if (result != build.result) return false;
        if (url != null ? !url.equals(build.url) : build.url != null) return false;

        return true;
    }

    @SuppressWarnings("PMD")
    @Override
    public int hashCode() {
        int result1 = url != null ? url.hashCode() : 0;
        result1 = 31 * result1 + (id != null ? id.hashCode() : 0);
        result1 = 31 * result1 + number;
        result1 = 31 * result1 + (int) (timestamp ^ (timestamp >>> 32));
        result1 = 31 * result1 + (int) (duration ^ (duration >>> 32));
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }
    //END GENERATED CODE
}
