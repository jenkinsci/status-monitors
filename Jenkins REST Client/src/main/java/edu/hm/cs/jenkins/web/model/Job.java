package edu.hm.cs.jenkins.web.model;

import java.util.List;

/**
 * Describes a job / project of jenkins.
 *
 * @author Tobias Wochinger
 */
public class Job {

    private String url;

    private String name;

    private String description;

    private String displayName;

    /**
     * Status of build.
     */
    private BallColor color;

    private List<Build> builds;

    private Build lastBuild;

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    public BallColor getColor() {
        return color;
    }

    public void setColor(final BallColor color) {
        this.color = color;
    }

    public List<Build> getBuilds() {
        return builds;
    }

    public void setBuilds(final List<Build> builds) {
        this.builds = builds;
    }

    public Build getLastBuild() {
        return lastBuild;
    }

    public void setLastBuild(final Build lastBuild) {
        this.lastBuild = lastBuild;
    }

    //BEGIN GENERATED CODE
    @SuppressWarnings("PMD")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (builds != null ? !builds.equals(job.builds) : job.builds != null) return false;
        if (color != job.color) return false;
        if (description != null ? !description.equals(job.description) : job.description != null) return false;
        if (displayName != null ? !displayName.equals(job.displayName) : job.displayName != null) return false;
        if (lastBuild != null ? !lastBuild.equals(job.lastBuild) : job.lastBuild != null) return false;
        if (name != null ? !name.equals(job.name) : job.name != null) return false;
        if (url != null ? !url.equals(job.url) : job.url != null) return false;

        return true;
    }

    @SuppressWarnings("PMD")
    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (builds != null ? builds.hashCode() : 0);
        result = 31 * result + (lastBuild != null ? lastBuild.hashCode() : 0);
        return result;
    }
    //END GENERATED CODE
}
