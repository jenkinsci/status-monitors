package edu.hm.cs.jenkins.web.service;


import edu.hm.cs.jenkins.web.model.Jobs;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Interface which Retrofit extends for the Jenkins-REST-API access.
 * Used for operations concerning {@link edu.hm.cs.jenkins.web.model.Job}s
 *
 * @author Tobias Wochinger
 */
public interface JobService {

    /**
     * Does a request to retrieve all jobs with name, url, description,
     * displayName and status.
     *
     * @param callback callback for the result of the operation
     */
    @GET("/api/json?tree=jobs[name,url,description,displayName,color]")
    void getAllJobs(final Callback<Jobs> callback);

    /**
     * Does a request to do a new build of a job.
     *
     * @param jobname  Name of the job to trigger
     * @param token    token for authentication
     * @param callback callback to handle errors and check success
     */
    @POST("/job/{jobname}/build")
    void triggerBuild(@Path("jobname") final String jobname, @Query("token") final String token,
                      final Callback<Object> callback);

}
