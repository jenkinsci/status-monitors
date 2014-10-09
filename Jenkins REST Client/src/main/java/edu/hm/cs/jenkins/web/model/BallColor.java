package edu.hm.cs.jenkins.web.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents possible Jenkins-
 * {@link edu.hm.cs.jenkins.web.model.Job} statuses.
 * The _anime variants are used when a job is currently built.
 *
 * @author Tobias Wochinger
 */
public enum BallColor {

    @SerializedName("red")RED,
    @SerializedName("red_anime")RED_ANIME,

    @SerializedName("yellow")YELLOW,
    @SerializedName("yellow_anime")YELLOW_ANIME,

    @SerializedName("blue")BLUE,
    @SerializedName("blue_anime")BLUE_ANIME,

    @SerializedName("grey")GREY,
    @SerializedName("grey_anime")GREY_ANIME,

    @SerializedName("disabled")DISABLED,
    @SerializedName("disabled_anime")DISABLED_ANIME,

    @SerializedName("aborted")ABORTED,
    @SerializedName("aborted_anime")ABORTED_ANIME,

    @SerializedName("notbuilt")NOTBUILT,
    @SerializedName("notbuilt_anime")NOTBUILT_ANIME
}
